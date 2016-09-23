package dnn.dao.user;

import dnn.dto.user.UserDto;
import dnn.dao.IDao;
import dnn.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IUserDao extends JpaRepository<User,Integer> {

    User findByName(String username);

}
