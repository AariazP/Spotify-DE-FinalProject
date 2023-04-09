package org.ed.model;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.HashMap;
import org.ed.exceptions.CRUDException;
import org.ed.interfaces.CRUDRepository;
import org.ed.services.DBConnection;

@Getter
@Setter
public class IUser implements CRUDRepository<User> {

    private HashMap<String, User> users;

    public IUser() {
        users = new HashMap<>();
    }


    @Override
    public void create(User user) throws CRUDException {

        if(users.containsKey(user.getUserName())) {
            throw new CRUDException("The user already exists");
        }

        User aux = users.get(user.getUserName());

        if(aux != null && aux.compareEmail(user.getEmail())) {
            throw new CRUDException("The email already exists");
        }

        users.put(user.getUserName(), user);
        DBConnection.getInstance().saveUsers(user);


    }

    @Override
    public User read(Long id) throws CRUDException {
        return null;
    }

    @Override
    public void update(User user) throws CRUDException {

    }

    @Override
    public void delete(String id) throws CRUDException {

    }

    public User read(String userName) throws CRUDException {
        if(!users.containsKey(userName)) {
            throw new CRUDException("The user does not exist");
        }
        return users.get(userName);
    }


}
