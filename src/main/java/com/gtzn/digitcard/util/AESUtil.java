package com.gtzn.digitcard.util;

import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Log4j2
public class AESUtil {
    private static KeyGenerator kgen;
    private static String password = "gtznzybdis";
    static {
        try {

            kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private static SecretKey secretKey = kgen.generateKey();
    private static byte[] enCodeFormat = secretKey.getEncoded();
    private static SecretKeySpec secretKeySpec =new SecretKeySpec(enCodeFormat, "AES");
    private static Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            byte[] result = cipher.doFinal(byteContent);
            String strResult = byte2hex(result);
            return strResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(hexStringToBytes(content));
            String strResult = new String(result,"UTF-8");
            return strResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String byte2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        String tmp = "";
        for (int n = 0; n < bytes.length; n++) {
            tmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}
