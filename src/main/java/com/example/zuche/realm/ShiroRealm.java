package com.example.zuche.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.config.ShiroAuthToken;
import com.example.zuche.permission.pojo.Permissions;
import com.example.zuche.permission.pojo.Role;
import com.example.zuche.permission.pojo.RolePermissions;
import com.example.zuche.permission.pojo.UserRole;
import com.example.zuche.permission.service.IPermissionsService;
import com.example.zuche.permission.service.IRolePermissionsService;
import com.example.zuche.permission.service.IRoleService;
import com.example.zuche.permission.service.IUserRoleService;
import com.example.zuche.users.pojo.Users;
import com.example.zuche.users.service.IUsersService;
import com.example.zuche.utils.TokenUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc :  从ShiroRealm 取得Token 并进行身份验证和角色权限配置
 * @Author : chengzhang
 * @Date : 2022/1/24 10:54
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    TokenUtils tokenUtils;

    @Resource
    IUserRoleService userRoleService;

    @Resource
    IRoleService roleService;

    @Resource
    IPermissionsService permissionsService;

    @Resource
    IRolePermissionsService rolePermissionsService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //指定当前authenticationToken 需要为ShiroAuthToken 的实体


        return token instanceof ShiroAuthToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroAuthToken shiroAuthToken = (ShiroAuthToken) authenticationToken;

        String token = (String) shiroAuthToken.getCredentials();

        //验证Token

        Users users = tokenUtils.validationToken(token);

        if (users == null || users.getUsername() == null) {
            throw new AuthenticationException("Token 无效");
        }
        return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取用户信息

        Users users = tokenUtils.validationToken(principalCollection.toString());


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        //获得用户的所有角色
        List<UserRole> userRoles = userRoleService.getBaseMapper().selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, users.getId()));
        if (CollectionUtils.isEmpty(userRoles)) {
            return info;
        }
        List<Long> roleIds = userRoles.stream().map(x -> x.getRoleId()).collect(Collectors.toList());
        List<Role> roles = roleService.getBaseMapper().selectList(new QueryWrapper<Role>().lambda().in(Role::getId, roleIds));
        info.addRoles(roles.stream().map(x -> x.getRoleName()).collect(Collectors.toList()));
        List<RolePermissions> rolePermissions = rolePermissionsService.getBaseMapper().selectList(
                new QueryWrapper<RolePermissions>().lambda().in(RolePermissions::getRoleId, roleIds));
        List<Long> permissionIds = rolePermissions.stream().map(x -> x.getPermissionsId()).distinct().collect(Collectors.toList());
        List<Permissions> permissions1 = permissionsService.listByIds(permissionIds);
        info.addStringPermissions(permissions1.stream().map(x -> x.getPermissionsName()).collect(Collectors.toList()));

        return info;
    }
}

