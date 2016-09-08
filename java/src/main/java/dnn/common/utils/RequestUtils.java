package dnn.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huanghuanlai on 16/9/4.
 */
public class RequestUtils {

    public static Cookie getUserTokenCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if(null!=cookies){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    tokenCookie = cookie;
                    break;
                }
            }
        }
        return tokenCookie;
    }

}
