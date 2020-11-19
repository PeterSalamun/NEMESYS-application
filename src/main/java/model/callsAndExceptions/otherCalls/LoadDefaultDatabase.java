package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class LoadDefaultDatabase extends BasicCall{

    private static final int OPTION_TYPE = JOptionPane.YES_NO_OPTION;
    private static final int MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;
    private static final String message = "Do you want to load default database?";
    private static final String title = "Load Default database";

    //Constructor
    public LoadDefaultDatabase() {
        super(message, title);
    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of if the user previously upload custom database and click
    // OpenDatabaseButton repeatedly. The OptionPane gives user the option to upload either custom
    // Database - JOptionPane.NO_response (method return int == 1) or default built-in database
    // JOptionPane.YES_response (method return int == 0), return int depending on the option in 
    // OptionPane.showConfirmDialog()
    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title, OPTION_TYPE, MESSAGE_TYPE);}
}
