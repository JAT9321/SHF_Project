package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.HouseBrokerDao;
import com.jiao.entity.HouseBroker;
import com.jiao.service.HouseBrokerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseBrokerService.class)
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

   @Autowired
   private HouseBrokerDao houseBrokerDao;

   @Override
   protected BaseDao<HouseBroker> getEntityDao() {
      return houseBrokerDao;
   }

   @Override
   public List<HouseBroker> findListByHouseId(Long houseId) {
      return houseBrokerDao.findListByHouseId(houseId);
   }
}