package com.yayiabc.http.mvc.pojo.jpa;

/**
 * Created by 小月亮 on 2017/9/4.
 */
public class WXAppEntry {
    private String appid;
    private Long timestamp;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public WXAppEntry(String appid, Long timestamp, String partnerid, String prepayid, String noncestr, String sign) {
        this.appid = appid;
        this.timestamp = timestamp;
        this.partnerid = partnerid;
        this.prepayid = prepayid;
        this.noncestr = noncestr;
        this.sign = sign;
    }

    public WXAppEntry() {
    }

    @Override
    public String toString() {
        return "WXAppEntry{" +
                "appid='" + appid + '\'' +
                ", timestamp=" + timestamp +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
