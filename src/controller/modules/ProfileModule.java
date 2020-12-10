package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.UserDetail;
import model.objects.Users;
import view.IView;

import java.sql.Date;
import java.util.List;

public class ProfileModule extends AbstractModule implements IModule {

    public ProfileModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        return showProfileMenu();
    }

    private Module showProfileMenu() {
        while(true) {
            String displayText =
                    "-----------------------\n" +
                    "| SELECT AN OPTION    |\n" +
                    "-----------------------\n" +
                    "| 1 - View Profile    |\n" +
                    "| 2 - Update Profile  |\n" +
                    "| X - Delete Account  |\n" +
                    "|                     |\n" +
                    "| * - Back            |\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    viewProfile();
                    break;
                case "2":
                    return updateProfile();
                case "X":
                case "x":
                    return deleteAccount();
                case "*":
                    return Module.MAINMENU;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private void viewProfile() {
        Users user = model.fetchUser(String.format("user_id = %d",controller.getCurrentUser().getUser_id())).get(0);
        UserDetail userDetail = model.fetchUserDetail(String.format("user_id = %d",controller.getCurrentUser().getUser_id())).get(0);
        String displayText =
                "-----------------------\n" +
                " User Name : " + user.getUserName() + "\n" +
                " First Name : " + userDetail.getFname() + "\n" +
                " Last Name : " + userDetail.getLname() + "\n" +
                " Role : " + user.getRole() + "\n" +
                " DOB : " + userDetail.getDob() + "\n" +
                " Phone : " + userDetail.getPhone() + "\n" +
                " Photo URL : " + userDetail.getUser_photo() + "\n" +
                "-----------------------";
        view.echoOutput(displayText);
    }

    private Module updateProfile() {
        Users currentUser = controller.getCurrentUser();
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
                    if (!currentUser.getRole().equals("Admin")) {
                        newUser.setVerified(false);
                        newUser.setRole("Admin");
                    }
                    break;
                case "2":
                    if (!currentUser.getRole().equals("Critique")) {
                        newUser.setVerified(false);
                        newUser.setRole("Critique");
                    }
                    break;
                case "3":
                    if (!currentUser.getRole().equals("Viewer")) {
                        newUser.setVerified(true);
                        newUser.setRole("Viewer");
                    }
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
        userDetail.setUser_id(currentUser.getUser_id());
        model.updateUser(currentUser, newUser);
        model.updateUserDetail(userDetail,userDetail);
        if (currentUser.getRole().equals(newUser.getRole())) {
            view.echoOutput("Profile updated successfully\n");
            controller.setCurrentUser(newUser);
            return Module.PROFILE;
        }
        view.echoOutput("Profile updated successfully. Please wait for Admin to approve your account\n");
        return Module.WELCOME;
    }

    private Module deleteAccount() {
        view.echoOutput("Are you sure you want to delete you account (y/n) : ");
        String input = view.getInput();
        if (input.equals("y") || input.equals("Y")) {
            model.deleteUser(controller.getCurrentUser());
            controller.setCurrentUser(null);
            view.echoOutput("Account deleted successfully\n");
            return Module.WELCOME;
        }
        else {
            return Module.PROFILE;
        }
    }
}
