import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ModelImpl;
import view.IView;
import view.ViewImpl;

public class MovieReview {

    public static void main(String[] args) {
        IView view = new ViewImpl();
        IModel model = new ModelImpl();
        IController controller = new ControllerImpl(view, model);
        controller.startController();
    }
}
