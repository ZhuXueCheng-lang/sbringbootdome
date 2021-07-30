package com.gtzn.digitcard.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            return null;
        }
        Cookie cookie1 = null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                cookie1=cookie;
            }
        }
        return cookie1;
    }

}
