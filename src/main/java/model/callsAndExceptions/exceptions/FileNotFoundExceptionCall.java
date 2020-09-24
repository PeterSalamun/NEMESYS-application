package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class FileNotFoundExceptionCall extends Exception {

    public FileNotFoundExceptionCall() {

    }

    public void getCall() {
        JOptionPane.showConfirmDialog(null, "File you choose has not been found!",
                "File error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }
}
