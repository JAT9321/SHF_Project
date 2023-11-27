package com.jiao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jiao.entity.Admin;
import com.jiao.entity.Permission;
import com.jiao.service.AdminService;
import com.jiao.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrameController {

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;

    /**
     * 框架首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        //后续替换为当前登录用户id
        //Long adminId = 1L;
        //spring security验证登录成功后，会的登录的信息放到它的上下文里：
        //        {
        //            "accountNonExpired": true,
        //                "accountNonLocked": true,
        //                "authorities": [],
        //            "credentialsNonExpired": true,
        //                "enabled": true,
        //                "username": "admin"
        //        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Admin admin = adminService.getByUsername(user.getUsername());
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("permissionList", permissionList);
        return "frame/index";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "frame/login";
    }


    // 框架首页
//    @GetMapping("/")
//    public String index() {
//        return "frame/index";
//    }

    //框架主页
    @GetMapping("/main")
    public String main() {
        return "frame/main";
    }

}
