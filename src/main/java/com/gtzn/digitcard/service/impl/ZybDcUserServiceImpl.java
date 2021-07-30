package com.gtzn.digitcard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gtzn.digitcard.dao.ZybDcUserDao;
import com.gtzn.digitcard.exception.ApiException;
import com.gtzn.digitcard.model.ZybDcUser;
import com.gtzn.digitcard.service.ZybDcUserService;
import com.gtzn.digitcard.util.AESUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 用户信息表zyb_dc_user(ZybDcUser)表服务实现类
 *
 * @author makejava
 * @since 2021-02-22 10:52:30
 */
@Service("zybDcUserService")
public class ZybDcUserServiceImpl extends ServiceImpl<ZybDcUserDao,ZybDcUser> implements ZybDcUserService {
    @Resource
    private ZybDcUserDao zybDcUserDao;

    /**
     * 根据手机号获取用户信息
     * @param mobilePhone
     * @return
     */
    @Override
    public ZybDcUser queryUserByMobilePhone(String mobilePhone) {
        return null;
    }


    /**
     * 用户注册
     * @param zybDcUser
     * @return
     */
    @Override
    public Boolean userRegistration(ZybDcUser zybDcUser) {

            zybDcUser.setJobStatus(1);//在职状态
            zybDcUser.setRegisterTime(LocalDateTime.now());//注册时间
            zybDcUser.setEnableStatus(1);//启/停用状态1启用，2停用
            zybDcUser.setAccessLevel(3);//权限等级1超级管理员，2管理员，3会员
            zybDcUser.setIsPayPassword(1);//是否已设置支付密码状态：1未设置，2已设置
            int count = zybDcUserDao.insert(zybDcUser);
            if (count > 0) {
                return true;
            } else {
                return false;
            }

    }

    /**
     * 用户登录
     * @param response
     * @param session
     * @param mobilePhone 手机号
     * @param code 验证码
     * @return
     */
    @Override
    public ZybDcUser userLogin(HttpServletResponse response, HttpSession session, String mobilePhone, String code){

        ZybDcUser zybDcUser = (ZybDcUser) session.getAttribute(code);
        if (zybDcUser == null){
            throw new ApiException("403","验证码错误！");
        }else if (zybDcUser.getEnableStatus() == 2) {
            throw new ApiException("403","该账户已停用，请联系管理人员！");
        }else {
            //添加登录状态
            response.setHeader("Authorization","authorization");
            doLongin(response,session,zybDcUser);
            return zybDcUser;
        }

    }


    /**
     * 登录状态设置
     * @param response
     * @param session
     * @param zybDcUser
     */
    private void doLongin(HttpServletResponse response, HttpSession session , ZybDcUser zybDcUser){
//        String uuid = UUID.randomUUID().toString();
        String md5UserName = AESUtil.encrypt(zybDcUser.getMobilePhone());
        Cookie cookie = new Cookie("szfkck", md5UserName);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(24 * 3600 * 90);
        response.addCookie(cookie);
        session.setAttribute("szfkss", zybDcUser);
        session.setMaxInactiveInterval(60*60*24*90);

        Cookie zybLogin = new Cookie("zybLogin", AESUtil.encrypt(zybDcUser.getMobilePhone()));
        zybLogin.setPath("/");
        zybLogin.setHttpOnly(true);
        zybLogin.setMaxAge(48*3600*365);
        zybLogin.setDomain("ziubao.com");
        response.addCookie(zybLogin);
    }

}
