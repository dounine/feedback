package dnn.web.user;

import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.entity.user.UserDetails;
import dnn.entity.user.UserType;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lgq on 16-9-3.
 */
@RequestMapping("test")
@RestController
public class TestAct {

    @Autowired
    protected ISerUser serUser;


    @GetMapping("findByCondition")
    public List<User> findByCondition() throws Throwable {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("userDetails.company", "艾佳");
        return serUser.findByCis(conditions);
    }

    @GetMapping("findByFuzzy")
    public List<User> findByFuzzy()throws Throwable {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("username", "ligui");
        return serUser.findByFuzzy(conditions);
    }

    @GetMapping("page")
    public List<User> Page() throws Throwable{
        //构建接收人列表

        //初始化发送人与接收人列表
        Email email = new Email("liguiqin_aj@163.com"); //使用系统账户，不需要再设置用户登录名及密码
        //设置邮件要发送的内容以及标题信息
        email.initEmailInfo("你好,逗比  ", "请你明天来办公室开会！");
        EmailUtil.SendMail(email);
        UserDto dto = new UserDto();
        dto.setPage(2);
        return serUser.findByPage(dto);
    }

    @GetMapping("count")
    public long count()throws Throwable {
        return serUser.count(new UserDto());
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
        user.setPassword("1234567890");
        user.setAccessTime(LocalDateTime.now());
        user.setUsername("刘霞");
        user.setStatus(Status.THAW);
        user.setUserType(UserType.CUSTOM);
        UserDetails userDetails = new UserDetails();
        userDetails.setCompany("艾佳");
        userDetails.setAddress("冠达商务中心");
        userDetails.setContact("程小姐");
        userDetails.setPostcodes("56483");
        userDetails.setTelephone("14543556577");
        userDetails.setFax("0734-14543556577");
        userDetails.setEmail("456466@qq.com");
        user.setDetails(userDetails);
        String[] tags = {"aa","bb","cc"};
        serUser.save(user);
    }

    @GetMapping("delModel")
    public void del() throws Throwable{
        String id = "57cad2a9ba340a7851437add";
        User user = serUser.findById(id);
        serUser.remove(user);
    }

    @GetMapping("delId")
    public void delId()throws Throwable {
        String id = "57cad2f0ba340a7851437ade";
        serUser.remove(id);
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
