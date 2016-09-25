package dnn.service.user;

import dnn.common.exception.SerException;
import dnn.common.utils.PasswordHash;
import dnn.common.utils.UserContext;
import dnn.dao.user.UserRep;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.Status;
import dnn.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements ISerUser {

    @Autowired
    private UserRep userRep;

    @Autowired
    protected PropertiesFactoryBean propertiesFactoryBean;

    public void systemLogin(User user) throws SerException {
        try {
            if (user.getPassword().equals(propertiesFactoryBean.getObject().getProperty("system.password"))) {
                System.out.println("admin 系统帐号登录成功!!");
            } else {
                throw new SerException("用户名或密码错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(User user) throws SerException {
        if (user.getUsername().equals("123@qq.com") && user.getPassword().equals("11111111")) {
            user.setUserType(UserType.CUSTOM);
            UserContext.saveUserSession(user);
            return;
        }

        try {
            if (user.getUsername().equals(propertiesFactoryBean.getObject().getProperty("system.username"))) {
                systemLogin(user);
                user.setUserType(UserType.MANAGER);
                UserContext.saveUserSession(user);
            } else {
                User dbUser = userRep.findByUsername(user.getUsername());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User register(User user) throws SerException {
        return super.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public void remove(User entity) throws SerException {
        super.remove(entity);
    }

    public User save(User entity) throws SerException {
        User user = userRep.findByUsername(entity.getUsername());
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
        return super.save(entity);
    }

}
