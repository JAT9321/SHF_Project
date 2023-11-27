package com.jiao.dao;

import com.jiao.base.BaseDao;
import com.jiao.entity.AdminRole;
import com.jiao.entity.Role;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {

    List<Role> findAll();




}
