package controller.modules;

import controller.IController;
import model.ModelImpl;
import view.IView;

public class MainModule extends AbstractModule implements IModule {

    public MainModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        String currentRole = controller.getCurrentUser().getRole();
        switch (currentRole) {
            case "Admin":
                return showAdminMenu();
            case "Critique":
                return showCritiqueMenu();
            default:
                return showViewersMenu();
        }
    }

    private Module showViewersMenu() {
        while(true) {
            String displayText =
                    "-----------------------\n" +
                    "| SELECT MODULE       |\n" +
                    "-----------------------\n" +
                    "| M - Movie           |\n" +
                    "| P - Profile         |\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "M":
                case "m":
                    return Module.MOVIE_MAIN;
                case "P":
                case "p":
                    return Module.PROFILE;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private Module showCritiqueMenu() {
        while(true) {
            String displayText =
                    "-----------------------\n" +
                    "| SELECT MODULE       |\n" +
                    "-----------------------\n" +
                    "| M - Movie           |\n" +
                    "| U - Users           |\n" +
                    "| P - Profile         |\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "M":
                case "m":
                    return Module.MOVIE_MAIN;
                case "U":
                case "u":
                    return Module.USERS_MAIN;
                case "P":
                case "p":
                    return Module.PROFILE;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private Module showAdminMenu() {
        while(true) {
            String displayText =
                    "-----------------------\n" +
                    "| SELECT MODULE       |\n" +
                    "-----------------------\n" +
                    "| M - Movie           |\n" +
                    "| U - Users           |\n" +
                    "| P - Profile         |\n" +
                    "-----------------------";
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "M":
                case "m":
                    return Module.MOVIE_MAIN;
                case "U":
                case "u":
                    return Module.USERS_MAIN;
                case "P":
                case "p":
                    return Module.PROFILE;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }
}
