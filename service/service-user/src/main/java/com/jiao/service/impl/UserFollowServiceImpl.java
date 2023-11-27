package com.jiao.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.base.BaseDao;
import com.jiao.base.BaseServiceImpl;
import com.jiao.dao.UserFollowDao;
import com.jiao.entity.UserFollow;
import com.jiao.service.DictService;
import com.jiao.service.UserFollowService;
import com.jiao.vo.UserFollowVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = UserFollowService.class)
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;

    @Reference
    private DictService dictService;

    @Override
    protected BaseDao<UserFollow> getEntityDao() {
        return userFollowDao;
    }

    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userId);
        userFollow.setHouseId(houseId);
        userFollowDao.insert(userFollow);
    }

    @Override
    public Boolean isFollowed(Long userId, Long houseId) {
        Integer count = userFollowDao.countByUserIdAndHouserId(userId, houseId);
        if (count.intValue() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserFollowVo> page = userFollowDao.findListPage(userId);
        List<UserFollowVo> list = page.getResult();
        for (UserFollowVo userFollowVo : list) {
            //户型：
            String houseTypeName = dictService.getById(userFollowVo.getHouseTypeId()).getName();
            //楼层
            String floorName = dictService.getById(userFollowVo.getFloorId()).getName();
            //朝向：
            String directionName = dictService.getById(userFollowVo.getDirectionId()).getName();
            userFollowVo.setHouseTypeName(houseTypeName);
            userFollowVo.setFloorName(floorName);
            userFollowVo.setDirectionName(directionName);
        }
        return new PageInfo<UserFollowVo>(page, 10);
    }

    @Override
    public void cancelFollow(Long id) {
        userFollowDao.updateToDeleteById(id);
    }

}