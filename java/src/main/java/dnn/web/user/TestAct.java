package dnn.web.user;

import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.dto.SearchJson;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.entity.user.UserDetails;
import dnn.entity.user.UserType;
import dnn.enums.RestrictionType;
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


    /**
     * 分页查询(带模糊搜索,排序)
     * @return
     * @throws Throwable
     */
    @GetMapping("page")
    public List<User> Page() throws Throwable{
        UserDto dto = new UserDto();
        //dto.setOrder("asc");
        //dto.setSort(); 排序字段
       // dto. dto.setSearchJsons(); 搜索
        System.out.println("哈哈:"+serUser.findByPage(dto));
        return serUser.findByPage(dto);
    }
    /**
        * 分页时搜索排序等测试查询(带模糊搜索,排序)
     * @return
             * @throws Throwable
     */
    @GetMapping("search")
    public List<User> search() throws Throwable{
        UserDto dto = new UserDto();

        SearchJson searchJson = new SearchJson();
        searchJson.setSearchName(RestrictionType.GT);
        String[] field={"age","int","12"};
        searchJson.setSearchField(field);
        SearchJson searchJson2 = new SearchJson();
        searchJson2.setSearchName(RestrictionType.BETWEEN);
        String[] field2={"accessTime","dateTime","2016-09-08 14:48:21","2016-09-08 19:48:21"};
        searchJson2.setSearchField(field2);

        SearchJson searchJson3 = new SearchJson();
        searchJson3.setSearchName(RestrictionType.LIKE);
        String[] field3={"username","string","xin"};
        searchJson3.setSearchField(field3);

        List<SearchJson> searchJsons = new ArrayList<>();
        searchJsons.add(searchJson);
        searchJsons.add(searchJson2);
        searchJsons.add(searchJson3);

        dto.setSearchJsons(searchJsons);
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

        user.setPassword("aml");
        user.setAccessTime(LocalDateTime.now());
        user.setUsername("xinaml");
        user.setHeight(20.4f);
        user.setAge(50);
        user.setMoney(888.88);

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

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
