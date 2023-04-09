import org.ed.exceptions.CRUDException;
import org.ed.model.IUser;
import org.ed.model.User;
import org.ed.patterns.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IUserTest {


    @Test
    public void registerUser() {
        try {
        // The user repository.
        IUser iUser = new IUser();
        // The user to be registered.
        User user = new UserBuilder()
                .name("Alejandro")
                .userName("Aariaz_")
                .password("123456")
                .email("alejandro@gmail.com")
                .nationality("Colombia")
                .build();
        // The user is registered.
            iUser.create(user);
        // The user is read.
            assertEquals(user, iUser.read(user.getUserName()));
        } catch (CRUDException e) {
            throw new RuntimeException(e);
        }

    }



}
