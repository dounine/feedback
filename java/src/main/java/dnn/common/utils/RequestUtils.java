package dnn.common.utils;

import dnn.common.request.RequestContext;
import dnn.entity.user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huanghuanlai on 16/9/4.
 */
public class RequestUtils {

    public static Cookie getUserTokenCookie(){
        return getUserTokenCookie(RequestContext.get());
    }

    public static final Cookie getUserTokenCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if(null!=cookies){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(User.TOKEN_NAME)){
                    tokenCookie = cookie;
                    break;
                }
            }
        }
        return tokenCookie;
    }

}
