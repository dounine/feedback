package feedback.p;

import dnn.common.exception.SerException;
import dnn.common.utils.UserContext;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;

/**
 * Created by huanghuanlai on 16/9/10.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserContext.class)
public class PeopleTest {

    @Test
    public void testCurrentUser(){
        PowerMockito.mockStatic(UserContext.class);
        UserContext userContext = PowerMockito.mock(UserContext.class);
        try {
            User user = new User();
            user.setId("123");
            user.setUsername("admin123");
            user.setAccessTime(LocalDateTime.now());
            user.setUserType(UserType.MANAGER);

            PowerMockito.when(userContext.currentUser()).thenReturn(user);//mock要返回什么内容

            User mockUser = userContext.currentUser();

            Assert.assertEquals("123",mockUser.getId());
        } catch (SerException e) {
            e.printStackTrace();
        }
    }
}
