package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class IOExceptionCall extends Exception {


    public IOExceptionCall() {}

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the file provided by user cannot be opened
    // or there are other problems with reading the file, return true
    public boolean getCall() {
        JOptionPane.showConfirmDialog(null, "File can not be opened!",
                "File Open Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
