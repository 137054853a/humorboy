package com.humorboy.commons.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomUtil {

    /**
     * 通用id字符串生成类
     *
     * @return
     */
    public static String createId() {
        String str = UUID.randomUUID().toString() + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + (int) (1 + Math.random() * (999 - 1 + 1));
        try {
            // 获取MD5算法对象
            MessageDigest instance = MessageDigest.getInstance("MD5");
            // 对字符串加密,返回字节数组
            byte[] digest = instance.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString().substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            // 没有该算法时,抛出异常, 不会走到这里
        }
        return null;
    }
}
