package com.yayiabc.http.mvc.service.Impl;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.URLUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.UploadFile;
import com.yayiabc.http.mvc.service.VideoScreenPicService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class VideoScreenPicServiceImpl implements VideoScreenPicService {
    private Logger logger= LogManager.getLogger(VideoScreenPicServiceImpl.class);
    /**
     * 七牛的视频截图
     *
     * @param fileName
     *            要截图文件名称
     * @param format
     *            截图的类型(jpg.png)
     * @uesr "xinzhifu@knet.cn"
     * @date 2016年11月21日下午2:43:42
     */
    public String qiNiuMediaPrtScreen(String fileName, String format) {

        String screenPic = "";
        long startTime = System.currentTimeMillis();// 获取当前时间
        // 身份验证
        Auth auth = Auth.create(UploadFile.accessKey,UploadFile.secretKey);
        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);
        // 新建一个OperationManager对象
        OperationManager operater = new OperationManager(auth, c);
        // 设置要截图的空间和key，并且这个key在你空间中存在(key就是文件的名字)
        String bucket = UploadFile.bucket;
        String key = fileName;
        // 设置截图操作参数
        String fops = "vframe/" + format + "/offset/1/w/640/h/480/rotate/auto";
        // 设置截图的队列
        String pipeline = UploadFile.bucket;
        // 可以对截图后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        int index=fileName.indexOf(".");
        String str=fileName;
        if(index!=-1){
            str = fileName.substring(0, fileName.indexOf("."));
        }
        String urlbase64 = UrlSafeBase64.encodeToString(UploadFile.bucket + ":" + str
                + "." + format);
        String pfops = fops + "|saveas/" + urlbase64;
        // 设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true);
        try {
            String persistid = operater.pfop(bucket, key, pfops, params);

            screenPic = this.getFileResourceUrl(str + "." + format);
            logger.info("视频截图成功.[persistid={}]"+ persistid);
            logger.info("截图成功");
        } catch (QiniuException e) {
            Response r = e.response;// 捕获异常信息
            logger.info(r.toString());// 请求失败时简单状态信息
            try {
                logger.info(r.bodyString());// 响应的文本信息
            } catch (QiniuException e1) {
                logger.error(e1.getMessage());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        logger.info("截取视频截图用时：" + (endTime - startTime) + "ms");

        return screenPic;
    }

    public String getFileResourceUrl(String filename) throws Exception {
        String downloadUrl = "";
        if (filename != null) {
            Mac mac = getMac();
            String baseUrl = URLUtils.makeBaseUrl(UploadFile.domainOfBucket, filename);
            GetPolicy getPolicy = new GetPolicy();
            downloadUrl = getPolicy.makeRequest(baseUrl, mac);
        }
        return downloadUrl;
    }

    /**
     * 操作许可
     *
     * @uesr "xinzhifu@knet.cn"
     * @date 2016年11月19日下午3:27:01
     */
    private Mac getMac() {
        Mac mac = new Mac(UploadFile.accessKey, UploadFile.secretKey);
        return mac;
    }

    @RequestMapping("getDownLoadUrl")
    @ResponseBody
    public DataWrapper<Void> getDownLoadUrl(
            @RequestParam("fileName") String fileName,
            @RequestParam("format") String format){
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        String url=qiNiuMediaPrtScreen(fileName,format);
        dataWrapper.setMsg(url);
        return dataWrapper;
    }
}
