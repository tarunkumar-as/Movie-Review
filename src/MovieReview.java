import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ModelImpl;
import view.IView;
import view.ViewImpl;

import java.io.InputStreamReader;

public class MovieReview {

    public static void main(String[] args) {
        IView view = new ViewImpl(new InputStreamReader(System.in), System.out);
        IModel model = new ModelImpl();
        IController controller = new ControllerImpl(view, model);
        controller.startController();
    }
}
