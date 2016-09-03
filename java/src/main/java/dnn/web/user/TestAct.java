package dnn.web.user;

import dnn.common.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.service.user.ISerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by lgq on 16-9-3.
 */
@RestController("test")
public class TestAct {

    @Autowired
    protected ISerUser serUser;

    @GetMapping("page")
    public List<User> Page(){
        UserDto dto = new UserDto();
        dto.setPage(2);
        return serUser.findByPage(dto);
    }

    @GetMapping("count")
    public long count(){
        return serUser.count();
    }


    @GetMapping("all")
    public  List<User>  all(){
        return serUser.findAll();
    }

    @GetMapping("findById")
    public  User  findById(){
        return serUser.findById("57ca9265c68673cd7e5ae780");
    }

    @GetMapping("add")
    public  void   add(){
        User user = new User();
        user.setPassword("888888");
        user.setAccessTime(LocalDateTime.now());
        user.setUsername("test");
        serUser.save(user);
    }

    @GetMapping("delModel")
    public  void   del(){
        String id = "57cad2a9ba340a7851437add";
        User user = serUser.findById(id);
        serUser.delete(user);
    }

    @GetMapping("delId")
    public  void   delId(){
        String id = "57cad2f0ba340a7851437ade";
        serUser.delete(id);
    }
    @GetMapping("update")
    public  void   update(){
        String id = "57cad325ba340a7851437adf";
        User user = serUser.findById(id);
        user.setUsername("updateName");
        user.setAccessTime(LocalDateTime.now().plusDays(50));
        serUser.update(user);
    }
}
