package model.callsAndExceptions.exceptions;

import javax.swing.*;

public class IOExceptionCall extends Exception {


    public IOExceptionCall() {

    }

    public void getCall() {
        JOptionPane.showConfirmDialog(null, "File can not be opened!",
                "File Open Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);}
}
