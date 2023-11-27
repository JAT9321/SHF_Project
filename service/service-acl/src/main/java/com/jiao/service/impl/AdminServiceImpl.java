package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.service.AdminService;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseService;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.AdminDao;
import com.jiao.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }


    @Override
    public Admin getByUsername(String username) {
        return adminDao.getByUsername(username);
    }

}
