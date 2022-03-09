package com.example.zuche.filter;

import com.example.zuche.config.ShiroAuthToken;
import com.example.zuche.utils.TokenUtils;
import com.example.zuche.utils.security.MyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @desc :  Shiro 的拦截器，拦截和验证Token 的有效性
 * @Author : chengzhang
 * @Date : 2022/1/24 11:17
 */
public class ShiroAuthFilter extends BasicHttpAuthenticationFilter {

    /**
     * 存储Token 的H Headers Key
     */
    protected static final String AUTHORIZATION_HEADER = "Authorization";


    /**
     * Token的开头部分
     */
    protected static final String BEARER = "Bearer";

    private String token;


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //设置主题
        //自动调用 ShiroRealm 进行Token 检查
        this.getSubject(request, response).login(new ShiroAuthToken(this.token));
        return true;
    }


    /**
     * 是否允许访问
     *
     * @param request     Request
     * @param response    Response
     * @param mappedValue mapperValue
     * @return true 表示允许放行
     */
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Request 中存在 Token
        if (this.getAuthzHeader(request) != null) {
            try {
                executeLogin(request, response);
                //刷新Token1 ,Token 未过期 ，每次都调用refreshToken ，判断是否需要刷新Token
                TokenUtils tokenUtils = new TokenUtils();
                String refreshToken = tokenUtils.refreshToken(this.token);
                if (refreshToken != null) {
                    this.token = refreshToken;
                    shiroAuthResponse(response, true);
                }
                return true;
            } catch (Exception e) {
                //刷新Token2 ，Token已经过期，如果过期是在规定时间内则刷新Token
                TokenUtils tokenUtils = new TokenUtils();
                String refreshToken = tokenUtils.refreshToken(this.token);

                if (refreshToken != null) {
                    this.token = refreshToken.substring(BEARER.length());
                    //重新调用executeLogin 授权
                    executeLogin(request, response);
                    shiroAuthResponse(response, true);
                    return true;
                } else {
                    //Token 刷新失败没得救或者非法Token
                    shiroAuthResponse(response, false);
                    return false;
                }
            }
        } else {
            //Token 不存在 ，返回未授权信息
            shiroAuthResponse(response, false);
            return false;
        }
    }


    /**
     * Token 预处理，从Request 的Header 取得Token
     *
     * @param request ServletRequest
     * @return token or null
     */
    @Override
    protected String getAuthzHeader(ServletRequest request) {

        try {
            //header 是否存在 Token
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            this.token = httpServletRequest.getHeader(AUTHORIZATION_HEADER).substring(BEARER.length());
            return this.token;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 未授权访问或者Header 添加Token
     *
     * @param response Response
     * @param refresh  是否是刷新Token
     */
    private void shiroAuthResponse(ServletResponse response, boolean refresh) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (refresh) {
            //刷新Token,设置返回的头部
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            httpServletResponse.addHeader(AUTHORIZATION_HEADER, BEARER + this.token);

        } else {
            //设置HTTP 状态码为401
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            //设置Json格式返回
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            try {
                //PrintWriter 输出Response返回信息
                PrintWriter writer = httpServletResponse.getWriter();
                ObjectMapper mapper = new ObjectMapper();
                MyResponse myResPonse = new MyResponse("error", "非授权访问");
                writer.write(mapper.writeValueAsString(myResPonse));
            } catch (IOException e) {
                //打印日志
            }


        }

    }
}
