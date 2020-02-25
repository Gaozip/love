package org.lizhishu.love.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: gzip
 * @Date: 2019/12/7  23:53
 * @Description:
 **/
@Component
public class WebUtil {

    public static boolean equalsNull(Object value) {
        if ((value != null) && (!value.equals("")) && (!value.equals("null"))) {
            return false;
        }
        return true;
    }

    public static boolean equalsListNull(List<?> list) {
        if ((list != null) && (list.size() > 0)) {
            return false;
        }
        return true;
    }

    public static String getIp(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException var7) {
        }

        String ip = request.getHeader("X-Forwarder-For");
        if (!Strings.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            String[] ipArr = ip.split(",");
            String[] var6 = ipArr;
            int var5 = ipArr.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String IP = var6[var4];
                if (!"unknown".equalsIgnoreCase(IP)) {
                    ip = IP;
                    break;
                }
            }
        } else {
            ip = request.getHeader("Proxy-Client-Ip");
            if (Strings.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-Ip");
            }
        }

        if (Strings.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "0.0.0.1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }
}
