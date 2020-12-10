package controller;

import model.objects.Users;

public interface IController {

    void startController();

    Users getCurrentUser();

    void setCurrentUser(Users currentUser);
}
