package dnn.web;

import com.alibaba.fastjson.JSON;
import dnn.common.json.ResponseText;
import dnn.dto.Condition;
import dnn.dto.SearchJson;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.entity.user.UserDetails;
import dnn.entity.user.UserInterest;
import dnn.entity.user.UserType;
import dnn.enums.DataType;
import dnn.enums.RestrictionType;
import dnn.service.user.ISerUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lgq on 16-9-29.
 */
@RestController
@RequestMapping("test")
public class TestAct {
    @Autowired
    protected ISerUser serUser;

    @GetMapping("findByPage")
    public ResponseText findByPage(UserDto dto) throws Throwable {
        Condition condition = new Condition();
        String[] between =
                new String[]{"1","99"};
        condition.setField("age");
        condition.setValues(between);
        condition.setFieldType(DataType.INT);
        condition.setRestrict(RestrictionType.BETWEEN);
        dto.getConditions().add(condition);
        List<User> users = serUser.findByPage(dto);
        return new ResponseText(users);
    }

    @GetMapping("add")
    public ResponseText add(UserDto dto) throws Throwable {
        User user = new User();
        user.setUsername("liguiqin22");
        user.setAge(77);
        user.setPassword("7777755");
        user.setUserType(UserType.CUSTOM);

        UserDetails details = new UserDetails();
        details.setEmail("liguiqin@qq.com");
        details.setAddress("广州");
        user.setDetails(details);

        Set<UserInterest> interests = new HashSet<>(1);
        UserInterest interest = new UserInterest();
        interest.setSeq(1);
        interest.setUser(user);
        interest.setCreateTime(LocalDateTime.now());
        interests.add(interest);
        user.setInterests(interests);
        serUser.save(user);
        return new ResponseText();
    }

    @GetMapping("update")
    public ResponseText update(UserDto dto) throws Throwable {
        List<Condition> conditions = dto.getConditions();
        Condition c= new Condition("username",DataType.STRING);
        c.setValues(new String[]{"liguiqin"});
        c.setRestrict(RestrictionType.EQ);
        conditions.add(c);
        List<User> users = serUser.findByCis(dto,false);
        User user = users.get(0);
        user.setPassword("666 this is a pass");
        user.getDetails().setTelephone("1999999");
        serUser.update(user);
        System.out.println(JSON.toJSONString(user));
        return new ResponseText();
    }
    @GetMapping("remove")
    public ResponseText remove(UserDto dto) throws Throwable {
        Condition c= new Condition("username",DataType.STRING);
        c.setValues(new String[]{"liguiqin"});
        c.setRestrict(RestrictionType.EQ);
        dto.getConditions().add(c);
        User user = serUser.findOne(dto);
        serUser.remove(user.getId());
        return new ResponseText(user);
    }

    @GetMapping("like")
    public ResponseText like(UserDto dto) throws Throwable {
        Condition c= new Condition("username",DataType.STRING);
        c.setValues(new String[]{"ligui"});
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(c);
        User user = serUser.findOne(dto);
        return new ResponseText(user);
    }

    @GetMapping("addAll")
    public ResponseText addAll(UserDto dto) throws Throwable {
        List<User> users = new ArrayList<>(5);
        for(int i=0;i<5;i++){
            User user = new User();
            user.setUsername("testName"+i);
            user.setAge(20+i);
            user.setPassword("password"+i);
            user.setUserType(UserType.MANAGER);
            users.add(user);

        }
        serUser.save(users);
        return new ResponseText(users);
    }

    @GetMapping("updateAll")
    public ResponseText updateAll(UserDto dto) throws Throwable {
        List<User> users = null;
        Condition c= new Condition("username",DataType.STRING);
        c.setValues(new String[]{"testName"});
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(c);
        users = serUser.findByCis(dto,true);
        int i =0;
        for(User user :users){
            user.setUsername("updateName"+i++);
        }
        serUser.update(users);
        return new ResponseText(users);
    }


}
