package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class SaveDataCall extends BasicCall {

    private static final int OPTION_TYPE = 1;
    private static final int MESSAGE_TYPE = 1;
    private static final String message = "Do you want to save the data?";
    private static final String title = "Save file";

    public SaveDataCall() {
        super(message, title);

    }

    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title,
                OPTION_TYPE, MESSAGE_TYPE);
    }
}
