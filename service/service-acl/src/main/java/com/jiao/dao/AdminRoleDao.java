package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.AdminRole;

import java.util.List;

public interface AdminRoleDao extends BaseDao<AdminRole> {

    void deleteByAdminId(Long adminId);

    List<Long> findRoleIdsByAdminId(Long adminId);
}