package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.UserInfoDao;
import com.jiao.entity.UserInfo;
import com.jiao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    protected BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    @Override
    public UserInfo getByPhone(String phone) {
        return userInfoDao.getByPhone(phone);
    }
}