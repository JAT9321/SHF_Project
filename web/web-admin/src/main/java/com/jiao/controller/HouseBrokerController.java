package com.jiao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiao.base.BaseController;
import com.jiao.entity.Admin;
import com.jiao.entity.HouseBroker;
import com.jiao.service.AdminService;
import com.jiao.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/houseBroker")
public class HouseBrokerController extends BaseController {

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private AdminService adminService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_EDIT = "houseBroker/edit";
    private final static String PAGE_SUCCESS = "common/successPage";


    /**
     * 进入新增
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String create(ModelMap model, @RequestParam("houseId") Long houseId) {
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("houseId", houseId);
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     *
     * @param houseBroker
     * @return
     */
    @PostMapping("/save")
    public String save(HouseBroker houseBroker) {
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.save(houseBroker);
        return PAGE_SUCCESS;
    }

    /**
     * 编辑
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        HouseBroker houseBroker = houseBrokerService.getById(id);
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("houseBroker", houseBroker);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     *
     * @param houseBroker
     * @return
     */
    @PostMapping(value = "/update")
    public String update(HouseBroker houseBroker) {
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.update(houseBroker);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id) {
        houseBrokerService.deleteById(id);
        return LIST_ACTION + houseId;
    }

}