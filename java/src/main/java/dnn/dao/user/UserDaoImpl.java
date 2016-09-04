package dnn.dao.user;

import dnn.common.dto.user.UserDto;
import dnn.common.utils.PasswordHash;
import dnn.dao.DaoImpl;
import dnn.entity.user.User;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
        user.setPassword("526cb1993b17402a71f88b46120037447a086f93b6e0d554:786987fa3055c393d0b07affc6a67bc6058bc8a7501be528");
        return user;
    }

    public static void main(String args[]) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(PasswordHash.createHash("admin123"));
    }
}
