package com.jiao.interceptor;

import com.jiao.result.Result;
import com.jiao.result.ResultCodeEnum;
import com.jiao.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录验证拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
            throws Exception {

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView model) throws Exception {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Object userInfo = request.getSession().getAttribute("USER");
        if (null == userInfo) {
            Result result = Result.build("未登陆", ResultCodeEnum.LOGIN_AUTH);
            WebUtil.writeJSON(response, result);
            return false;
        }
        return true;
    }


}
