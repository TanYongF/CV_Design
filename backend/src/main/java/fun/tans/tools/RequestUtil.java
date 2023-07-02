package fun.tans.tools;

import fun.tans.service.UserService;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Describe: Cookie工具类
 * @Author: tyf
 * @CreateTime: 2022/4/21
 **/
public class RequestUtil {
    /**
     * 获取对应的用户Cookie值
     *
     * @param request    请求
     * @param cookieName cookie名称
     * @return 值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    /**
     * 从请求中获取token 1.从cookie中获取 2.从header中获取
     * @param request http请求对象
     * @return
     */
    public static String getTokenByRequest(HttpServletRequest request) {
        String headerToken = request.getHeader(UserService.COOKIE_NAME_TOKEN);
        String cookieToken = RequestUtil.getCookieValue(request, UserService.COOKIE_NAME_TOKEN);

        //token为空，那么该用户未登录，返回null
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(headerToken)) {
            return null;
        }
        return StringUtils.isEmpty(headerToken) ? cookieToken : headerToken;
    }


}