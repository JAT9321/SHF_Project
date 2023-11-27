package com.jiao.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jiao.service.PermissionService;
import com.jiao.service.RoleService;
import com.jiao.base.BaseController;
import com.jiao.entity.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    //    @Autowired
    @Reference
    RoleService roleService;

    @Reference
    private PermissionService permissionService;

    private final static String PAGE_ASSIGN_SHOW = "role/assignShow";
    private final static String PAGE_SUCCESS = "common/successPage";

    //    @RequestMapping 用下面带有分页查询的方法
    //    public String index(ModelMap model) {
    //        List<Role> roles = roleService.getAll();
    //        // 这里的key要用list 是因为前端接受参数名是list
    //        model.put("list", roles);
    //        return "role/index";
    //    }
    @PreAuthorize("hasAuthority('role.show')")
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> pageInfo = roleService.findPage(filters);
        model.addAttribute("page", pageInfo);
        model.addAttribute("filters", filters);
        return "role/index";
    }

    //弹出增加小窗口
    @PreAuthorize("hasAuthority('role.create')")
    @GetMapping("/create")
    public String create() {
        return "role/create";
    }

    @PreAuthorize("hasAuthority('role.create')")
    @PostMapping("save")
    public String save(Role role, HttpServletRequest request) {
        roleService.save(role);
        return "common/successPage";
    }

    // 查询id对应的学生数据，并转会给前端，在另一个页面显示
    @PreAuthorize("hasAuthority('role.edit')")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Role role = roleService.getById(id);
        model.put("role", role);
        return "role/edit";
    }

    @PreAuthorize("hasAuthority('role.edit')")
    @PostMapping("/update")
    public String update(Role role) {
        roleService.update(role);
        return "common/successPage";
    }

    @PreAuthorize("hasAuthority('role.delete')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/role";
    }


    /**
     * 进入分配权限页面
     *
     * @param roleId
     * @return
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    @GetMapping("/assignShow/{roleId}")
    public String assignShow(ModelMap model, @PathVariable Long roleId) {
        List<Map<String, Object>> zNodes = permissionService.findPermissionsByRoleId(roleId);
        model.addAttribute("zNodes", zNodes);
        model.addAttribute("roleId", roleId);
        return PAGE_ASSIGN_SHOW;
    }

    /**
     * 给角色分配权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    @PostMapping("/assignPermission")
    public String assignPermission(Long roleId, Long[] permissionIds) {
        permissionService.saveRolePermissionRealtionShip(roleId, permissionIds);
        return PAGE_SUCCESS;
    }


}
