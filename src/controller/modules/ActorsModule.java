package controller.modules;

import controller.IController;
import model.ModelImpl;
import model.objects.Actors;
import model.objects.Movies;
import view.IView;

import java.util.List;

public class ActorsModule extends AbstractModule implements IModule {

    public ActorsModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        String displayText =
                "-----------------------\n" +
                "| SELECT AN OPTION    |\n" +
                "-----------------------\n" +
                "| 1 - Get List of Actors\n" +
                "| 2 - Get Actor Detail\n" +
                "| 3 - Add Actor       |\n" +
                "| 4 - Update Actor    |\n" +
                "| 5 - Delete Actor    |\n" +
                "|                     |\n" +
                "| 0 - Back            |\n" +
                "-----------------------";

        while(true) {
            view.echoOutput(displayText);
            String input = view.getInput();
            switch (input) {
                case "1":
                    listActors();
                    return Module.ACTORS;
                case "2":
                    getActorByDetail();
                    return Module.ACTORS;
                case "3":
                    addActor();
                    return Module.ACTORS;
                case "4":
                    updateActor();
                    return Module.ACTORS;
                case "5":
                    deleteActor();
                    return Module.ACTORS;
                case "0":
                    return Module.MOVIE_MAIN;
                default:
                    view.echoOutput("Invalid Input. Enter valid input\n");
            }
        }
    }

    private void listActors() {
        List<Actors> actorsList = model.fetchAllActors();
        displayActorsInList(actorsList);
    }

    private void getActorByDetail() {
        view.echoOutput("Enter actor name: ");
        String input = view.getInput();
        List<Actors> actorsList = model.fetchActor("name = '" + input +"'");
        displayActorsInList(actorsList);
    }

    private void displayActorsInList(List<Actors> actorsList) {
        String displayText =
                "-----------------------\n" +
                "| Actors List         |\n" +
                "-----------------------\n";

        for (Actors actor : actorsList) {
            StringBuilder actorsText = new StringBuilder("Actor Name : " + actor.getName() + "\n" +
                    "Photo URL : " + actor.getPhoto_url() + "\n" +
                    "Sex : " +actor.getSex() + "\n\n");
            displayText += actorsText.toString();
        }
        if (actorsList.size() == 0) {
            view.echoOutput("No actors to display\n");
        }
        else {
            view.echoOutput(displayText);
        }
    }

    private void addActor() {
        List<Actors> actorsList = model.fetchAllActors();
        Actors newActor = new Actors();
        String displayText;
        displayText =
                "-----------------------\n" +
                "| Enter Actor Name:    |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        boolean actorExists = false;
        for (Actors actors : actorsList) {
            if (actors.getName().equalsIgnoreCase(input)) {
                actorExists = true;
                break;
            }
        }
        if (actorExists) {
            view.echoOutput("Actor profile already exists in the database\n");
            return;
        }
        newActor.setName(input);


        displayText =
                "-----------------------\n" +
                "| Enter Photo URL:    |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newActor.setPhoto_url(view.getInput());

        displayText =
                "-----------------------\n" +
                "| Enter SEX:          |\n" +
                "-----------------------";
        view.echoOutput(displayText);
        newActor.setSex(view.getInput().charAt(0));

        model.createActor(newActor);
        view.echoOutput("Actor profile created usccessfully");
    }

    private void updateActor() {
        List<Actors> actorsList = model.fetchAllActors();
        Actors newActor = new Actors();
        String displayText;
        displayText =
                "-----------------------\n" +
                        "| Enter Actor Name:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        boolean actorExists = false;
        for (Actors actors : actorsList) {
            if (actors.getName().equalsIgnoreCase(input)) {
                actorExists = true;
                newActor = actors;
                break;
            }
        }
        if (!actorExists) {
            view.echoOutput("Invalid actor name\n");
            return;
        }
        displayText =
                "-----------------------\n" +
                        "| Enter Photo URL:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        newActor.setPhoto_url(view.getInput());
        model.updateActor(newActor, newActor);
        view.echoOutput("Actor information updated successfully\n");
    }

    private void deleteActor() {
        List<Actors> actorsList = model.fetchAllActors();
        Actors newActor = new Actors();
        String displayText;
        displayText =
                "-----------------------\n" +
                        "| Enter Actor Name:    |\n" +
                        "-----------------------";
        view.echoOutput(displayText);
        String input = view.getInput();
        boolean actorExists = false;
        for (Actors actors : actorsList) {
            if (actors.getName().equalsIgnoreCase(input)) {
                actorExists = true;
                newActor = actors;
                break;
            }
        }
        if (!actorExists) {
            view.echoOutput("Invalid actor name\n");
            return;
        }
        view.echoOutput("Are you sure you want to delete the Actor and their related data?(y/n)");
        input = view.getInput();
        if (input.equalsIgnoreCase("y")) {
            model.deleteActor(newActor);
            view.echoOutput("Actor information deleted successfully\n");
        }
    }
}
