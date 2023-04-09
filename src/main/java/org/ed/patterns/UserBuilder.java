package org.ed.patterns;

import lombok.Getter;
import lombok.Setter;
import org.ed.model.Domain;
import org.ed.model.User;
import org.ed.utilities.MethodsUtilities;

/**
 * This class is a builder for the User class.
 * @author Alejandro Arias
 * @version 1.0
 * @since 2020-10-20
 */

@Setter
@Getter
public class UserBuilder {

    // The user to be built.
    private User user;

    public UserBuilder() {
        user = new User();
    }

    /*
        * The following methods are the setters for the user's attributes.
     */
    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder userName(String userName) {
        user.setUserName(userName);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(MethodsUtilities.hashPassword(password));
        return this;
    }
    public UserBuilder password(String password, boolean flag) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder nationality(String nationality) {
        user.setNationality(nationality);
        return this;
    }

    public User build() {
        return user;
    }

    public UserBuilder id(Long id) {
        user.setId(id);
        return this;
    }


    public UserBuilder loadFavoritesSongs(String favoritesSongs) {

        String[] idSongs = favoritesSongs.split(" ");

        for (String id:idSongs){
            user.getSongs().add(Domain.getInstance().getSong(id));
        }

        return this;

    }
}
