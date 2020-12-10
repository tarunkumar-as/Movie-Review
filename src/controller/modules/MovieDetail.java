package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.*;
import view.IView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDetail extends AbstractModule implements IModule {

    public MovieDetail(IView view, ModelImpl model, IController controller) {
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
                "| 1 - View Movie Details\n" +
                "| 2 - Update Movie Details\n" +
                "| 3 - View Reviews    |\n" +
                "| 4 - Add Review      |\n" +
                "| 5 - Delete Review   |\n" +
                "| 6 - Like the Movie  |\n" +
                "| 7 - DisLike the Movie\n" +
                "| 8 - Like the Review |\n" +
                "| 9 - DisLike the Review\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    viewMovieDetails();
                    return Module.MOVIE_DETAIL;
                case "2":
                    updateMovieDetails();
                    return Module.MOVIE_DETAIL;
                case "3":
                    viewReviews();
                    return Module.MOVIE_DETAIL;
                case "4":
                    addReview();
                    return Module.MOVIE_DETAIL;
                case "5":
                    deleteReview();
                    return Module.MOVIE_DETAIL;
                case "6":
                    likeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "7":
                    dislikeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "8":
                    likeAReview();
                    return Module.MOVIE_DETAIL;
                case "9":
                    dislikeAReview();
                    return Module.MOVIE_DETAIL;
                case "0":
                    return Module.MOVIE_MAIN;
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
                "| 1 - View Movie Details\n" +
                "| 2 - View Reviews    |\n" +
                "| 3 - Add Review      |\n" +
                "| 4 - Like the Movie  |\n" +
                "| 5 - DisLike the Movie\n" +
                "| 6 - Like the Review |\n" +
                "| 7 - DisLike the Review\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    viewMovieDetails();
                    return Module.MOVIE_DETAIL;
                case "2":
                    viewReviews();
                    return Module.MOVIE_DETAIL;
                case "3":
                    addReview();
                    return Module.MOVIE_DETAIL;
                case "4":
                    likeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "5":
                    dislikeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "6":
                    likeAReview();
                    return Module.MOVIE_DETAIL;
                case "7":
                    dislikeAReview();
                    return Module.MOVIE_DETAIL;
                case "0":
                    return Module.MOVIE_MAIN;
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
                "| 1 - View Movie Details\n" +
                "| 2 - View Reviews    |\n" +
                "| 3 - Like the Movie  |\n" +
                "| 4 - DisLike the Movie\n" +
                "| 5 - Like the Review |\n" +
                "| 6 - DisLike the Review\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    viewMovieDetails();
                    return Module.MOVIE_DETAIL;
                case "2":
                    viewReviews();
                    return Module.MOVIE_DETAIL;
                case "3":
                    likeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "4":
                    dislikeTheMovie();
                    return Module.MOVIE_DETAIL;
                case "5":
                    likeAReview();
                    return Module.MOVIE_DETAIL;
                case "6":
                    dislikeAReview();
                    return Module.MOVIE_DETAIL;
                case "0":
                    return Module.MOVIE_MAIN;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }
    private void viewMovieDetails() {
        Movies currentMovie = controller.getSelectedMovie();
        String displayText =
                "-----------------------\n" +
                "| Movies List         |\n" +
                "-----------------------\n";

        StringBuilder movieText = new StringBuilder("Movie Name : " + currentMovie.getMovie_name() + "\n" +
                "Certification : " + currentMovie.getCertification() + "\n" +
                "Duration : " + currentMovie.getDuration() + " minutes\n" +
                "Movie Poster : " + currentMovie.getPhoto_url() + "\n");
        movieText.append("Actors list : ");
        for (Actors actors : currentMovie.getActorsList()) {
            movieText.append(actors.getName()).append(", ");
        }
        if (currentMovie.getActorsList().size() > 0) {
            movieText.setLength(movieText.length() - 2);
        }
        List<MovieLike> movieLikeList = model.fetchMovieLike(String.format("movie_id = %d",currentMovie.getMovie_Id()));
        int upVotes = 0;
        int downVotes = 0;
        for (MovieLike movieLike : movieLikeList) {
            if (movieLike.getUp_down_vote() > 0) {
                upVotes++;
            }
            else {
                downVotes++;
            }
        }
        displayText += (movieText.toString() + "\n");
        displayText += String.format("UpVotes : %d\n", upVotes);
        displayText += String.format("DownVotes : %d\n", downVotes);
        view.echoOutput(displayText);
    }

    private void updateMovieDetails() {
        List<Actors> actorsList = model.fetchAllActors();
        String displayText;
        Movies newMovie = new Movies();
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
        newMovie.setMovie_Id(controller.getSelectedMovie().getMovie_Id());
        newMovie.setMovie_name(controller.getSelectedMovie().getMovie_name());

        model.updateMovie(newMovie, newMovie);
        controller.setSelectedMovie(newMovie);
        view.echoOutput("Movie updated successfully\n");
    }

    private void viewReviews() {
        List<Reviews> reviews = model.fetchReview(String.format("movie_id = %d",controller.getSelectedMovie().getMovie_Id()));
        List<Users> users = model.fetchAllUsers();
        if (reviews.size() == 0) {
            view.echoOutput("No reviews posted yet\n");
            return;
        }
        String displayString = "";
        for(Reviews review : reviews) {
            displayString+= (review.getComment() + "\n");
            int upVotesCount = model.fetchReviewLike(String.format("review_id = %d and up_down_vote > 0", review.getReview_id())).size();
            int downVotesCount = model.fetchReviewLike(String.format("review_id = %d and up_down_vote < 0", review.getReview_id())).size();
            Users currentUser = users.stream().filter(obj -> obj.getUser_id() == review.getUser_id()).toArray(Users[]::new)[0];
            String reviewDetails = String.format("Id: %d | UserName: %s | Date: %s | UpVotes: %d | DownVotes: %d", review.getReview_id(), currentUser.getUserName(), review.getDate().toString(), upVotesCount, downVotesCount);
            displayString+= (reviewDetails + "\n");
        }
        view.echoOutput(displayString);
    }

    private void addReview() {
        Reviews newReview = new Reviews();
        String displayText =
                "-----------------------\n" +
                "| Enter review comment |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newReview.setComment(view.getInput());
        newReview.setUser_id(controller.getCurrentUser().getUser_id());
        newReview.setMovie_id(controller.getSelectedMovie().getMovie_Id());
        newReview.setDate(Date.valueOf(LocalDate.now()));
        model.createReview(newReview);
        view.echoOutput("Review posted\n");
    }

    private void deleteReview() {
        List<Reviews> reviewsList = model.fetchReview(String.format("movie_id = %d",controller.getSelectedMovie().getMovie_Id()));
        String displayText =
                "-----------------------\n" +
                "| Enter review id:    |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Reviews review : reviewsList) {
            if (review.getReview_id() == Integer.parseInt(input)) {
                view.echoOutput("Confirm delete comment(y/n):");
                if (view.getInput().equalsIgnoreCase("y")) {
                    model.deleteReview(review);
                    view.echoOutput("Review deleted successfully\n");
                }
                return;
            }
        }
        view.echoOutput("Invalid review Id\n");
    }

    private void likeTheMovie() {
        List<MovieLike> movieLikeList = model.fetchMovieLike(String.format("movie_id = %d and user_id = %d", controller.getSelectedMovie().getMovie_Id(), controller.getCurrentUser().getUser_id()));
        MovieLike object = new MovieLike();
        object.setUser_id(controller.getCurrentUser().getUser_id());
        object.setMovie_id(controller.getSelectedMovie().getMovie_Id());
        object.setUp_down_vote(1);
        if (movieLikeList.size() > 0) {
            model.updateMovieLike(object,object);
        }
        else  {
            model.createMovieLike(object);
        }
        view.echoOutput("UpVoted the movie\n");
    }

    private void dislikeTheMovie() {
        List<MovieLike> movieLikeList = model.fetchMovieLike(String.format("movie_id = %d and user_id = %d", controller.getSelectedMovie().getMovie_Id(), controller.getCurrentUser().getUser_id()));
        MovieLike object = new MovieLike();
        object.setUser_id(controller.getCurrentUser().getUser_id());
        object.setMovie_id(controller.getSelectedMovie().getMovie_Id());
        object.setUp_down_vote(-1);
        if (movieLikeList.size() > 0) {
            model.updateMovieLike(object,object);
        }
        else  {
            model.createMovieLike(object);
        }
        view.echoOutput("DownVoted the movie\n");
    }

    private void likeAReview() {
        List<Reviews> reviewsList = model.fetchReview(String.format("movie_id = %d",controller.getSelectedMovie().getMovie_Id()));
        String displayText =
                "-----------------------\n" +
                        "| Enter review id:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Reviews review : reviewsList) {
            if (review.getReview_id() == Integer.parseInt(input)) {
                List<ReviewLike> reviewLikeList = model.fetchReviewLike(String.format("user_id = %d and review_id = %d", controller.getCurrentUser().getUser_id(), review.getReview_id()));
                ReviewLike reviewLike = new ReviewLike();
                reviewLike.setReview_id(review.getReview_id());
                reviewLike.setUser_id(controller.getCurrentUser().getUser_id());
                reviewLike.setUp_down_vote(1);
                if (reviewLikeList.size() > 0) {
                    model.updateReviewLike(reviewLike, reviewLike);
                }
                else {
                    model.createReviewLike(reviewLike);
                }
                view.echoOutput("Successfully UpVoted the Review\n");
                return;
            }
        }
        view.echoOutput("Invalid review Id\n");
    }

    private void dislikeAReview() {
        List<Reviews> reviewsList = model.fetchReview(String.format("movie_id = %d",controller.getSelectedMovie().getMovie_Id()));
        String displayText =
                "-----------------------\n" +
                        "| Enter review id:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Reviews review : reviewsList) {
            if (review.getReview_id() == Integer.parseInt(input)) {
                List<ReviewLike> reviewLikeList = model.fetchReviewLike(String.format("user_id = %d and review_id = %d", controller.getCurrentUser().getUser_id(), review.getReview_id()));
                ReviewLike reviewLike = new ReviewLike();
                reviewLike.setReview_id(review.getReview_id());
                reviewLike.setUser_id(controller.getCurrentUser().getUser_id());
                reviewLike.setUp_down_vote(-1);
                if (reviewLikeList.size() > 0) {
                    model.updateReviewLike(reviewLike, reviewLike);
                }
                else {
                    model.createReviewLike(reviewLike);
                }
                view.echoOutput("Successfully DownVoted the Review\n");
                return;
            }
        }
        view.echoOutput("Invalid review Id\n");
    }
}
