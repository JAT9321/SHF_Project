package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission> {

    void deleteByRoleId(Long roleId);

    List<Long> findPermissionIdListByRoleId(Long roleId);
}