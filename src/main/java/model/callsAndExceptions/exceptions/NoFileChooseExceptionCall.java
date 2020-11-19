package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class NoFileChooseExceptionCall extends Exception {

    public NoFileChooseExceptionCall() {
    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the user does not choose in JFileChooser
    // any file for opening or save (includes data file as well as custom database), return true
    public boolean getCall() {
        JOptionPane.showConfirmDialog(null, "No file has been choose!",
                "File choose error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}
