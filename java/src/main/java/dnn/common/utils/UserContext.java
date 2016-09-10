package dnn.common.utils;

import dnn.common.exception.SerException;
import dnn.common.request.RequestContext;
import dnn.entity.user.User;
import dnn.service.user.session.Online;
import dnn.service.user.session.UserSession;

import javax.servlet.http.Cookie;

/**
 * Created by huanghuanlai on 16/9/10.
 */
public final class UserContext {
    private UserContext(){}

    /**
     * 获取当前登录的用户部分信息(id,username,userType,accessTime)
     * @return 用户信息
     */
    public static final User currentUser() throws SerException {
        Cookie cookie = RequestUtils.getUserTokenCookie(RequestContext.get());
        if(null==cookie){
            throw new SerException("cookie中不存在token值");
        }
        Online online = UserSession.findByToken(cookie.getValue());
        if(null!=online){
            User user = new User();
            user.setAccessTime(online.getLastAccessTime());
            user.setUsername(online.getUsername());
            user.setId(online.getId());
            user.setUserType(online.getUserType());
            return user;
        }
        throw new SerException("请登录后再操作");
    }
}
