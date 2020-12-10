package model;

import model.crud.*;
import model.objects.UserDetail;
import model.objects.Users;

import java.sql.Date;
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

    // USERS TABLE
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

    // USERDETAIL TABLE
    public void createUserDetail(UserDetail newUser) {
        userDetailCRUD.create(newUser);
    }

    public List<UserDetail> fetchUserDetail(String query) {
        return userDetailCRUD.read(query);
    }

    public List<UserDetail> fetchAllUserDetails() {
        return userDetailCRUD.read(null);
    }

    public void updateUserDetail(UserDetail oldUser, UserDetail newUser) {
        userDetailCRUD.update(oldUser, newUser);
    }

    public void deleteUserDetail(UserDetail deleteUser) {
        userDetailCRUD.delete(deleteUser);
    }

    public static void main(String[] args) {
        ModelImpl impl = new ModelImpl();
//        UserDetail olduserDetail = new UserDetail();
//        olduserDetail.setUser_id(1);
//        UserDetail userDetail = new UserDetail();
//        userDetail.setDob(Date.valueOf("1994-12-29"));
//        userDetail.setUser_id(1);
//        userDetail.setFname("TarunK");
//        userDetail.setLname("KumarM");
//        userDetail.setPhone(Long.parseLong("6175139111"));
//        userDetail.setUser_photo("www.googleyyyy.com");
//        impl.createUserDetail(userDetail);
//        impl.fetchAllUserDetails();
//        impl.updateUserDetail(olduserDetail, userDetail);
//        impl.deleteUserDetail(olduserDetail);
    }
}
