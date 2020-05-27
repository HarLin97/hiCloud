package com.fuchangling.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 获取客户端信息
 *
 * @author Harlin
 */
public class ClientInfoUtil {

    private final static String UNKNOWN = "unknown";

    /**
     * 获取客户端ip
     * 先从各代理请求头中找
     * 处理本级ip
     * 2018年8月23日
     *
     * @param request
     * @return IP
     * author:wangzhen
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddr());
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr() + "";
            //如果是本机地址
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;

    }


    /**
     * 获取客户端操作系统
     * 2018年8月23日
     *
     * @param request
     * @return author:wangzhen
     */
    public static String getClientOS(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String cos = "unknow os";
        Pattern p = compile(".*(Windows NT 6\\.1).*");
        Matcher m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Windows7";
            return cos;
        }
        p = compile(".*(Windows NT 10\\.0).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Windows10";
            return cos;
        }
        p = compile(".*(Windows NT 5\\.1|Windows XP).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "WindowsXP";
            return cos;
        }
        p = compile(".*(Windows NT 5\\.2).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Windows2003";
            return cos;
        }
        p = compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Windows2000";
            return cos;
        }
        p = compile(".*(Mac|apple|MacOS8).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "MAC";
            return cos;
        }
        p = compile(".*(WinNT|WindowsNT).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "WindowsNT";
            return cos;
        }
        p = compile(".*Linux.*");
        if (m.find()) {
            cos = "Linux";
            return cos;
        }
        p = compile(".*68k|68000.*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Mac68k";
            return cos;
        }
        p = compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");
        m = p.matcher(userAgent);
        if (m.find()) {
            cos = "Windows9x";
            return cos;
        }
        return cos;
    }

    /**
     * 获取客户端浏览器
     * 2018年8月23日
     *
     * @param request
     * @return author:wangzhen
     */
    public static String getClientBrowser(HttpServletRequest request) {
        String agent = request.getHeader("user-agent").toLowerCase();
        if (agent.indexOf("msie 7") > 0) {
            return "IE7";
        } else if (agent.indexOf("msie 8") > 0) {
            return "IE8";
        } else if (agent.indexOf("msie 9") > 0) {
            return "IE9";
        } else if (agent.indexOf("msie 10") > 0) {
            return "IE10";
        } else if (agent.indexOf("msie") > 0) {
            return "IE";
        } else if (agent.indexOf("opera") > 0) {
            return "Opera";
        } else if (agent.indexOf("opera") > 0) {
            return "Opera";
        } else if (agent.indexOf("firefox") > 0) {
            return "Firefox";
        } else if (agent.indexOf("webkit") > 0) {
            return "Webkit";
        } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
            return "IE11";
        } else {
            return "Other Browser";
        }
    }

}
