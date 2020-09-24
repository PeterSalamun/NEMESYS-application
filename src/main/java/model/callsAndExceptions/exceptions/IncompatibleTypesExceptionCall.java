package model.callsAndExceptions.exceptions;

import model.utilities.inputCheck.Errors;
import model.utilities.inputCheck.ErrorsDisplay;

import javax.swing.*;
import java.util.ArrayList;

public class IncompatibleTypesExceptionCall extends Exception {

    ArrayList<Errors> errorsArrayList;
    ErrorsDisplay errorDisplay;

    public IncompatibleTypesExceptionCall(ArrayList<Errors> errorsArrayList) {
        this.errorsArrayList = errorsArrayList;
    }

    public void getCall() {
        JOptionPane.showConfirmDialog(null, "Incompatible type of input. Please make " +
                        "corrections in the source file.",
                "Incompatible types error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    public void displayingErrors() {
        errorDisplay = new ErrorsDisplay(errorsArrayList);
    }
}
