package model;

import model.crud.*;
import model.objects.Actors;
import model.objects.Movies;
import model.objects.UserDetail;
import model.objects.Users;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

    // ACTORS TABLE
    public void createActor(Actors newUser) {
        actorsCRUD.create(newUser);
    }

    public List<Actors> fetchActor(String query) {
        return actorsCRUD.read(query);
    }

    public List<Actors> fetchAllActors() {
        return actorsCRUD.read(null);
    }

    public void updateActor(Actors oldUser, Actors newUser) {
        actorsCRUD.update(oldUser, newUser);
    }

    public void deleteActor(Actors deleteUser) {
        actorsCRUD.delete(deleteUser);
    }

    // MOVIES TABLE
    public void createMovie(Movies newMovie) {
        moviesCRUD.create(newMovie);
    }

    public List<Movies> fetchMovie(String query) {
        return moviesCRUD.read(query);
    }

    public List<Movies> fetchAllMovies() {
        return moviesCRUD.read(null);
    }

    public void updateMovie(Movies oldMovie, Movies newMovie) {
        moviesCRUD.update(oldMovie, newMovie);
    }

    public void deleteMovie(Movies deleteMovie) {
        moviesCRUD.delete(deleteMovie);
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

        Actors actors = new Actors();
        actors.setActor_id(2);
        Actors oldactors = new Actors();
        oldactors.setActor_id(1);
//        actors.setSex('M');
//        actors.setName("123");
//        actors.setPhoto_url("asdfGoogle.com");
//        impl.createActor(actors);
//        impl.fetchAllActors();
//        impl.updateActor(oldactors, actors);
//        impl.deleteActor(oldactors);

//        Movies movies = new Movies();
//        movies.setMovie_Id(2);
//        movies.setMovie_name("TEstmovie");
//        movies.setPhoto_url("movie_url");
//        movies.setDuration(90);
//        movies.setCertification("U");
//        movies.setActorsList(new ArrayList<>(List.of(oldactors, actors)));

//        impl.createMovie(movies);
//        impl.updateMovie(movies,movies);
        impl.fetchAllMovies();
    }
}
