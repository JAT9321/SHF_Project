package com.jiao.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.dao.AdminRoleDao;
import com.jiao.entity.AdminRole;
import com.jiao.service.RoleService;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.RoleDao;
import com.jiao.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    protected BaseDao<Role> getEntityDao() {
        return roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Map<String, Object> findRoleByAdminId(Long adminId) {

        List<Role> roleAll = roleDao.findAll();
        List<Long> roleIdsByAdminId = adminRoleDao.findRoleIdsByAdminId(adminId);

        //对角色进行分类
        List<Role> noAssignRoleList = new ArrayList<>();
        List<Role> assignRoleList = new ArrayList<>();
        for (Role role : roleAll) {
            //已分配
            if (roleIdsByAdminId.contains(role.getId())) {
                assignRoleList.add(role);
            } else {
                noAssignRoleList.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("noAssignRoleList", noAssignRoleList);
        roleMap.put("assignRoleList", assignRoleList);

        return roleMap;
    }

    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        adminRoleDao.deleteByAdminId(adminId);

        for (Long roleId : roleIds) {
            if (StringUtils.isEmpty(String.valueOf(roleId)) || roleId==null) continue;
            AdminRole userRole = new AdminRole();
            userRole.setAdminId(adminId);
            userRole.setRoleId(roleId);
            adminRoleDao.insert(userRole);
        }
    }

}
