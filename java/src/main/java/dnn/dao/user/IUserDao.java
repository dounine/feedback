package dnn.dao.user;

import dnn.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IUserDao extends JpaRepository<User,String> {

    User findByUsername(String username);

}
