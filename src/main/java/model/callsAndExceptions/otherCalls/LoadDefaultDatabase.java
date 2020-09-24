package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class LoadDefaultDatabase extends BasicCall{

    private static final int OPTION_TYPE = JOptionPane.YES_NO_OPTION;
    private static final int MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;
    private static final String message = "Do you want to load default database?";
    private static final String title = "Load Default database";

    public LoadDefaultDatabase() {
        super(message, title);
    }

    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title, OPTION_TYPE, MESSAGE_TYPE);
    }
}
