package com.jiao.config;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jiao.entity.Admin;
import com.jiao.service.AdminService;
import com.jiao.service.PermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


//Spring Security支持通过实现UserDetailsService接口的方式来提供用户认证授权信息
@Component
public class MyUserDetailService implements UserDetailsService {

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Username is " + username);
        Admin admin = adminService.getByUsername(username);
        if (null == admin) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //用户权限注入
        List<String> codeListByAdminId = permissionService.findCodeListByAdminId(admin.getId());
        ArrayList<GrantedAuthority> authrities = new ArrayList<>();
        for (String code : codeListByAdminId) {
            if (StringUtils.isEmpty(code)) continue;
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(code);
            authrities.add(grantedAuthority);
        }
        return new User(username, admin.getPassword(), authrities);
    }
}
