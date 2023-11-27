package com.jiao.service;

import com.jiao.base.BaseService;
import com.jiao.entity.HouseImage;

import java.util.List;

public interface HouseImageService extends BaseService<HouseImage> {

    List<HouseImage> findList(Long houseId, Integer type);
}