package com.humorboy.practise.utils;


import com.humorboy.practise.annotation.Column;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by root on 17-5-31.
 */
public class ExcelUtils {
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();//设置表头字段的位置，确定对象值该写到正确的单元格中

    private ExcelUtils(){}

    public static ExcelUtils getInstance(){
        return new ExcelUtils();
    }

    public <T> void  importExcel(List<T> t) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        List<String> header = getHeader(t.get(0).getClass());
        //设置表头
        for (int i = 0; i < header.size(); i++) {
            map.put(header.get(i), i);//
            Cell cell = row.createCell(i);
            cell.setCellValue(header.get(i));
        }
        //填充数据
        int i = 1;
        for (T t1 : t) {
            Row row1 = sheet.createRow(i);
            setValueRow(row1, t1);
            i++;
        }
        writeFile(workbook);
    }

    /**
     * 循环设置行数据
     * @param r 行
     * @param t 填充行数据
     * @param <T> 泛型数据
     * @throws Exception
     */
    private <T> void setValueRow(Row r, T t) throws Exception {
        Field[] fields = t.getClass().getDeclaredFields();//暴力字段名字
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);//获取字段注解
            // 如果注解为null则是不是表头字段
            if (column != null){
                //执行getter方法 getXsss()
                Method method = t.getClass().getMethod("get" + StringUtils.capitalize(field.getName()));
                Object o = method.invoke(t);
                r.createCell(map.get(column.value())).setCellValue(o.toString());
            }
        }
    }

    /**
     * 获取表头数据，来源于对象注解Column
     * @param clazz 对象类型
     * @return
     * @throws Exception
     */
    private List<String> getHeader(Class clazz) throws Exception {
        List<String> list = new ArrayList<String>();
        //TODO 待优化
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    map.put(column.value(), i++);
                    list.add(column.value());
                }
            }
        }
        return list;
    }

    /**
     * 获取对象上字段的值
     * @param f
     * @param t
     * @return
     * @throws Exception
     */
    private <T> Object getFieldValue(Field f,T t) throws Exception {
        Column column = f.getAnnotation(Column.class);
        if (column != null) {
            Method method = t.getClass().getMethod("get" + StringUtils.capitalize(f.getName()));
            return method.invoke(t);
        }
        return null;
    }

    /**
     * 文件写出方法
     * @param wb
     * @throws IOException
     */
    private void writeFile(Workbook wb) throws IOException {
        Random random = new Random(System.currentTimeMillis());
        FileOutputStream outputStream = new FileOutputStream(new File(random.toString() + ".cvs"));
        wb.write(outputStream);
        outputStream.close();
    }
}
