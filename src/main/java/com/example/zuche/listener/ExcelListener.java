package com.example.zuche.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelListener<T> extends AnalysisEventListener<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelListener.class);
    private final Map<Integer, T> dataMap = new LinkedHashMap();
    private final Map<Integer, String> headData = new LinkedHashMap();
    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    public ExcelListener() {
    }

    @Override
    public void invoke(T object, AnalysisContext context) {
        Integer rowIndex = context.readRowHolder().getRowIndex() + 1;
        this.dataMap.put(rowIndex, object);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headData.putAll(headMap);
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        } else {
            try {
                Field[] var1 = object.getClass().getDeclaredFields();
                int var2 = var1.length;

                for (int var3 = 0; var3 < var2; ++var3) {
                    Field f = var1[var3];
                    f.setAccessible(true);
                    ExcelProperty property = (ExcelProperty) f.getAnnotation(ExcelProperty.class);
                    if (property != null && !"serialVersionUID".equals(f.getName()) && StringUtils.isNotBlank((String) f.get(object))) {
                        return false;
                    }
                }
            } catch (Exception var6) {
            }

            return true;
        }
    }

    public Map<Integer, T> getData() {
        return this.dataMap;
    }

    public Map<Integer, String> getHeadData() {
        return this.headData;
    }
}
