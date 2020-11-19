package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class FileSavedCall extends BasicCall{

    private static final int OPTION_TYPE = JOptionPane.DEFAULT_OPTION;
    private static final int MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;
    private static final String message = "Your file have been saved.";
    private static final String title = "File saved";

    //Constructor
    public FileSavedCall() {
        super(message, title);
    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of if the file has been successfully saved under the name and
    // in the place the user choose in JFileChooser, return int equall to the JOptionPane.OK_response
    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title, OPTION_TYPE, MESSAGE_TYPE);

    }
}
