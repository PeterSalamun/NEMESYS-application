package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class FileSavedCall extends BasicCall{

    private static final int OPTION_TYPE = -1;
    private static final int MESSAGE_TYPE = 1;
    private static final String message = "Your file have been saved.";
    private static final String title = "File saved";

    public FileSavedCall() {
        super(message, title);

    }

    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title, OPTION_TYPE, MESSAGE_TYPE);

    }
}
