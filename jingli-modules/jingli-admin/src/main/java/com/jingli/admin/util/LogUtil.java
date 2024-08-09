
package com.jingli.admin.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {


    public static String SIGN = "   ";
    private static String FROM_REQUEST = "Request";
    private static String FROM_RESPONSE = "Response";

    public static void logRequest(String url, String ip, String param) {
        StringBuffer sb = new StringBuffer();
        /*if (url.endsWith(".css") || url.endsWith(".js")
                || url.endsWith(".png") || url.endsWith(".html")
                    || url.endsWith(".gif")|| url.endsWith(".jpg")) {
            return;
        }*/
        if (filterUrl(url)) {
            return;
        }
        sb.append("From=").append(FROM_REQUEST).append(SIGN);
        sb.append("Uri=").append(url).append(SIGN);
        sb.append("ip=").append(ip).append(SIGN);
        sb.append("Param=").append(param).append(SIGN);
        log.info(sb.toString());
    }


    public static void logResponse(String url, String content) {
        StringBuffer sb = new StringBuffer();
        /*if (content.contains("<head>")) {
            return ;
        }*/
        if (filterUrl(url)) {
            return;
        }

        if (content.length() > 1000) {
            content = content.substring(0, 1000);
            content += "......";
        }
        sb.append("From=").append(FROM_RESPONSE).append(SIGN);
        sb.append("Content=").append(content).append(System.getProperty("line.separator"));
        log.info(sb.toString());
    }

    //|| url.endsWith(".html")
    private static boolean filterUrl(String url) {
        if (url.contains("/lib/") || url.contains("/css/")
                || url.contains("/js/") || url.contains("/img/")
                || url.endsWith(".html")) {
            return true;
        }
        return false;
    }
}
