package com.yayiabc.http.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyHttpServletResponse extends HttpServletResponseWrapper {
    //定义一个容器，用来存储Serlvet 处理完后response 写出的数据
     private ByteArrayOutputStream bos = new ByteArrayOutputStream()  ;
     private PrintWriter printWriter = null;
     public MyHttpServletResponse(HttpServletResponse response) {
                 super(response) ;
             }
     //处理字节流输出的情况
             @Override
             public ServletOutputStream getOutputStream() throws IOException {
                 return new MyServletOutputStream(bos);
            }

            //处理字符流输出的情况:用字符流时要注意乱码：字节转字符要查码表，字符转字节也要查码表
           @Override
           public PrintWriter getWriter() throws IOException {
                 printWriter  = new PrintWriter(new OutputStreamWriter(bos, super.getCharacterEncoding())) ;
                return printWriter;
            }
    //获取response 写出的数据
    public byte[] getBufferedBytes(){
             try {
                   if (printWriter != null) {
                       printWriter.close();
                   }
             bos.flush() ;
            } catch (IOException e) {
           e.printStackTrace();
         }
        byte[] byteArray = bos.toByteArray() ;
       return  byteArray;
         }
}
