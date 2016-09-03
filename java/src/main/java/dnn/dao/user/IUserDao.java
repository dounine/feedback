package dnn.dao.user;

import dnn.dao.IDao;
import dnn.entity.user.User;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IUserDao extends IDao<User>{

    User findByName(String username);

}
