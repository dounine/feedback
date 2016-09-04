package dnn.web.user;

import dnn.common.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lgq on 16-9-3.
 */
@RestController
public class TestAct {

    @Autowired
    protected ISerUser serUser;


    @GetMapping("findByCondition")
    public List<User> findByCondition() throws Throwable {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("userDetails.company", "艾佳");
        return serUser.findByCondition(conditions);
    }

    @GetMapping("findByFuzzy")
    public List<User> findByFuzzy()throws Throwable {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("username", "ligui");
        return serUser.findByFuzzy(conditions);
    }

    @GetMapping("page")
    public List<User> Page() throws Throwable{
        UserDto dto = new UserDto();
        dto.setPage(2);
        return serUser.findByPage(dto);
    }

    @GetMapping("count")
    public long count()throws Throwable {
        return serUser.count();
    }


    @GetMapping("all")
    public List<User> all()throws Throwable {
        List<User> users = serUser.findAll();
        return users;
    }

    @GetMapping("findById")
    public User findById()throws Throwable {
        return serUser.findById("57ca9265c68673cd7e5ae780");
    }

    @GetMapping("add")
    public void add()throws Throwable {
        User user = new User();
        user.setPassword("deatil");
        user.setAccessTime(LocalDateTime.now());
        user.setUsername("xinaml");
        serUser.save(user);
    }

    @GetMapping("delModel")
    public void del() throws Throwable{
        String id = "57cad2a9ba340a7851437add";
        User user = serUser.findById(id);
        serUser.delete(user);
    }

    @GetMapping("delId")
    public void delId()throws Throwable {
        String id = "57cad2f0ba340a7851437ade";
        serUser.delete(id);
    }

    @GetMapping("update")
    public void update() throws Throwable{
        String id = "57cad325ba340a7851437adf";
        User user = serUser.findById(id);
        user.setUsername("updateName");
        user.setAccessTime(LocalDateTime.now().plusDays(50));
        serUser.update(user);
    }
}
