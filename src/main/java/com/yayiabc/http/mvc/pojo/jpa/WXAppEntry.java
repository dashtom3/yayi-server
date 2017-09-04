package com.yayiabc.http.mvc.pojo.jpa;

/**
 * Created by 小月亮 on 2017/9/4.
 */
public class WXAppEntry {
    private String appId;
    private String timestamp;
    private String partnerid;
    private String prepayid;
    private String nonceStr;
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public WXAppEntry(String appId, String timestamp, String partnerid, String prepayid, String nonceStr, String signature) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.partnerid = partnerid;
        this.prepayid = prepayid;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

    public WXAppEntry() {
    }

    @Override
    public String toString() {
        return "WXAppEntry{" +
                "appId='" + appId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
