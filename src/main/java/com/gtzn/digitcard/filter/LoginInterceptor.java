package com.gtzn.digitcard.filter;

import com.gtzn.digitcard.model.ZybDcUser;
import com.gtzn.digitcard.service.ZybDcUserService;
import com.gtzn.digitcard.util.AESUtil;
import com.gtzn.digitcard.util.CookieUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Package: com.*.*.interceptor
 * @ClassName: AdminInterceptor
 * @Description:拦截器
 * @author: bzf
 */
@Log4j2
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ZybDcUserService zybDcUserService;


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");

        try {

            //统一拦截（查询当前session是否存在userName）(这里userName会在每次登陆成功后，写入session)
            HttpSession session = request.getSession();
            //szfkss = 用户登录session
            ZybDcUser mobilePhone=(ZybDcUser) session.getAttribute("szfkss");

            if(mobilePhone!=null){
                return true;
            } else {
                //szfkck = 用户登录cookie
                Cookie qsdlck = CookieUtil.getCookieByName(request, "szfkck");
                if(qsdlck !=null){
                    String value = qsdlck.getValue();
                    //解密
                    String decrypt = AESUtil.decrypt(value);
//                    Logbook a = logbookService.queryById(1);
                    ZybDcUser zybDcUser = zybDcUserService.queryUserByMobilePhone(decrypt);
                    if (zybDcUser != null){
                        session.setAttribute("szfkss", zybDcUser);
                        session.setMaxInactiveInterval(60*60*24*90);
                        return true;
                    }
//                    else {
//                        response.sendError(101);
//                        return false;
//                    }
                }
//                else {
//                    response.sendError(101);
//                    return false;
//                }
            }
//            response.sendRedirect(request.getContextPath()+"/riderInfo/riderLogin");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            PrintWriter res = response.getWriter();
//            res.append(mapper.writeValueAsString(R.error("101","Not Login")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        session.setAttribute(
//                "qsdlss",RiderInfo.builder().id(3).userName("18605807725")
//                        .riderName("应琪瑜")
//                        .riderPhone("18605807725")
//                        .team(2)
//                        .carType(0)
//                        .riderType(1)
//                        .longitude("333.3333333")
//                        .latitude("333.3333333")
//                        .riderStatus(0)
//                        .enableStatus(0)
//                        .isInstant(1)
//                        .accessLevel(1).build()
//        );
//        session.setMaxInactiveInterval(60*60*24*30);

        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

//    /**
//     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
////         System.out.println("执行了TestInterceptor的postHandle方法");
//    }
//
//    /**
//     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
////        System.out.println("执行了TestInterceptor的afterCompletion方法");
//    }

}
