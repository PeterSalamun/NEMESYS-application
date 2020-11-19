package model.callsAndExceptions.otherCalls;

import javax.swing.*;

public class SaveDataCall extends BasicCall {

    private static final int OPTION_TYPE = 1;
    private static final int MESSAGE_TYPE = 1;
    private static final String message = "Do you want to save the data?";
    private static final String title = "Save file";

    //Constructor
    public SaveDataCall() {
        super(message, title);

    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of if there are data ready to save and user is closing
    // window without saving the data,return int depending on the option in OptionPane.showConfirmDialog()
    @Override
    public int getCall() {
        return JOptionPane.showConfirmDialog(null, message, title,
                OPTION_TYPE, MESSAGE_TYPE);
    }
}
