package view;

import java.io.IOException;
import java.util.Scanner;

public class ViewImpl implements IView {

    private final Readable reader;
    private final Appendable appendable;

    public ViewImpl(Readable reader, Appendable appendable) throws IllegalArgumentException {
        if ((reader == null) || (appendable == null)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.reader = reader;
        this.appendable = appendable;
    }

    @Override
    public String getInput() throws IllegalStateException {
        Scanner scanner = new Scanner(reader);
        if (!scanner.hasNext()) {
            throw new IllegalStateException("No inputs found");
        }
        return scanner.next();
    }

    @Override
    public void echoOutput(String displayText) throws IllegalStateException, IllegalArgumentException {
        if (displayText == null) {
            throw new IllegalArgumentException("Invalid text to display");
        }
        try {
            appendable.append(displayText + "\n");
        } catch (IOException e) {
            throw new IllegalStateException("IO exception caught");
        }
    }
}
