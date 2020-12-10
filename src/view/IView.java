package view;

public interface IView {

    public String getInput() throws IllegalStateException;

    public void echoOutput(String displayText) throws IllegalStateException, IllegalArgumentException;
}
