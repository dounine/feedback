package dnn.common.handler.intercept;

import dnn.common.utils.RequestUtils;
import dnn.entity.user.UserType;
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

    private static final String[] IGNORE_URI = {"/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURI().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        Cookie tokenCookie = RequestUtils.getUserTokenCookie(request);
        if (!flag) {
            if(null!=tokenCookie&& StringUtils.isNotBlank(tokenCookie.getValue())){
                if(UserSession.verify(tokenCookie.getValue())){//验证令牌的正确性
                    UserType userType = UserSession.findByToken(tokenCookie.getValue()).getUserType();
                    if(UserType.CUSTOM.equals(userType)&&url.startsWith("/admin")){
                        response.sendRedirect("/404");
                    }else if(UserType.MANAGER.equals(userType)&&url.startsWith("/custom")){
                        response.sendRedirect("/404");
                    }else{
                        UserSession.update(tokenCookie.getValue());//更新会话
                        flag = true;
                    }
                }else{
                    tokenCookie.setMaxAge(0);//清除验证失败cookie
                    response.addCookie(tokenCookie);
                    response.sendRedirect("/login");
                }
            }else{
                response.sendRedirect("/login");
            }
        }else{
            if(null!=tokenCookie&& StringUtils.isNotBlank(tokenCookie.getValue())){
                if(UserSession.verify(tokenCookie.getValue())){//验证令牌的正确性
                    UserSession.update(tokenCookie.getValue());//更新会话
                    UserType userType = UserSession.findByToken(tokenCookie.getValue()).getUserType();
                    if(UserType.CUSTOM.equals(userType)){
                        response.sendRedirect("/custom");
                    }else{
                        response.sendRedirect("/admin");
                    }
                }else{
                    tokenCookie.setMaxAge(0);//清除验证失败cookie
                    response.addCookie(tokenCookie);
                }
            }
        }
        return flag;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
