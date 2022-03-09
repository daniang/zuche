package com.example.zuche.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.permission.pojo.Role;
import com.example.zuche.permission.pojo.RolePermissions;
import com.example.zuche.permission.pojo.UserRole;
import com.example.zuche.permission.service.IRolePermissionsService;
import com.example.zuche.permission.service.IRoleService;
import com.example.zuche.permission.service.IUserRoleService;
import com.example.zuche.users.pojo.Users;
import com.example.zuche.users.service.IUsersService;
import com.example.zuche.utils.security.RsaUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc :  生成和验证Token的工具类.可选的Token主体部分是指在验证和授权的时候用不上这些信息。
 * @Author : chengzhang
 * @Date : 2022/1/24 9:36
 */
@Component
public class TokenUtils implements Serializable {
    private static final long serialVersionUID = -3L;
    /**
     * Token 有效时长 多少秒
     */
    private static final Long EXPIRATION = 2 * 60L;

    @Resource
    IUserRoleService userRoleService;

    @Resource
    IRoleService roleService;

    @Resource
    IUsersService usersService;

    @Resource
    IRolePermissionsService rolePermissionsService;

    /**
     * 生成Token 字符串，setAudience 接收者setExpiration 过期时间 role 用户角色
     *
     * @param users 用户信息
     * @return 生成的Token 字符串 or null
     */
    public String createToken(Users users) {

        try {
            //Token 的过期时间
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
            List<UserRole> userRoles = userRoleService.getBaseMapper().selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, users.getId()));
            List<Long> roleIds = userRoles.stream().map(x -> x.getRoleId()).collect(Collectors.toList());
            List<Role> roles = roleService.getBaseMapper().selectList(new QueryWrapper<Role>().lambda().in(Role::getId, roleIds));
            List<RolePermissions> rolePermissions = rolePermissionsService.getBaseMapper().selectList(
                    new QueryWrapper<RolePermissions>().lambda().in(RolePermissions::getRoleId, roleIds));

            //生成Token
            String token = Jwts.builder()
                    .setIssuer("StringBoot") //设置Token签发者 可选
                    .setAudience(users.getUsername()) //根据用户名设置Token的接收者
                    .setExpiration(expirationDate) //设置过期时间
                    .setIssuedAt(new Date()) //设置Token生成时间 可选
                    .claim("role", roles) //通过claim 方法设置一个key = role ,value = userRole 的值
                    .claim("permission", rolePermissions)//通过claim 方法设置一个 key= permission ,value = Permissions
                    .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey())
                    .compact();

            return String.format("Bearer %s", token);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证Token,并获取到用户名和用户权限信息
     *
     * @param token Token 字符串
     * @return users 用户信息
     */
    public Users validationToken(String token) {

        try {
            //解密Token ，获取Claims 主体

            Claims claims = Jwts.parser().setSigningKey(RsaUtils.getPublicKey()).parseClaimsJws(token).getBody();

            assert claims != null;

            Users user = usersService.getUserByName(claims.getAudience());


            return user;
        } catch (Exception e) {

            return null;
        }


    }

    /**
     * Token 刷新
     *
     * @param token 旧Token
     * @return String 新Token 或者null
     */
    public String refreshToken(String token) {

        try {
            //解密Token ,获取Claims 主体
            Claims claims = Jwts.parser().setSigningKey(RsaUtils.getPublicKey()).parseClaimsJws(token).getBody();
            //刷新Token1 下面代码是未到期刷新
            //可以更改代码，在验证的Token的时候直接判断是否要刷新Token
            assert claims != null;
            //Token 过期时间
            Date expiration = claims.getExpiration();
            //如果1分钟内过期，则刷新Token
            if (!expiration.before(new Date(System.currentTimeMillis() + 60 * 1000))) {
                return null;
            }

            Users user = usersService.getUserByName(claims.getAudience());

            return createToken(user);


        } catch (ExpiredJwtException e) {
            //刷新Token2 ： Token 在解密的时候会自动判断是否过期
            //过期ExpiredJwtException 可以通过 e.getClaims() 取得claims
            //实际中千万不要直接这么用
            //todo 需要自己用RSA 算法验证Token 的合法性
            try {
                Claims claims = e.getClaims();
                //如果claims 不为空表示Token正常解析出了主题部分
                assert claims != null;
                //Token 过期时间
                Date expiration = claims.getExpiration();
                //如果过期时间在10分钟 内,则刷新Token
                if (!expiration.after(new Date(System.currentTimeMillis() - 10 * 60 * 1000))) {
                    //超过10分钟 ，没得救了
                    return null;
                } else {
                    Users user = usersService.getUserByName(claims.getAudience());
                    return createToken(user);
                }
            } catch (Exception e1) {
                return null;
            }
        }


    }
}
