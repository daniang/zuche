package com.example.zuche.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.permission.dao.PermissionsMapper;
import com.example.zuche.permission.dao.RoleMapper;
import com.example.zuche.permission.dao.RolePermissionsMapper;
import com.example.zuche.permission.dao.UserRoleMapper;
import com.example.zuche.permission.pojo.Permissions;
import com.example.zuche.permission.pojo.Role;
import com.example.zuche.permission.pojo.RolePermissions;
import com.example.zuche.permission.pojo.UserRole;
import com.example.zuche.users.pojo.Users;
import com.example.zuche.users.dao.UsersMapper;
import com.example.zuche.users.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-16
 */
@Service
@Component
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionsMapper rolePermissionsMapper;

    @Resource
    private PermissionsMapper permissionsMapper;


    @Override
    public List<Users> getAll() {

        return this.baseMapper.getAll();
    }

    @Override
    public List<Users> getByAge(Integer age) {
        return this.baseMapper.getByAge(age);
    }

    @Override
    public Users getUserByName(String name) {
        Users users = this.baseMapper.selectOne(new QueryWrapper<Users>().lambda().eq(Users::getAccount, name));

        List<UserRole> userRole = userRoleMapper.selectList(new QueryWrapper<UserRole>().lambda()
                .eq(UserRole::getUserId, users.getId()));

        List<String> roleIds = userRoleMapper.selectObjs(
                new QueryWrapper<UserRole>().lambda().select(UserRole::getRoleId).eq(UserRole::getUserId, users.getId()))
                .stream().map(x -> x.toString()).collect(Collectors.toList());

        List<Role> roles = roleMapper.selectBatchIds(roleIds);

        Set<Role> roleSet    = roles.stream().collect(Collectors.toSet());


        for (Role role : roleSet) {

            List<RolePermissions> rolePermissions = rolePermissionsMapper.selectList(new QueryWrapper<RolePermissions>().lambda().eq(RolePermissions::getRoleId, role.getId()));

            List<Permissions> permissions = permissionsMapper.selectBatchIds(rolePermissions.stream().map(RolePermissions::getPermissionsId).collect(Collectors.toSet()));

            Set<Permissions> permissionsSet = permissions.stream().collect(Collectors.toSet());

            role.setPermissions(permissionsSet);
        }
        users.setRoles(roleSet);

        return users;
    }

    @Override
    public Integer testUsers() {


        List<Users> users = this.baseMapper.selectList(new QueryWrapper<Users>().lambda().notExists("select 1 from user_role ur where users.id = ur.user_id "));


        return 1;
    }
}
