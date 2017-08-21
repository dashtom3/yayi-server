package com.yayiabc.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadFile {
	public static String getUpToken(){
		String accessKey = "2EkHs4sPHlelB-JYR5WuDp3jp9spsqyxIkluejva";
		String secretKey = "RniQyKlZ3hpLmS8rp-OhVHtUwkSqQiDFMi6TAF6g";
		String bucket = "yayi";

		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);
		return upToken;
	}
	public static String getUrl(String fileName) throws UnsupportedEncodingException{
		String domainOfBucket = "https://portal.qiniu.com/bucket/yayi";
		String encodedFileName = URLEncoder.encode(fileName, "utf-8");
		String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		return finalUrl;
		
	}
	public static void main(String[] args) {
		String accessKey = "PNswrKsxn6TET-MJ8fSXkcoZ0A8Jt5xDVktE5cp6";
		String secretKey = "ihJh_FJZ4_vM2OHW1OL_Cxfhis9QWSo7eGwChCN2";
		String bucket = "yayi";

		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("callbackUrl", "http://123.56.220.72:8089/api/file/callback");
		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);
	}
	

}


