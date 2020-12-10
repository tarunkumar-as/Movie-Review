package controller.modules;

import controller.IController;
import model.ModelImpl;
import view.IView;

public class MovieModule extends AbstractModule implements IModule {

    public MovieModule(IView view, ModelImpl model, IController controller) {
        super(view, model, controller);
    }

    @Override
    public Module showModuleInfo() {
        return null;
    }
}
