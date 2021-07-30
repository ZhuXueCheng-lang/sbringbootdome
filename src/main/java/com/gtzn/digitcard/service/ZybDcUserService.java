package com.gtzn.digitcard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gtzn.digitcard.model.ZybDcUser;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户信息表zyb_dc_user(ZybDcUser)表服务接口
 *
 * @author makejava
 * @since 2021-02-22 10:52:28
 */
public interface ZybDcUserService extends IService<ZybDcUser> {

    /**
     * 根据手机号获取用户信息
     * @param mobilePhone
     * @return
     */
    ZybDcUser queryUserByMobilePhone(String mobilePhone);

    /**
     * 用户注册
     * @param zybDcUser
     * @return
     */
    Boolean userRegistration(ZybDcUser zybDcUser);

    /**
     * 用户登录
     * @param response
     * @param session
     * @param mobilePhone
     * @param code
     * @return
     */
    ZybDcUser userLogin(HttpServletResponse response, HttpSession session,String mobilePhone,String code);

}
