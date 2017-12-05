package com.yayiabc.http.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;



/**
 * Created by XiaoJiang01 on 2017/3/17.
 */

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type,token,admintoken,saletoken,date,Content-Encoding,server,connection,transfer-encoding");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        filterChain.doFilter(servletRequest,response);
        response.setHeader("Content-Type","application/json;charset=UTF-8,application/gzip");
        //创建HttpServletResponse 包装类的实例
        MyHttpServletResponse myResponse = new MyHttpServletResponse(response) ;
        filterChain.doFilter(servletRequest,myResponse);
        //GZIP压缩：
        byte[] buff = myResponse.getBufferedBytes() ;
        //创建缓存容器：
        ByteArrayOutputStream baos = new ByteArrayOutputStream() ;

        GZIPOutputStream gzip = new GZIPOutputStream(baos) ;

        gzip.write(buff) ;

        gzip.close() ;

        buff = baos.toByteArray() ;

        //设置响应头；
        response.setHeader("Content-Encoding", "gzip");
        response.setContentLength(buff.length) ;
        response.getOutputStream().write( buff) ;
    }

    @Override
    public void destroy() {

    }
}
