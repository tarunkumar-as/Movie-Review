package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.Users;
import view.IView;

import java.util.List;

public class LoginModule extends AbstractModule implements IModule {

    private String userName;
    private String password;

    public LoginModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        while(true) {
            showLoginText();
            String query = String.format("userName = '%s' and password = '%s'", userName, password);
            List<Users> usersList = model.fetchUser(query);
            if (usersList.size() == 0) {
                view.echoOutput("Invalid User Name or Password. Enter again\n");
                continue;
            }
            else if (!usersList.get(0).isVerified()) {
                view.echoOutput("User not verified by Admin. Please try again later\n");
                return Module.WELCOME;
            }
            else {
                controller.setCurrentUser(usersList.get(0));
                return Module.MAINMENU;
            }
        }
    }

    private void showLoginText() {
        String displayText =
                        "-----------------------\n" +
                        "| Enter User Name:     |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        userName = view.getInput();
        displayText =
                "-----------------------\n" +
                "| Enter Password:      |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        password = view.getInput();
    }
}
