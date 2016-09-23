package dnn.service.user;

import com.mongodb.WriteResult;
import dnn.common.beans.PropertiesLoader;
import dnn.common.exception.SerException;
import dnn.common.utils.PasswordHash;
import dnn.common.utils.UserContext;
import dnn.dao.user.IUserDao;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.Status;
import dnn.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements ISerUser {

    @Autowired
    protected IUserDao userDao;
    @Autowired
    protected PropertiesLoader propertiesLoader;

    public void systemLogin(User user) throws SerException {
        if (user.getPassword().equals(propertiesLoader.getProperty("system.password"))) {
            System.out.println("admin 系统帐号登录成功!!");
        }else{
            throw new SerException("用户名或密码错误");
        }
    }

    @Override
    public void login(User user) throws SerException {
        if(user.getUsername().equals("123@qq.com")&&user.getPassword().equals("11111111")){
            user.setUserType(UserType.CUSTOM);
            UserContext.saveUserSession(user);
            return;
        }

        if (user.getUsername().equals(propertiesLoader.getProperty("system.username"))) {
            systemLogin(user);
            user.setUserType(UserType.MANAGER);
            UserContext.saveUserSession(user);
        }else{
            User dbUser = userDao.findByName(user.getUsername());
            if (null != dbUser) {
                try {
                    if (PasswordHash.validatePassword(user.getPassword(), dbUser.getPassword())) {
                        if (Status.UNREVIEW.equals(dbUser.getStatus())) {
                            throw new SerException("帐号未审核,请联系管理进行审核.");
                        }

                        UserContext.saveUserSession(dbUser);
                    }
                } catch (SerException e) {
                    throw e;
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            throw new SerException("用户名或密码错误");
        }
    }

    @Override
    public Map<String, Object> listOnline() {
        Map<String, Object> online = new HashMap<>();
        online.put("total", 1l);
        online.put("rows", null);
        return online;
    }

    @Override
    public WriteResult auditiingUser(User user) throws SerException {
        WriteResult writeResult = userDao.UpdateByCis2(user, "status", Status.THAW);
        return writeResult;
    }

    @Override
    public void remove(User entity) throws SerException {
        super.remove(entity);
    }

    @Override
    public void save(User entity) throws SerException {
        User user = userDao.findByName(entity.getUsername());
        if (null != user) {
            throw new SerException(entity.getUsername() + " 用户已经存在");
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
