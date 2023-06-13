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
     * @param request 请求
     * @return token
     */
    public static String getTokenByRequest(HttpServletRequest request) {
        String paramToken = request.getParameter(UserService.COOKIE_NAME_TOKEN);
        String cookieToken = RequestUtil.getCookieValue(request, UserService.COOKIE_NAME_TOKEN);

        //token为空，那么该用户未登录，返回null
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        return StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
    }


}