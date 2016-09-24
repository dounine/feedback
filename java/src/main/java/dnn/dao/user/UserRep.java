package dnn.dao.user;

import dnn.dao.MyRep;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface UserRep extends MyRep<User,UserDto> {

    /**
     * 此处使用的是spring-data-jpa接口,不需要对接口进行实现,jpa可根据命名自动进行数据的查询
     * jpa接口规范：http://docs.spring.io/spring-data/jpa/docs/1.11.0.M1/reference/html/
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

}
