package dnn.service.user;

import dnn.common.exception.SerException;
import dnn.common.utils.PasswordHash;
import dnn.dao.user.IUserDao;
import dnn.entity.user.User;
import dnn.service.ServiceImpl;
import dnn.service.user.session.TokenUtils;
import dnn.service.user.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Service
public class UserSerImpl extends ServiceImpl<User> implements ISerUser {

    @Autowired
    protected IUserDao userDao;

    @Override
    public String login(User user) throws SerException {
        User dbUser = userDao.findByName(user.getUsername());
        if(null!=dbUser){
            try {
                if(!PasswordHash.validatePassword(user.getPassword(),dbUser.getPassword())){
                    throw new SerException("用户名或密码错误");
                }
                UserSession.removeByUsername(user.getUsername());
                String token = TokenUtils.create("192.168.0.1",user.getUsername());
                UserSession.put(token,user);
                return token;
            } catch (SerException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        throw new SerException("用户名或密码错误");
    }
}
