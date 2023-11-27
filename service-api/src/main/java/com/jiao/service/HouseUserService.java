package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {

    List<HouseUser> findListByHouseId(Long houseId);
}