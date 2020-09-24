package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class WrongExtensionExceptionCall extends Exception {

    public WrongExtensionExceptionCall() {

    }

    public void getCall() {
        JOptionPane.showConfirmDialog(null, "Unsupported file. Only *.xlsx files " +
                        "are supported.",
                "Extension error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

}
