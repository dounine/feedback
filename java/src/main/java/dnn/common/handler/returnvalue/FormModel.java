package dnn.common.handler.returnvalue;

/**
 * Created by huanghuanlai on 16/4/23.
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormModel {
    String value();
}