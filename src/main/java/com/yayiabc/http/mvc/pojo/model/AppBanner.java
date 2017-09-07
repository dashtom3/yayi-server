package com.yayiabc.http.mvc.pojo.model;

/**
 * Created by 小月亮 on 2017/9/5.
 */
public class AppBanner {
    private Integer appBannerId;
    private String appBannerName;
    private String appBannerAddress;
    private String appBannerType;

    public Integer getAppBannerId() {
        return appBannerId;
    }

    public void setAppBannerId(Integer appBannerId) {
        this.appBannerId = appBannerId;
    }

    public String getAppBannerName() {
        return appBannerName;
    }

    public void setAppBannerName(String appBannerName) {
        this.appBannerName = appBannerName;
    }

    public String getAppBannerAddress() {
        return appBannerAddress;
    }

    public void setAppBannerAddress(String appBannerAddress) {
        this.appBannerAddress = appBannerAddress;
    }

    public String getAppBannerType() {
        return appBannerType;
    }

    public void setAppBannerType(String appBannerType) {
        this.appBannerType = appBannerType;
    }

    public AppBanner(Integer appBannerId, String appBannerName, String appBannerAddress, String appBannerType) {
        this.appBannerId = appBannerId;
        this.appBannerName = appBannerName;
        this.appBannerAddress = appBannerAddress;
        this.appBannerType = appBannerType;
    }

    public AppBanner() {
    }

    @Override
    public String toString() {
        return "AppBanner{" +
                "appBannerId=" + appBannerId +
                ", appBannerName='" + appBannerName + '\'' +
                ", appBannerAddress='" + appBannerAddress + '\'' +
                ", appBannerType='" + appBannerType + '\'' +
                '}';
    }
}
