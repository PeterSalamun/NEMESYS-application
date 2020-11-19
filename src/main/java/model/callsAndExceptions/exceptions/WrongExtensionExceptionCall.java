package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class WrongExtensionExceptionCall extends Exception {

    public WrongExtensionExceptionCall() {}

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the file choose by user in JFileChooser
    // has different extension than *.xlsx, return true
    public boolean getCall() {
        JOptionPane.showConfirmDialog(null, "Unsupported file. Only *.xlsx files " +
                        "are supported.",
                "Extension error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

}
