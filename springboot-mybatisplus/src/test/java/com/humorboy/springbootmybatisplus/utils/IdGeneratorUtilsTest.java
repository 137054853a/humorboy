package com.humorboy.springbootmybatisplus.utils;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Random;

class IdGeneratorUtilsTest {

    @Test
    void genItemId() {
//        for (int i = 0; i < 10; i++) {
//
//        }
        long l = IdGeneratorUtils.genItemId();
        System.err.println("===========》  " + l);
    }

    @Test
    void g() {
        /**0 时间 1 身份 2 3位随机码 **/
        String accTemplate = "{0}{1}{2}";
        String currentMonth = DateUtils.getCurrentMonth1();
        for (int i = 0; i < 10; i++) {
            String s = randCode();
            String message = MessageFormat.format(accTemplate, currentMonth, "02", s);
            System.out.println(message);
        }
    }

    private String randCode() {
        Random random = new Random();
        Integer ff = random.nextInt(9999);
        int length = ff.toString().length();
        String aa = "";
        for (int i = 0; i < 4 - length; i++) {
            aa += "0";
        }
        return aa + ff;
    }
}