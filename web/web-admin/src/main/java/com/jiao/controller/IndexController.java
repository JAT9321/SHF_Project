package com.jiao.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class IndexController {

    private final static String PAGE_AUTH = "frame/auth";

    @GetMapping("getInfo")
    @ResponseBody
    public Object getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getPrincipal();
    }

    @GetMapping("/auth")
    public String auth() {
        return PAGE_AUTH;
    }


}
