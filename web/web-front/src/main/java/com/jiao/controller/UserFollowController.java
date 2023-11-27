package com.jiao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiao.base.BaseController;
import com.jiao.entity.UserInfo;
import com.jiao.result.Result;
import com.jiao.service.UserFollowService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/userFollow")
@CrossOrigin

public class UserFollowController extends BaseController {

    @Reference
    private UserFollowService userFollowService;

    /**
     * 关注房源
     *
     * @param houseId
     * @param request
     * @return
     */
    @GetMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable("houseId") Long houseId, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        userFollowService.follow(userId, houseId);
        return Result.ok();
    }


    @GetMapping("/auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable("id") Long id, HttpServletRequest request) {
        userFollowService.cancelFollow(id);
        return Result.ok();
    }
}