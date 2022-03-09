package com.example.zuche.utils;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.zuche.pojo.ExportField;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc :  exsyexcel
 * @Author : chengzhang
 * @Date : 2022/1/18 16:13
 */
public class EasyExcelUtils {

    public EasyExcelUtils() {
    }

    //excel表头
    public static final List<List<String>> head(List<ExportField> exportFields) {
        List<List<String>> list = new ArrayList<>();

        for (ExportField exportField : exportFields
        ) {

            List<String> head = new ArrayList<>();
            head.add(exportField.getFieldDesc());
            list.add(head);
        }
        return list;
    }

    //要导出的字段数据
    public static final <T> List<List<T>> dataList(List<ExportField> exportFields, T obj) {

        List<List<T>> list = new ArrayList<>();
        List<T> data = new ArrayList<>();

        for (ExportField exportField : exportFields) {
            //先根据反射获取实体类的class对象
            Class objClass = obj.getClass();
            //设置实体类属性的集合
            Field[] fields = ReflectUtil.getFields(objClass);

            //循环实体类对象集合
            for (Field field : fields) {
                field.setAccessible(true);
                //判断实体类属性跟特定字段集合名是否一样
                if (field.getName().equals(exportField.getFieldName())) {

                    try {
                        T object = (T) field.get(obj);
                        //获取属性对应的值
                        data.add(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        list.add(data);
        return list;
    }

    //设置excel样式
    public static HorizontalCellStyleStrategy getStyleStrategy() {
        //头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        //背景设置为灰色 浅蓝色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);

        //字体样式
        headWriteFont.setFontName("Frozen");
        headWriteCellStyle.setWriteFont(headWriteFont);
        //自动换行
        headWriteCellStyle.setWrapped(false);

        //水平对齐方式
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //垂直对齐方式
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //锁定表头
        headWriteCellStyle.setLocked(true);
        //内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色，
        // 头默认了FillPatternType所以可以不指定contentWriteCellStyle.setFillPatternType(FillPatternType.SQUARES);
        //背景白色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();

        //字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        //字体样式
        contentWriteFont.setFontName("Song");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //这个策略是 头是头的样式  内容是内容的样式  其他的策略可以自己实现

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);


    }
}
