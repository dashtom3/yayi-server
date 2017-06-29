package com.yayiabc.common.enums;

import java.io.Serializable;

/**
 * Created by TIANCHENGYUAN103 on 2015-12-04.
 */
public enum DataEnum implements Serializable {

    No_Exception("正常", 0),
    Exception("数据异常", 1);

    private String label;
    private Integer code;

    DataEnum() {
    }

    DataEnum(String label, Integer code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return label;
    }

    public static DataEnum parse(int code) {
        for (DataEnum theEnum : DataEnum.values()) {
            if (theEnum.getCode() == code) {
                return theEnum;
            }
        }
        return No_Exception;
    }
}
