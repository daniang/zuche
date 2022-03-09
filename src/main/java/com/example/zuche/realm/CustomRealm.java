package com.example.zuche.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.apache.catalina.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2021/12/27 11:25
 */
@Component
public class CustomRealm extends AuthorizingRealm {


    @Resource
    IUsersService usersService;

    @Resource
    IUserRoleService userRoleService;

    @Resource
    IRoleService roleService;
    @Resource
    IRolePermissionsService rolePermissionsService;

    @Resource
    IPermissionsService permissionsService;


    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        //获取登录时候的账号
        String name = authenticationToken.getPrincipal().toString();

        Users user = usersService.getUserByName(name);

        if (null == user) {

            return null;
        } else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return info;
        }

    }

    /**
     * 增加权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String name = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Users user = usersService.getUserByName(name);


        //获得用户的所有角色
        List<UserRole> userRoles = userRoleService.getBaseMapper().selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId()));
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


