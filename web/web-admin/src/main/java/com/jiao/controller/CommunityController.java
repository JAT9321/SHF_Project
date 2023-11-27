package com.jiao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jiao.base.BaseController;
import com.jiao.entity.Community;
import com.jiao.entity.Dict;
import com.jiao.service.CommunityService;
import com.jiao.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Reference
    CommunityService communityService;
    @Reference
    DictService dictService;


    private final static String LIST_ACTION = "redirect:/community";

    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Community> page = communityService.findPage(filters);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList", areaList);
        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return "community/index";
    }


    /**
     * 进入新增
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String create(ModelMap model) {
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList", areaList);
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     *
     * @param community
     * @return
     */
    @PostMapping("/save")
    public String save(Community community) {
        communityService.save(community);
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
        Community community = communityService.getById(id);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("community", community);
        model.addAttribute("areaList", areaList);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     *
     * @param community
     * @return
     */
    @PostMapping(value = "/update")
    public String update(Community community) {

        communityService.update(community);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communityService.deleteById(id);
        return LIST_ACTION;
    }

}
