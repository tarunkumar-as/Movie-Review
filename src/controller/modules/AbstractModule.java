package controller.modules;

import controller.IController;
import model.ModelImpl;
import view.IView;

public abstract class AbstractModule {

    protected final IView view;
    protected final ModelImpl model;
    protected final IController controller;

    AbstractModule(IView view, ModelImpl model, IController controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    protected Module getNextModule() {
        return Module.WELCOME;
    }
}
