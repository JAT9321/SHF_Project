package com.jiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//SpringSecurity的配置类 需要配置包扫描
@Configuration
@EnableWebSecurity // 开启spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启controller方法权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //内存保存用户，方便验证
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("lucy")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("");
//    }

    // 密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //默认Spring Security不允许iframe嵌套显示，我们需要设置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        //必须调用父类的方法，否则就不需要认证即可访问
        //        super.configure(http); //当进行自定义配置时，这个就不需要了
        //        //允许iframe嵌套显示
        //        //http.headers().frameOptions().disable();
        //        //允许iframe显示  sameOrigin->相同的域名下，可以进行嵌套显示
        //        http.headers().frameOptions().sameOrigin();

        //        允许iframe显示  sameOrigin->相同的域名下，可以进行嵌套显示
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/static/**", "/loginPage").permitAll()//放行静态资源和登录需要访问的地址,前端向后端发起的请求进行放行
                .anyRequest().authenticated()//其他都需要经过验证
                .and()
                .formLogin()
                .loginPage("/loginPage")//登录界面表单的地址，spring security 由loginPage跳转到登录界面frame/login
                .usernameParameter("username")//默认的也是username，如果表单中对应的也是username就可以省略此项配置
                .passwordParameter("password")//和username一样
                .loginProcessingUrl("/login")//提交表单的处理地址 spring security自己配置的有这个，我们不需要去写对应的登录验证方法
                .defaultSuccessUrl("/")//登录成功跳转到哪
                //.failureForwardUrl()//登录失败去哪里
                .and()
                .logout()
                .logoutUrl("/logout")////退出登陆的路径，指定spring security拦截的注销url,退出功能是security提供的
                .logoutSuccessUrl("/login")//用户退出后要被重定向的url
                .and()
                .csrf().disable();//关闭跨域请求伪造功能
        //添加自定义无权限处理器
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeineHandler());
    }
}
