package com.jingli.admin.util.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @ClassName AuthUtils
 * @Description:
 * @Author 杨膑
 * @Version V1.0
 **/
public class AuthUtils {

    public static String md5Hex(String password, String salt) {
        return md5Hex(password + salt);
    }

    public static String md5Hex(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bs = md5.digest(str.getBytes());
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < bs.length; ++i) {
            if (Integer.toHexString(255 & bs[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & bs[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & bs[i]));
            }
        }

        return md5StrBuff.toString();

    }
}
