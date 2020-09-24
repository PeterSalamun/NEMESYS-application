package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class NoFileChooseCall extends Exception {

    public NoFileChooseCall() {
    }

    public void getCall() {
        JOptionPane.showConfirmDialog(null, "No file has been choose!",
                "File choose error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
}
