package com.yayiabc.common.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GetQCode {
	public static void getqCode(String url,HttpServletResponse response) throws IOException{
		if(url!=null&&!"".equals(url)){
			ServletOutputStream stream =null;
			try {
				int width=200;
				int height=200;
				stream=response.getOutputStream();
				QRCodeWriter writer=new QRCodeWriter();
				BitMatrix m=writer.encode(url, BarcodeFormat.QR_CODE,height,width);
				MatrixToImageWriter.writeToStream(m,"png", stream);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(stream!=null){
					stream.flush();
					stream.close();
				}
			}
		}
	}
	
}
