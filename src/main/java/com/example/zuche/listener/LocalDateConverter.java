package com.example.zuche.listener;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.example.zuche.utils.LocalDateTimeUtils;

import java.time.LocalDate;

public class LocalDateConverter implements Converter {


    public CellData convertToExcelData(LocalDate localDate) {

        CellData cellData = new CellData();

        cellData.setData(LocalDateTimeUtils.localDateToDate(localDate));
        return cellData;
    }


}
