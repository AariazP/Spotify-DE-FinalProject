package org.ed.patterns;

import lombok.Getter;
import lombok.Setter;
import org.ed.model.Admin;

@Setter
@Getter
public class AdminBuilder {

    private UserBuilder userBuilder;
    private Admin admin;



    public AdminBuilder buildAdmin() {
        admin = new Admin();
        userBuilder = new UserBuilder();
        userBuilder.setUser(admin);
        return this;
    }

    /*
        * the following methods are the same as the ones in UserBuilder
     */


    public AdminBuilder name(String name) {
        userBuilder.name(name);
        return this;
    }

    public AdminBuilder userName(String userName) {
        userBuilder.userName(userName);
        return this;
    }

    public AdminBuilder password(String password) {
        userBuilder.password(password);
        return this;
    }

    public AdminBuilder email(String email) {
        userBuilder.email(email);
        return this;
    }

    public AdminBuilder nationality(String nationality) {
        userBuilder.nationality(nationality);
        return this;
    }

    public Admin build() {
        return admin;
    }





}
