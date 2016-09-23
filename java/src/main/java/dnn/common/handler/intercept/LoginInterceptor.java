package dnn.common.handler.intercept;

import dnn.common.utils.UserContext;
import dnn.entity.user.UserType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huanghuanlai on 16/9/4.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {"/login","/register","/captcha"};

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
        boolean isLogin = UserContext.isLogin();
        if (!flag) {
            if(isLogin){
                UserType userType = UserContext.currentUser().getUserType();
                if(UserType.CUSTOM.equals(userType)&&url.startsWith("/admin")){
                    response.sendRedirect("/404");
                }else if(UserType.MANAGER.equals(userType)&&url.startsWith("/customer")){
                    response.sendRedirect("/404");
                }else{
                    flag = true;
                }
            }else{
                response.sendRedirect("/login");
            }
        }else{
            if(isLogin){
                    //UserSession.update(tokenCookie.getValue());//更新会话
                UserType userType = UserContext.currentUser().getUserType();
                if(!"/login".equals(url)){//除登录请求,其余请求全部拦截
                    if(UserType.CUSTOM.equals(userType)){
                        response.sendRedirect("/customer");
                    }else{
                        response.sendRedirect("/admin");
                    }
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
