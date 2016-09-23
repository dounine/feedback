package dnn.common.utils;

import dnn.common.request.RequestContext;
import dnn.entity.user.User;

/**
 * Created by huanghuanlai on 16/9/10.
 */
public final class UserContext {
    public static final String USER_SESSION = "user.session";
    private UserContext(){}

    /**
     * 获取当前登录的用户部分信息(id,username,userType,accessTime)
     * @return 用户信息
     */
    public static final User currentUser(){
        Object userSession = RequestContext.get().getSession().getAttribute(USER_SESSION);
        if(null==userSession){
            throw new RuntimeException("请登录后再操作");
        }
        return (User)userSession;
    }

    public static void saveUserSession(User user){
        RequestContext.get().getSession().setAttribute(USER_SESSION,user);
    }

    public static void removeUserSession(){
        RequestContext.get().getSession().removeAttribute(USER_SESSION);
    }

    public static boolean isLogin(){
        Object object = RequestContext.get().getSession().getAttribute(USER_SESSION);
        return object!=null;
    }
}
