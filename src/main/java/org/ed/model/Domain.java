package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ed.exceptions.CRUDException;
import org.ed.services.DBConnection;
import org.ed.utilities.DBUtilities;
import org.ed.utilities.MethodsUtilities;

@ToString
@Setter
@Getter
public class Domain {


    private static Domain domain;
    private IUser iUser;
    private IAlbum iAlbum;
    private ISong iSong;
    private IArtist iArtist;


    private Domain() {
        iUser = new IUser();
        iAlbum = new IAlbum();
        iSong = new ISong();
        iArtist = new IArtist();
    }

    public static Domain getInstance() {
        if (domain == null) {
            domain = new Domain();
            domain.cargarDatos();
        }
        return domain;
    }

    private void cargarDatos() {

        DBConnection dbConnection = DBConnection.getInstance();
        dbConnection.loadUsers(iUser.getUsers());
        dbConnection.loadArtists(iArtist.getArtists());
    }


    public void register(User userBuilder) {
        try {
            iUser.create(userBuilder);
        } catch (CRUDException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existUser(String text) {
        try {
            return iUser.read(text) != null;
        } catch (CRUDException ignored) {
            return false;
        }
    }

    public void addUser(User user) {
        try {
            iUser.create(user);
        } catch (CRUDException e) {
            throw new RuntimeException(e);
        }
    }

    public Song getSong(String id) {

        try {
            return iSong.read(Long.parseLong(id));
        } catch (Exception e) {
            return null;
        }

    }

    public boolean login(String user, String password) {
        try {
            User user1 = iUser.read(user);
            return user1.getPassword().equals(MethodsUtilities.hashPassword(password));
        } catch (CRUDException e) {
            return false;
        }
    }

    public boolean isUserLogged() {
        try {
            User user1 = iUser.read(MethodsUtilities.getUserLogged());
            return user1.getPassword().equals(MethodsUtilities.getPasswordLogged());
        } catch (CRUDException e) {
            return false;
        }
    }
}
