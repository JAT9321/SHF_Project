package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.HouseImageDao;
import com.jiao.entity.HouseImage;
import com.jiao.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseImageService.class)
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }

    @Override
    public List<HouseImage> findList(Long houseId, Integer type) {
        return houseImageDao.findList(houseId, type);
    }
}