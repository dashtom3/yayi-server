package com.yayiabc.common.weixin;

/**
 * Created by 小月亮 on 2017/9/4.
 */
public class WXAppPayConfigImpl extends WXPayConfig{
    private byte[] certData;
    private static WXAppPayConfigImpl INSTANCE;

    /*private WXPayConfigImpl() throws Exception{
        String certPath = "D://CERT/common/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }*/


    //线程安全的单例模式
    public static WXAppPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXAppPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXAppPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return "wx983825eaeef912b7";
    }
    /*public String getAppID() {
        return "wx4b1a6fde77626a32";
    }*/

    /*public String getMchID() {
        return "1377180402";
    }*/
    public String getMchID() {
        return "1292687201";
    }

    /*public String getKey() {
        return "d3592e6d6642995950d3451c1ea36607";
    }*/
    public String getKey() {
        return "xiaojiangxiaojiangxiaojiangjiang";
    }

  /*  public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }*/


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
