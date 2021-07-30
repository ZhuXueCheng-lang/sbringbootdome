package com.gtzn.digitcard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Package: com.*.*.config
 * @ClassName: LoginConfig
 * @Description:拦截器配置
 * @author: bzf
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/zybDcUser/userRegistration",            //用户注册
                "/zybDcUser/userLogin",   //用户登录
                "/zybDcUser/getCode",     //用户登录发送短信验证
                "/zybDcUser/getRegistrationCode",  //用户注册发送短信验证码
                "/zybDcUser/userLogout",    //退出登录
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.png",
                "/**/*.jpg",
                "/**/*.jpeg",
                "/**/*.woff",
                "/orderNotice/orderCancel",
                "/**/*.ttf"
        );
        registration.excludePathPatterns(
                "/swagger-resources/**",
                "/webjars/**",
                "/v2/**",
                "/swagger-ui.html/**"
        );

    }


}

