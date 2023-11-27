package com.jiao.service;

import com.github.pagehelper.PageInfo;
import com.jiao.base.BaseService;
import com.jiao.entity.House;
import com.jiao.vo.HouseQueryVo;
import com.jiao.vo.HouseVo;

public interface HouseService extends BaseService<House> {

    void publish(Long id, Integer status);

    PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo);
}
