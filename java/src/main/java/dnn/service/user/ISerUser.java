package dnn.service.user;

import dnn.common.exception.SerException;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.service.IService;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface ISerUser extends IService<User,UserDto>{

    void login(User user) throws SerException;

    User register(User user) throws SerException;

    User findByUsername(String username) throws SerException;

}
