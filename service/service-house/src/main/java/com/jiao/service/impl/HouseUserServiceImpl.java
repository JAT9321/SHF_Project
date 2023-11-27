package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.HouseUserDao;
import com.jiao.entity.HouseUser;
import com.jiao.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserDao.findListByHouseId(houseId);
    }
}