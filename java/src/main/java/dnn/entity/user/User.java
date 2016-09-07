package dnn.entity.user;

import dnn.common.jsr303.PasswordValid;
import dnn.common.validation.Add;
import dnn.entity.BaseEntity;
import dnn.enums.Status;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by huanghuanlai on 16/4/28.
 */
@Document
public class User extends BaseEntity{

    public static final int UNAME_MIN_SIZE = 6;
    public static final int UNAME_MAX_SIZE = 20;

    //groups 用于CRUD组合使用,当字段有分组时,@Validated(?)占位符内必需有值(Add,Del,Edit,Get)
    @NotBlank(message = "用户名不能空",groups = {Add.class})
    @Length(message = "用户名长度在 {min} 到 {max} 之间",min = UNAME_MIN_SIZE,max = UNAME_MAX_SIZE,groups = {Add.class})
    private String username;

    @NotBlank(message = "密码不能空",groups = {Add.class})
    @PasswordValid(groups = {Add.class})
    private String password;
    private String[] tags;
    private Status status;
    private UserDetails details;

    private LocalDateTime accessTime;

    private UserType userType=UserType.MANAGER;

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }
}
