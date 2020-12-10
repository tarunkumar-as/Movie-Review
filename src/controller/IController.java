package controller;

import model.objects.Movies;
import model.objects.Users;

public interface IController {

    void startController();

    Users getCurrentUser();

    void setCurrentUser(Users currentUser);

    Movies getSelectedMovie();

    void setSelectedMovie(Movies movie);
}
