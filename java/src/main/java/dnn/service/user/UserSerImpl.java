package dnn.service.user;

import dnn.common.dto.user.UserDto;
import dnn.common.exception.SerException;
import dnn.common.utils.IpUtils;
import dnn.common.utils.PasswordHash;
import dnn.dao.user.IUserDao;
import dnn.entity.user.User;
import dnn.service.ServiceImpl;
import dnn.service.user.session.Online;
import dnn.service.user.session.TokenUtils;
import dnn.service.user.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Service
public class UserSerImpl extends ServiceImpl<User,UserDto> implements ISerUser {

    @Autowired
    protected IUserDao userDao;

    @Override
    public String login(User user) throws SerException {
        User dbUser = userDao.findByName(user.getUsername());
        if(null!=dbUser){
            try {
                if(PasswordHash.validatePassword(user.getPassword(),dbUser.getPassword())){
                    UserSession.removeByUsername(user.getUsername());
                    String token = TokenUtils.create(IpUtils.getRequestIp(),user.getUsername());

                    Online online = new Online();
                    online.setLastAccessTime(LocalDateTime.now());
                    online.setId(dbUser.getId());
                    online.setUsername(user.getUsername());
                    online.setLoginTime(LocalDateTime.now());
                    online.setToken(token);

                    UserSession.put(token,online);

                    return token;
                }
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

    @Override
    public Map<String, Object> listOnline() {
        Map<String,Object> online = new HashMap<>();
        online.put("total",UserSession.count());
        online.put("rows",UserSession.sessions());
        return online;
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public void save(User entity) throws SerException {
        User user = userDao.findByName(entity.getUsername());
        if(null!=user){
            throw new SerException(entity.getUsername()+" 用户已经存在");
        }
        try {
            entity.setPassword(PasswordHash.createHash(entity.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        super.save(entity);
    }
}
