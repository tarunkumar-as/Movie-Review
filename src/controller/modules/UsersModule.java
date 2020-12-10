package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.Actors;
import model.objects.Movies;
import model.objects.Reviews;
import model.objects.Users;
import view.IView;

import java.util.ArrayList;
import java.util.List;

public class UsersModule extends AbstractModule implements IModule {

    public UsersModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        Users currentUser = controller.getCurrentUser();
        switch (currentUser.getRole()) {
            case "Admin":
                return adminUserModules();
            default:
                return critiqueUserModules();
        }
    }

    private Module adminUserModules() {
        String displayText =
                "-----------------------\n" +
                "| SELECT AN OPTION    |\n" +
                "-----------------------\n" +
                "| 1 - List all Users  |\n" +
                "| 2 - Remove User     |\n" +
                "| 3 - Approve Users   |\n" +
                "| 4 - View User Activity\n"+
                "|                     |\n" +
                "| * - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    listAllUsers();
                    return Module.USERS_MAIN;
                case "2":
                    removeUser();
                    return Module.USERS_MAIN;
                case "3":
                    approveUsers();
                    return Module.USERS_MAIN;
                case "4":
                    viewUserActivity();
                    return Module.USERS_MAIN;
                case "*":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private Module critiqueUserModules() {
        String displayText =
                "-----------------------\n" +
                        "| SELECT AN OPTION    |\n" +
                        "-----------------------\n" +
                        "| 1 - List all Critiques\n" +
                        "| 2 - View Critique Activity\n"+
                        "|                     |\n" +
                        "| * - Back            |\n" +
                        "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    listAllCritiques();
                    return Module.USERS_MAIN;
                case "2":
                    viewUseCritiqueActivity();
                    return Module.USERS_MAIN;
                case "*":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private void listAllUsers() {
        List<Users> usersList = model.fetchAllUsers();
        String displayText =
                "-----------------------\n" +
                "| Users List          |\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        for (Users user : usersList) {
            String userText = "User ID : " + user.getUser_id() + "\n" +
                    "User Name : " + user.getUserName() + "\n" +
                    "Role : " + user.getRole() + "\n" +
                    "Is Verified : " + user.isVerified() + "\n";
            view.echoOutput(userText);
        }
    }

    private void removeUser() {
        List<Users> usersList = model.fetchAllUsers();
        String displayText =
                "-----------------------\n" +
                "| Enter User Name to remove\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Users user : usersList) {
            if (user.getUserName().equalsIgnoreCase(input)) {
                view.echoOutput("Confirm delete user(y/n):\n");
                if (view.getInput().equalsIgnoreCase("y")) {
                    model.deleteUser(user);
                    view.echoOutput("User removed successfully\n");
                    return;
                }
            }
        }
        view.echoOutput("Invalid user name provided\n");
    }

    private void approveUsers() {
        List<Users> usersList = model.fetchUser("is_verified = false");
        String displayText =
                "-----------------------\n" +
                "| Enter User Name to approve\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Users user : usersList) {
            if (user.getUserName().equalsIgnoreCase(input)) {
                view.echoOutput("Confirm approve user(y/n):\n");
                if (view.getInput().equalsIgnoreCase("y")) {
                    user.setVerified(true);
                    model.updateUser(user, user);
                    view.echoOutput("User approved successfully\n");
                    return;
                }
            }
        }
        view.echoOutput("Invalid user name provided\n");
    }

    private void viewUserActivity() {
        List<Users> usersList = model.fetchAllUsers();
        String displayText =
                "-----------------------\n" +
                "| Enter User Name to view their activity\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Users user : usersList) {
            if (user.getUserName().equalsIgnoreCase(input)) {
                List<Reviews> reviewsList = model.fetchReview(String.format("user_id = %d", user.getUser_id()));
                boolean hasContent = false;
                for (Reviews reviews : reviewsList) {
                    hasContent = true;
                    Movies movie = model.fetchMovie(String.format("movie_id = %d", reviews.getMovie_id())).get(0);
                    displayText = String.format("Date: %s | Comment: %s | Movie Name: %s\n", reviews.getDate().toString(), reviews.getComment(), movie.getMovie_name());
                    view.echoOutput(displayText);
                }
                if (!hasContent) {
                    view.echoOutput("No user activity found\n");
                }
                return;
            }
        }
        view.echoOutput("Invalid user name provided\n");
    }

    private void listAllCritiques() {
        List<Users> usersList = model.fetchUser("role = 'Critique'");
        String displayText =
                "-----------------------\n" +
                "| Users List          |\n" +
                "-----------------------\n";
        view.echoOutput(displayText);
        for (Users user : usersList) {
            String userText = "User ID : " + user.getUser_id() + "\n" +
                    "User Name : " + user.getUserName() + "\n" +
                    "Role : " + user.getRole() + "\n";
            view.echoOutput(userText);
        }
    }

    private void viewUseCritiqueActivity() {
        List<Users> usersList = model.fetchUser("role = 'Critique'");
        String displayText =
                "-----------------------\n" +
                        "| Enter User Name to view their activity\n" +
                        "-----------------------\n";
        view.echoOutput(displayText);
        String input = view.getInput();
        for (Users user : usersList) {
            if (user.getUserName().equalsIgnoreCase(input)) {
                List<Reviews> reviewsList = model.fetchReview(String.format("user_id = %d", user.getUser_id()));
                boolean hasContent = false;
                for (Reviews reviews : reviewsList) {
                    hasContent = true;
                    Movies movie = model.fetchMovie(String.format("movie_id = %d", reviews.getMovie_id())).get(0);
                    displayText = String.format("Date: %s | Comment: %s | Movie Name: %s\n", reviews.getDate().toString(), reviews.getComment(), movie.getMovie_name());
                    view.echoOutput(displayText);
                }
                if (!hasContent) {
                    view.echoOutput("No user activity found\n");
                }
                return;
            }
        }
        view.echoOutput("Invalid user name provided\n");
    }
}
