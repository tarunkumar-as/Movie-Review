package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.Actors;
import model.objects.Movies;
import model.objects.Users;
import view.IView;

import java.util.ArrayList;
import java.util.List;

public class MovieModule extends AbstractModule implements IModule {

    public MovieModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        Users currentUser = controller.getCurrentUser();
        switch (currentUser.getRole()) {
            case "Admin":
                return adminMovieModules();
            case "Critique":
                return critiqueModules();
            default:
                return viewerModules();
        }
    }

    private Module adminMovieModules() {
        String displayText =
                "-----------------------\n" +
                "| SELECT AN OPTION    |\n" +
                "-----------------------\n" +
                "| 1 - Actors          |\n" +
                "| 2 - List Movies     |\n" +
                "| 3 - Search Movies   |\n" +
                "| 4 - Filter Movies   |\n" +
                "| 5 - Add Movie       |\n" +
                "| 6 - Select Movie    |\n" +
                "| 7 - Delete Movie    |\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    return Module.ACTORS;
                case "2":
                    listMovies();
                    return Module.MOVIE_MAIN;
                case "3":
                    searchMovies();
                    return Module.MOVIE_MAIN;
                case "4":
                    filterMovies();
                    return Module.MOVIE_MAIN;
                case "5":
                    addMovies();
                    return Module.MOVIE_MAIN;
                case "6":
                    return selectMovie();
                case "7":
                    deleteMovie();
                    return Module.MOVIE_MAIN;
                case "0":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private Module critiqueModules() {
        String displayText =
                "-----------------------\n" +
                "| SELECT AN OPTION    |\n" +
                "-----------------------\n" +
                "| 1 - List Movies     |\n" +
                "| 2 - Search Movies   |\n" +
                "| 3 - Filter Movies   |\n" +
                "| 4 - Select Movie    |\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    listMovies();
                    return Module.MOVIE_MAIN;
                case "2":
                    searchMovies();
                    return Module.MOVIE_MAIN;
                case "3":
                    filterMovies();
                    return Module.MOVIE_MAIN;
                case "4":
                    return selectMovie();
                case "0":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private Module viewerModules() {
        String displayText =
                "-----------------------\n" +
                "| SELECT AN OPTION    |\n" +
                "-----------------------\n" +
                "| 1 - List Movies     |\n" +
                "| 2 - Search Movies   |\n" +
                "| 3 - Filter Movies   |\n" +
                "| 4 - Select Movie    |\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    listMovies();
                    return Module.MOVIE_MAIN;
                case "2":
                    searchMovies();
                    return Module.MOVIE_MAIN;
                case "3":
                    filterMovies();
                    return Module.MOVIE_MAIN;
                case "4":
                    return selectMovie();
                case "0":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private void listMovies() {
        List<Movies> moviesList = model.fetchAllMovies();
        displayMoviesList(moviesList);
    }

    private void searchMovies() {
        view.echoOutput("Enter the search phrase: ");
        String input = view.getInput();
        List<Movies> moviesList = model.fetchMovie("movie_name like '%" + input + "%'");
        displayMoviesList(moviesList);
    }

    private void filterMovies() {
        String displayText =
                "------------------------\n" +
                "| SELECT FILTER OPTIONS|\n" +
                "------------------------\n" +
                "| 1 - Short films(<90min)\n" +
                "| 2 - Full length films(>=90 min)\n" +
                "| 3 - G certified      |\n" +
                "| 4 - PG certified     |\n" +
                "| 5 - R certified      |\n" +
                "| 6 - X certified      |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        String query = "";
        if (input.contains("1")) {
            query += "or duration < 90 ";
        }
        if (input.contains("2")) {
            query += "or duration > 89 ";
        }
        if (input.contains("3")) {
            query += "or certification = 'G' ";
        }
        if (input.contains("4")) {
            query += "or certification = 'PG' ";
        }
        if (input.contains("5")) {
            query += "or certification = 'R' ";
        }
        if (input.contains("6")) {
            query += "or certification = 'U' ";
        }
        if (query.length() > 0) {
            query = query.substring(3);
        }
        List<Movies> moviesList = model.fetchMovie(query);
        displayMoviesList(moviesList);
    }

    private void  displayMoviesList(List<Movies> moviesList) {
        String displayText =
                "-----------------------\n" +
                "| Movies List         |\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        displayText = "-----------------------\n";

        for (Movies movie : moviesList) {
            StringBuilder movieText = new StringBuilder("Movie Name : " + movie.getMovie_name() + "\n" +
                    "Certification : " + movie.getCertification() + "\n" +
                    "Duration : " + movie.getDuration() + " minutes\n" +
                    "Movie Poster : " + movie.getPhoto_url() + "\n");
            movieText.append("Actors list : ");
            for (Actors actors : movie.getActorsList()) {
                movieText.append(actors.getName()).append(", ");
            }
            if (movie.getActorsList().size() > 0) {
                movieText.setLength(movieText.length() - 2);
            }
            view.echoOutput(displayText + movieText.toString());
        }
        if (moviesList.size() == 0) {
            view.echoOutput("No movies to display\n");
        }
    }

    private void addMovies() {
        List<Actors> actorsList = model.fetchAllActors();

        Movies newMovie = new Movies();
        String displayText =
                "-----------------------\n" +
                "| Enter Movie Name:    |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newMovie.setMovie_name(view.getInput());

        while(true) {
            displayText =
                    "-----------------------\n" +
                    "| Enter Duration (in min)\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            try {
                int duration = Integer.parseInt(view.getInput());
                newMovie.setDuration(duration);
                break;
            } catch (Exception e) {
                view.echoOutput("Invalid duration. Enter again\n");
                continue;
            }
        }

        displayText =
                "-----------------------\n" +
                "| Enter poster url:   |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newMovie.setPhoto_url(view.getInput());

        displayText =
                "-----------------------\n" +
                "| Enter certification:   |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newMovie.setCertification(view.getInput());

        List<Actors> movieActorsList = new ArrayList<>();
        String actorsListString = "";
        for(Actors actors : actorsList) {
            actorsListString += (", " + actors.getName());
        }
        actorsListString = actorsListString.substring(2);
        while(true) {
            displayText =
                    "-----------------------\n" +
                    "| Enter actor name (" + actorsListString + ")\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            String userName = view.getInput();

            Actors currentActor = null;
            boolean userNameExists = false;
            for (Actors actor : actorsList) {
                if (actor.getName().equalsIgnoreCase(userName)) {
                    userNameExists = true;
                    currentActor = actor;
                    break;
                }
            }
            if (userNameExists) {
                if (!movieActorsList.contains(currentActor)) {
                    movieActorsList.add(currentActor);
                }
                view.echoOutput("Want to add another actor to the movie?(y/n): ");
                String input = view.getInput();
                if (!(input.equals("y") || input.equals("Y"))) {
                    break;
                }
            }
            else {
                view.echoOutput("Invalid Actor Name. Please enter again");
            }
        }
        newMovie.setActorsList(movieActorsList);

        if (model.createMovie(newMovie)) {
            view.echoOutput("Movie added successfully\n");
        }
        else {
            view.echoOutput("Unable to add movie\n");
        }
    }

    private Module selectMovie() {
        List<Movies> moviesList = model.fetchAllMovies();
        view.echoOutput("Enter movie name:\n");
        String movieName = view.getInput();
        for (Movies movie : moviesList) {
            if (movie.getMovie_name().equalsIgnoreCase(movieName)) {
                controller.setSelectedMovie(movie);
                return Module.MOVIE_DETAIL;
            }
        }
        return Module.MOVIE_MAIN;
    }

    private void deleteMovie() {
        List<Movies> moviesList = model.fetchAllMovies();
        view.echoOutput("Enter movie name:");
        String movieName = view.getInput();
        Movies selectedMovie = null;
        for (Movies movie : moviesList) {
            if (movie.getMovie_name().equalsIgnoreCase(movieName)) {
                selectedMovie = movie;
            }
        }
        if (selectedMovie == null) {
            view.echoOutput("Invalid movie name\n");
        }
        else {
            view.echoOutput("Do you want to delete the movie and its related data?(y/n)");
            String input = view.getInput();
            if (input.equals("y") || input.equals("Y")) {
                model.deleteMovie(selectedMovie);
                view.echoOutput("Movie deleted successfully\n");
            }
        }
    }
}
