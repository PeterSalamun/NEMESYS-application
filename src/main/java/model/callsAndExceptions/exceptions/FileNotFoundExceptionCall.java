package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class FileNotFoundExceptionCall extends Exception {

    public FileNotFoundExceptionCall() {}

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the user is trying to open a file which
    // does not exists, return true
    public boolean getCall() {
        JOptionPane.showConfirmDialog(null, "File you choose has not been found!",
                "File error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
