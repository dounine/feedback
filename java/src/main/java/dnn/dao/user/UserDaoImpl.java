package dnn.dao.user;

import dnn.dto.user.UserDto;
import dnn.common.utils.PasswordHash;
import dnn.dao.DaoImpl;
import dnn.entity.user.User;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@Repository
public class UserDaoImpl extends DaoImpl<User,UserDto> implements IUserDao {

    @Override
    public User findByName(String username) {
        Map<String,Object> keys = new HashMap<>();
        keys.put("username",username);
        List<User> users = findByCis(keys);
        if(null!=users&&users.size()>0){
            return users.get(0);
        }
        return null;
    }

    public static void main(String args[]) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(PasswordHash.createHash("admin123"));
    }
}
