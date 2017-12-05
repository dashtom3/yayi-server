package com.yayiabc.http.filter;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyServletOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream bos = null ;
    public MyServletOutputStream (ByteArrayOutputStream bos) {
                 this.bos = bos ;
             }
     public void write(int b) throws IOException {
                 bos.write(b) ;
     }
}
