package com.yayiabc.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadFile {
	public static final String accessKey = "2EkHs4sPHlelB-JYR5WuDp3jp9spsqyxIkluejva";
	public static final String secretKey = "RniQyKlZ3hpLmS8rp-OhVHtUwkSqQiDFMi6TAF6g";
	public static final String bucket = "yayi";
	public static final String domainOfBucket = "https://portal.qiniu.com/bucket/yayi";
	public static String getUpToken(){
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);
		return upToken;
	}
	public static String getUrl(String fileName) throws UnsupportedEncodingException{
		String encodedFileName = URLEncoder.encode(fileName, "utf-8");
		String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		return finalUrl;
		
	}
	public static void main(String[] args) {


		Auth auth = Auth.create(accessKey, secretKey);
		StringMap putPolicy = new StringMap();
		putPolicy.put("callbackUrl", "http://123.56.220.72:8089/api/file/callback");
		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);
	}
	

}


