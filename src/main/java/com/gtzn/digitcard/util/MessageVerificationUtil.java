package com.gtzn.digitcard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageVerificationUtil {
    //发送验证码
    public static String sendSingleMsg(String mobile, String msg) {
        String url = "http://sms.253.com/msg/send";// 应用地址
//        String account = "N405354_N5625811";// 账号
//        String pswd = "s6oC9O2w5h730d";// 密码
        String account = "N205350_N5625811";// 账号
        String pswd = "bVUQcHZjdTaad1";// 密码
        boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
        String extno = null;// 扩展码

        try {
            String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
//            System.out.println(returnString);
            String regex = "(\\d\\d\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d).*,(.*)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(returnString);
            String time = "";
            while (m.find()) {
                time = m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " " + m.group(4) + ":" + m.group(5);
                returnString = m.group(6);
                // System.out.println("returnString=" + returnString);
            }
            return returnString;
        } catch (Exception e) {
            // TODO 处理异常
            e.printStackTrace();
        }

        return null;
    }
}
