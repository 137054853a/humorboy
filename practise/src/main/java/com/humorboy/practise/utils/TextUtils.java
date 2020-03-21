package com.humorboy.practise.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * txt文档章节重排序、去重，并重新生成文件
 * @Date 11:06
 * @Author PC028
 */
public class TextUtils {
    private void readFile(String uri) throws IOException {
        Map<String, String> chapters = new HashMap<>();
        List<String> headers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(uri)),
                "UTF-8"));
        String reg = ".*第[0-9]+章.*";
        String lineTxt = null;
        String head = null;
        StringBuilder chapter = new StringBuilder();
        while ((lineTxt = br.readLine()) != null) {
            if (StringUtils.isEmpty(lineTxt.trim())) {//去除空行
                continue;
            }
            if (lineTxt.matches(reg)) {
                chapters.put(head, chapter.toString());
                head = lineTxt;
                chapter = new StringBuilder();//清空章节数据
                headers.add(lineTxt);
                continue;
            }
            chapter.append(lineTxt.trim());//拼装章节
        }
        List<String> newHeaders = newHeader(headers);
        FileWriter fileWriter = new FileWriter(new File("D:/重生药王.txt"));
        BufferedWriter bwriter = new BufferedWriter(fileWriter);
        newHeaders.forEach(x -> {
            try {
                bwriter.write(x + "\r\n");
                String s = chapters.get(x);
                bwriter.write(s + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bwriter.close();
        fileWriter.close();
    }

    private List<String> newHeader(List<String> headers) {
        final String regEx = "[^0-9]";
        List<String> collect = headers.stream()
                .sorted((x1, x2) -> {
                    Pattern p = Pattern.compile(regEx);
                    Matcher m1 = p.matcher(x1);
                    Matcher m2 = p.matcher(x2);
                    String trim1 = m1.replaceAll("").trim();
                    String trim2 = m2.replaceAll("").trim();
                    return Integer.valueOf(trim1).compareTo(Integer.valueOf(trim2));
                })
                .distinct()
                .collect(Collectors.toList());
        return collect;
    }


    @Test
    public void fff() throws IOException {
        String path = "D:/重生药王1.txt";
        readFile(path);
    }
}
