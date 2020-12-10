package controller.modules;

import controller.IController;
import model.ModelImpl;
import view.IView;

public class WelcomeModule extends AbstractModule implements IModule {

    public WelcomeModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        showWelcomeText();
        return getNextModule();
    }

    private void showWelcomeText() {
        String welcomeText =
                "-----------------------\n" +
                        "| MOVIE REVIEW SYSTEM |\n" +
                        "-----------------------\n" +
                        "| 1 - Sign Up         |\n" +
                        "| 2 - Login           |\n" +
                        "| * - Quit            |\n" +
                        "-----------------------";
        view.echoOutput(welcomeText);
    }

    @Override
    protected Module getNextModule() {
        while(true) {
            String input = view.getInput();
            switch (input) {
                case "1":
                    return Module.SIGNUP;
                case "2":
                    return Module.LOGIN;
                case "*":
                    view.echoOutput("Thank you \uD83D\uDE0A");
                    System.exit(0);
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
                    showWelcomeText();
            }
        }
    }
}
