package com.yayiabc.common.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

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

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return null;
    }
}
