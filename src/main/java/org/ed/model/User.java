package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.CircularList;

/**
 * This class represents a user of the application.
 * @author Alejandro Arias
 * @version 1.0
 * @since 2020-12-01
 */

@Getter
@Setter
public class User {

    private static Long auxId = 0L;
    //Attributes
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String nationality;

    private CircularList<Song> songs;

    //Constructors
    public User() {
        this.id = auxId++;
        songs = new CircularList<>();
    }


    //To String
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    /**
     * This method compares the email of the user with the email of another user.
     * @param email The email to compare.
     * @return True if the emails are the same, false otherwise.
     */
    public boolean compareEmail(String email) {
        return this.email.equals(email);
    }

    public String getFavoriteSong() {
        StringBuilder aux = new StringBuilder();
        while (songs.hasNext()){
            aux.append(songs.next().getId()).append(" ");
        }
        return aux.toString();

    }

    /**
     * This method set the id of the user.
     * @param id The id to set.
     */
    public static void setId(Long id) {
        auxId = ++id;
    }
}
