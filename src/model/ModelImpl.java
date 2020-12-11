package model;

import model.crud.*;
import model.objects.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl {

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
    public boolean createUser(Users newUser) {
        return usersCRUD.create(newUser);
    }

    public List<Users> fetchUser(String query) {
        return usersCRUD.read(query);
    }

    public List<Users> fetchAllUsers() {
        return usersCRUD.read(null);
    }

    public boolean updateUser(Users oldUser, Users newUser) {
        return usersCRUD.update(oldUser, newUser);
    }

    public boolean deleteUser(Users deleteUser) {
        return usersCRUD.delete(deleteUser);
    }

    // USERDETAIL TABLE
    public boolean createUserDetail(UserDetail newUser) {
        return userDetailCRUD.create(newUser);
    }

    public List<UserDetail> fetchUserDetail(String query) {
        return userDetailCRUD.read(query);
    }

    public List<UserDetail> fetchAllUserDetails() {
        return userDetailCRUD.read(null);
    }

    public boolean updateUserDetail(UserDetail oldUser, UserDetail newUser) {
        return userDetailCRUD.update(oldUser, newUser);
    }

    public boolean deleteUserDetail(UserDetail deleteUser) {
        return userDetailCRUD.delete(deleteUser);
    }

    // ACTORS TABLE
    public boolean createActor(Actors newUser) {
        return actorsCRUD.create(newUser);
    }

    public List<Actors> fetchActor(String query) {
        return actorsCRUD.read(query);
    }

    public List<Actors> fetchAllActors() {
        return actorsCRUD.read(null);
    }

    public boolean updateActor(Actors oldUser, Actors newUser) {
        return actorsCRUD.update(oldUser, newUser);
    }

    public boolean deleteActor(Actors deleteUser) {
        return actorsCRUD.delete(deleteUser);
    }

    // MOVIES TABLE
    public boolean createMovie(Movies newMovie) {
        return moviesCRUD.create(newMovie);
    }

    public List<Movies> fetchMovie(String query) {
        return moviesCRUD.read(query);
    }

    public List<Movies> fetchAllMovies() {
        return moviesCRUD.read(null);
    }

    public boolean updateMovie(Movies oldMovie, Movies newMovie) {
        return moviesCRUD.update(oldMovie, newMovie);
    }

    public boolean deleteMovie(Movies deleteMovie) {
        return moviesCRUD.delete(deleteMovie);
    }

    // MOVIESLIKE TABLE
    public boolean createMovieLike(MovieLike newMovie) {
        return movieLikeCRUD.create(newMovie);
    }

    public List<MovieLike> fetchMovieLike(String query) {
        return movieLikeCRUD.read(query);
    }

    public List<MovieLike> fetchAllMovieLikes() {
        return movieLikeCRUD.read(null);
    }

    public boolean updateMovieLike(MovieLike oldMovie, MovieLike newMovie) {
        return movieLikeCRUD.update(oldMovie, newMovie);
    }

    public boolean deleteMovieLike(MovieLike deleteMovie) {
        return movieLikeCRUD.delete(deleteMovie);
    }

    // REVIEWS TABLE
    public boolean createReview(Reviews newReview) {
        return reviewsCRUD.create(newReview);
    }

    public List<Reviews> fetchReview(String query) {
        return reviewsCRUD.read(query);
    }

    public List<Reviews> fetchAllReviews() {
        return reviewsCRUD.read(null);
    }

    public boolean updateReview(Reviews oldReview, Reviews newReview) {
        return reviewsCRUD.update(oldReview, newReview);
    }

    public boolean deleteReview(Reviews deleteReview) {
        return reviewsCRUD.delete(deleteReview);
    }

    // REVIEWSLIKE TABLE
    public boolean createReviewLike(ReviewLike newReview) {
        return reviewLikeCRUD.create(newReview);
    }

    public List<ReviewLike> fetchReviewLike(String query) {
        return reviewLikeCRUD.read(query);
    }

    public List<ReviewLike> fetchAllReviewLikes() {
        return reviewLikeCRUD.read(null);
    }

    public boolean updateReviewLike(ReviewLike oldReview, ReviewLike newReview) {
        return reviewLikeCRUD.update(oldReview, newReview);
    }

    public boolean deleteReviewLike(ReviewLike deleteReview) {
        return reviewLikeCRUD.delete(deleteReview);
    }

//    public static void main(String[] args) {
//        ModelImpl impl = new ModelImpl();
//
//        Users users = new Users();
//        users.setUserName("Tarun");
//        users.setPassword("Tarun");
//        users.setRole("Admin");
//        users.setVerified(true);
//        impl.createUser(users);

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

//        Actors actors = new Actors();
//        actors.setActor_id(2);
//        Actors oldactors = new Actors();
//        oldactors.setActor_id(1);
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
//
//        impl.createMovie(movies);
//        impl.updateMovie(movies,movies);
//        impl.fetchAllMovies();

//        Reviews reviews = new Reviews();
//        reviews.setReview_id(1);
//        reviews.setDate(Date.valueOf("1991-10-02"));
//        reviews.setComment("Very Good");
//        reviews.setUser_id(1);
//        reviews.setMovie_id(2);
//
//        impl.createReview(reviews);
//        impl.fetchAllReviews();
//        impl.updateReview(reviews, reviews);
//        impl.deleteReview(reviews);

//        ReviewLike reviewLike = new ReviewLike();
//        reviewLike.setUser_id(1);
//        reviewLike.setReview_id(2);
//        reviewLike.setUp_down_vote(-1);
//        impl.createReviewLike(reviewLike);
//        impl.fetchAllReviewLikes();
//        impl.updateReviewLike(reviewLike, reviewLike);
//        impl.deleteReviewLike(reviewLike);

//        MovieLike movieLike = new MovieLike();
//        movieLike.setUser_id(1);
//        movieLike.setMovie_id(2);
//        movieLike.setUp_down_vote(2);
//        impl.createMovieLike(movieLike);
//        impl.fetchAllMovieLikes();
//        impl.updateMovieLike(movieLike, movieLike);
//        impl.deleteMovieLike(movieLike);
//    }
}
