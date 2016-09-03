package dnn.entity;

import dnn.common.validation.Del;
import dnn.common.validation.Edit;
import dnn.common.validation.Get;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public class BaseEntity {
    @Id
    //validation
    @NotBlank(message = "user id not blank",groups = {Del.class, Edit.class, Get.class})
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
