package com.yayiabc.common.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * json日期的转换器
 */
public class JsonDateValueProcessor implements JsonValueProcessor{

    private String datePattern = "yyyy-MM-dd hh:mm:ss";

    public JsonDateValueProcessor() {
    }

    public JsonDateValueProcessor(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    public Object process(Object value){
        try {
            if(value instanceof Date){
                SimpleDateFormat sdf=new SimpleDateFormat(datePattern, Locale.UK);
                return sdf.format((Date)value);
            }
            return value==null?"":value.toString();
        }catch (Exception e){
            return "";
        }
    }
}
