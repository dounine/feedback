package dnn.common.handler.intercept;

import dnn.service.user.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huanghuanlai on 16/9/4.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if(null!=cookies){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    tokenCookie = cookie;
                    break;
                }
            }
            if(null!=tokenCookie&&StringUtils.isNotBlank(tokenCookie.getValue())){
                if(UserSession.verify(tokenCookie.getValue())){//验证令牌的正确性
                    UserSession.update(tokenCookie.getValue());//更新会话
                    return true;
                }else{
                    tokenCookie.setMaxAge(0);//清除验证失败cookie
                    response.addCookie(tokenCookie);
                }
            }
        }
        if(request.getRequestURI().equals("/login")){
            request.setAttribute("login",true);
            return true;
        }
        response.sendRedirect("login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(request.getRequestURI().equals("/login")&&null==request.getAttribute("login")){
            response.sendRedirect("/admin");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
