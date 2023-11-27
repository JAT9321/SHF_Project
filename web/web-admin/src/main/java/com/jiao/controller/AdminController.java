package com.jiao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jiao.service.AdminService;
import com.jiao.base.BaseController;
import com.jiao.entity.Admin;
import com.jiao.service.RoleService;
import com.jiao.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    //    @Autowired 这里要改用dubbo的注解，不仅会导入bean也会创建对应的dubbo对象，方便引用rpc远程服务
    @Reference
    private AdminService adminService;

    @Reference
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String PAGE_ASSIGN_SHOW = "admin/assignShow";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
//        HttpSession requestSession = request.getSession();
//        requestSession.setAttribute("Jio", "1239321");
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);
        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return "admin/index";
    }

    @GetMapping("create")
    public String create() {
        return "admin/create";
    }

    @PostMapping("/save")
    public String save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        // 默认头像
        admin.setHeadUrl("https://cdn.jsdelivr.net/gh/cxy-sky/jat-blog-img/N10AXH]~9J]J2VXVX96LONU.png");
        adminService.save(admin);
        return "common/successPage";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin", admin);
        return "admin/edit";
    }

    @PostMapping("/update")
    public String update(Admin admin) {
        adminService.update(admin);
        return "common/successPage";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/uploadShow/{id}")
    public String uploadShow(ModelMap model, @PathVariable Long id) {
        model.addAttribute("id", id);
        return "admin/upload";
    }

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
        String newFileName = UUID.randomUUID().toString();
        // 上传图片
        QiniuUtil.upload2Qiniu(file.getBytes(), newFileName);
        String url = "你的七牛云空间的域名/" + newFileName;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl(url);
        adminService.update(admin);
        return "common/successPage";
    }


    /**
     * 进入分配角色页面
     *
     * @param adminId
     * @return
     */
    @GetMapping("/assignShow/{adminId}")
    public String assignShow(ModelMap model, @PathVariable Long adminId) {
        Map<String, Object> roleMap = roleService.findRoleByAdminId(adminId);
        model.addAllAttributes(roleMap);
        model.addAttribute("adminId", adminId);
        return PAGE_ASSIGN_SHOW;
    }

    /**
     * 根据用户分配角色
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    @PostMapping("/assignRole")
    public String assignRole(Long adminId, Long[] roleIds) {
        roleService.saveUserRoleRealtionShip(adminId, roleIds);
        return PAGE_SUCCESS;
    }


}
