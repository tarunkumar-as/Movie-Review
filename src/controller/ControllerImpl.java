package controller;

import model.IModel;
import view.IView;

public class ControllerImpl implements IController {

    private final IView view;
    private final IModel model;

    public ControllerImpl(IView view, IModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void startController() {
        
    }
}