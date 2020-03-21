package com.humorboy.practise.utils;

import java.text.MessageFormat;
import java.util.Random;

/**
 * @Date 16:40
 * @Author PC028
 */
public class IdGeneratorUtils {

    public static Integer genItemId() {
        /**
         * id 生成规则，100010010
         * 1000 系统编码
         * 10000 表示编号，编号随机生成
         * */
        //取当前时间的长整形值包含毫秒
//        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        Integer end2 = random.nextInt(100000);
        //如果不足两位前面补0
        String str = "10" + String.format("%02d", end2);
        Integer id = Integer.valueOf(str);
        return id;
    }

    public static String getAccountID() {
        /**0 时间 1 身份 2 3位随机码 **/
        String accTemplate = "{0}{1}{2}";
        String currentMonth = DateUtils.getCurrentMonth1();
//        取4位随机码
        Random random = new Random();
        Integer ff = random.nextInt(9999);
        int length = ff.toString().length();
//        补码
        String aa = "";
        for (int i = 0; i < 4 - length; i++) {
            aa += "0";
        }
        String message = MessageFormat.format(accTemplate, currentMonth, "02", ff+aa);
        return message;
    }


}
