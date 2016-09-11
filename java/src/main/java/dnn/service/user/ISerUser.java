package dnn.service.user;

import com.mongodb.WriteResult;
import dnn.dto.user.UserDto;
import dnn.common.exception.SerException;
import dnn.entity.user.User;
import dnn.service.IService;

import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface ISerUser extends IService<User,UserDto>{

    String login(User user) throws SerException;

    Map<String,Object> listOnline();

    WriteResult auditiingUser(User user) throws SerException;

}
