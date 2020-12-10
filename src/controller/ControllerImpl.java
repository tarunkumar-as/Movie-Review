package controller;

import controller.modules.*;
import controller.modules.Module;
import model.ModelImpl;
import model.objects.Movies;
import model.objects.Users;
import view.IView;

public class ControllerImpl implements IController {

    private final IView view;
    private final ModelImpl model;
    private Module moduleType;
    private IModule currentModule;

    private Users currentUser;
    private Movies selectedMovie;

    public ControllerImpl(IView view, ModelImpl model) {
        this.view = view;
        this.model = model;
        moduleType = Module.WELCOME;
    }

    @Override
    public void startController() {
        while (true) {
            switch (moduleType) {
                case LOGIN:
                    this.currentModule = new LoginModule(view, model, this);
                    break;
                case SIGNUP:
                    this.currentModule = new SignUpModule(view, model, this);
                    break;
                case MAINMENU:
                    this.currentModule = new MainModule(view, model, this);
                    break;
                case MOVIE_MAIN:
                    this.currentModule = new MovieModule(view, model, this);
                    break;
                case MOVIE_DETAIL:
                    this.currentModule = new MovieDetail(view, model, this);
                    break;
                case ACTORS:
                    this.currentModule = new ActorsModule(view, model, this);
                    break;
                case USERS_MAIN:
                    this.currentModule = new UsersModule(view, model, this);
                    break;
                case PROFILE:
                    this.currentModule = new ProfileModule(view, model, this);
                    break;
                case WELCOME:
                default:
                    this.currentModule = new WelcomeModule(view, model, this);
            }
            this.moduleType = currentModule.showModuleInfo();
        }
    }

    @Override
    public Users getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;

    }

    @Override
    public Movies getSelectedMovie() {
        return selectedMovie;
    }

    @Override
    public void setSelectedMovie(Movies movie) {
        selectedMovie = movie;
    }
}