import org.ed.model.User;
import org.ed.patterns.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {


    @Test
    public void compareEmail(){

        User user = new UserBuilder()
                .name("Alejandro")
                .userName("Aariaz_")
                .password("123456")
                .email("alejandro@gmail.com")
                .nationality("Colombia")
                .build();

        assertTrue(user.compareEmail("alejandro@gmail.com"));


    }
}
