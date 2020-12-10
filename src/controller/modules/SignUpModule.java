package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.UserDetail;
import model.objects.Users;
import view.IView;

import java.sql.Date;
import java.util.List;

public class SignUpModule extends AbstractModule implements IModule {

    public SignUpModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        return showSignUpText();
    }

    private Module showSignUpText() {
        List<Users> usersList = model.fetchAllUsers();

        Users newUser = new Users();
        UserDetail userDetail = new UserDetail();
        String displayText =
                        "-----------------------\n" +
                        "| Enter First Name:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        userDetail.setFname(view.getInput());

        displayText =
                        "-----------------------\n" +
                        "| Enter Last Name:     |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        userDetail.setLname(view.getInput());

        while(true) {
            displayText =
                            "-----------------------\n" +
                            "| Enter DOB(yyyy-mm-dd)|\n" +
                            "-----------------------";
            view.echoOutput(displayText);
            try {
                Date date = Date.valueOf(view.getInput());
                userDetail.setDob(date);
                break;
            } catch (Exception e) {
                view.echoOutput("Invalid date format. Enter again\n");
                continue;
            }
        }

        while(true) {
            displayText =
                            "-----------------------\n" +
                            "| Enter phone number: |\n" +
                            "-----------------------";
            view.echoOutput(displayText);
            try {
                userDetail.setPhone(Long.parseLong(view.getInput()));
                break;
            } catch (Exception e) {
                view.echoOutput("Invalid phone number. Enter again\n");
                continue;
            }
        }

        displayText =
                        "-----------------------\n" +
                        "| Enter user photo url:|\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        userDetail.setUser_photo(view.getInput());

        while(true) {
            displayText =
                            "-----------------------\n" +
                            "| Enter user name:    |\n" +
                            "-----------------------";
            view.echoOutput(displayText);
            String userName = view.getInput();
            boolean userNameExists = false;
            for (Users users : usersList) {
                if (users.getUserName().equalsIgnoreCase(userName)) {
                    userNameExists = true;
                    break;
                }
            }
            if (userNameExists) {
                view.echoOutput("Username taken. Choose another Username\n");
                continue;
            }
            else {
                newUser.setUserName(userName);
                break;
            }
        }

        displayText =
                        "-----------------------\n" +
                        "| Enter password:     |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        newUser.setPassword(view.getInput());

        while(true) {
            displayText =
                            "-----------------------\n" +
                            "| Enter role:         |\n" +
                            "| 1 - Admin           |\n" +
                            "| 2 - Critique        |\n" +
                            "| 3 - Viewer          |\n" +
                            "-----------------------";
            view.echoOutput(displayText);
            String role = view.getInput();
            boolean invalidInput = false;
            switch (role) {
                case "1":
                    newUser.setVerified(false);
                    newUser.setRole("Admin");
                    break;
                case "2":
                    newUser.setVerified(false);
                    newUser.setRole("Critique");
                    break;
                case "3":
                    newUser.setVerified(true);
                    newUser.setRole("Viewer");
                    break;
                default:
                    invalidInput = true;
            }
            if (invalidInput) {
                view.echoOutput("Invalid role. Enter valid role\n");
                continue;
            }
            else {
                break;
            }
        }

        return createUser(newUser, userDetail);
    }

    private Module createUser(Users newUser, UserDetail userDetail) {
        if (model.createUser(newUser)) {
            String query = String.format("userName = '%s' and password = '%s'", newUser.getUserName(), newUser.getPassword());
            Users currentUser = model.fetchUser(query).get(0);
            userDetail.setUser_id(currentUser.getUser_id());
            if (model.createUserDetail(userDetail)) {
                if (newUser.isVerified()) {
                    controller.setCurrentUser(currentUser);
                    return Module.MAINMENU;
                }
                else {
                    view.echoOutput("User created successfully. Please wait for Admin to approve your account\n");
                    return Module.WELCOME;
                }
            }
            else {
                model.deleteUser(currentUser);
            }
        }
        view.echoOutput("Unable to create account. Please try again\n");
        return Module.SIGNUP;
    }
}
