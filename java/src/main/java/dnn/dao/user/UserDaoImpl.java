package dnn.dao.user;

import dnn.common.dto.user.UserDto;
import dnn.dao.DaoImpl;
import dnn.entity.user.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Repository
public class UserDaoImpl extends DaoImpl<User,UserDto> implements IUserDao {

    @Override
    public User findByName(String username) {
        User user = new User();
        user.setId("123");
        user.setUsername("admin");
        user.setAccessTime(LocalDateTime.now());
        user.setPassword("admin123");
        return user;
    }
}
