package com.yayiabc.http.fr;

import com.yayiabc.http.mvc.controller.unionpay.sdk.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 防盗链
 */
public class Dodo implements Filter {

    //private static Logger logger = LoggerFactory.getLogger(Dodo.class);
    // 限制访问地址列表正则
    private static List<Pattern> urlLimit = new ArrayList<Pattern>();
    // 允许访问列表
    private static List<String> urlAllow = new ArrayList<String>();
    // 错误地址列表
    private static String urlError = "";

    // 必须过Filter的请求
    protected boolean shouldBeFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getServletPath();

        for (int i = 0; i < urlLimit.size(); i++) {
            Matcher m = urlLimit.get(i).matcher(path);
            if (m.matches()) {
                LogUtil.writeLog("当前的Path为{" + path + "}必须进行过滤");
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (null == httpRequest || null == httpResponse) {
            return;
        }
        // 放行不符合拦截正则的Path
        if (!shouldBeFilter(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }

        String requestHeader = httpRequest.getHeader("referer");
        if (null == requestHeader) {
            httpResponse.sendRedirect(urlError);
            return;
        }
        for (int i = 0; i < urlAllow.size(); i++) {
            if (requestHeader.startsWith(urlAllow.get(i))) {
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }
        httpResponse.sendRedirect(urlError);
        return;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
        LogUtil.writeLog("防盗链配置开始...");
        String filename;
        try {
            filename = fc.getServletContext().getRealPath(
                    "/WEB-INF/preventLink.properties");
            File f = new File(filename);
            InputStream is = new FileInputStream(f);
            Properties pp = new Properties();
            pp.load(is);
            // 限制访问的地址正则
            String limit = pp.getProperty("url.limit");
            // 解析字符串，变成正则，放在urlLimit列表中
            parseRegx(limit);
            // 不受限的请求头
            String allow = pp.getProperty("url.allow");
            // 将所有允许访问的请求头放在urlAllow列表中
            urlAllow = parseStr(urlAllow, allow);
            urlError = pp.getProperty("url.error");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseRegx(String str) {
        if (null != str) {
            String[] spl = str.split(",");
            if (null != spl) {
                for (int i = 0; i < spl.length; i++) {
                    Pattern p = Pattern.compile(spl[i].trim());
                    urlLimit.add(p);
                }
            }
        }

    }
    private List<String> parseStr(List<String> li, String str) {
        if (null == str || "".equals(str.trim())) {
            return null;
        }
        String[] spl = str.split(",");
        if (null != spl && spl.length > 0) {
            li = Arrays.asList(spl);
        }
        return li;
    }
}
