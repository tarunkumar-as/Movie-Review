package model;

import model.crud.*;
import model.objects.Users;

import java.sql.SQLException;
import java.util.List;

public class ModelImpl implements IModel {

    private final ActorsCRUD actorsCRUD = new ActorsCRUD();
    private final MovieLikeCRUD movieLikeCRUD = new MovieLikeCRUD();
    private final MoviesCRUD moviesCRUD = new MoviesCRUD();
    private final ReviewLikeCRUD reviewLikeCRUD = new ReviewLikeCRUD();
    private final ReviewsCRUD reviewsCRUD = new ReviewsCRUD();
    private final UserDetailCRUD userDetailCRUD = new UserDetailCRUD();
    private final UsersCRUD usersCRUD = new UsersCRUD();

    public ModelImpl() {
        try {
            SQLConnector.createConnection();
        } catch (SQLException throwables) {
            System.out.println("Unable to connect to database");
            throwables.printStackTrace();
        }
    }

    public void createUser(Users newUser) {
        usersCRUD.create(newUser);
    }

    public List<Users> fetchUser(String query) {
        return usersCRUD.read(query);
    }

    public List<Users> fetchAllUsers() {
        return usersCRUD.read(null);
    }

    public void updateUser(Users oldUser, Users newUser) {
        usersCRUD.update(oldUser, newUser);
    }

    public void deleteUser(Users deleteUser) {
        usersCRUD.delete(deleteUser);
    }
}
