package model.callsAndExceptions.exceptions;

import model.utilities.inputCheck.Errors;
import model.utilities.inputCheck.ErrorsDisplay;

import javax.swing.*;
import java.util.ArrayList;

public class IncompatibleTypesExceptionCall extends Exception {

    ArrayList<Errors> errorsArrayList;
    ErrorsDisplay errorDisplay;

    //Constructor
    public IncompatibleTypesExceptionCall(ArrayList<Errors> errorsArrayList) {
        this.errorsArrayList = errorsArrayList;
    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the data provided by user contains any
    // data which are not in accordance with the templates, return true
    public boolean getCall() {
        JOptionPane.showConfirmDialog(null, "Incompatible type of input. Please make " +
                        "corrections in the file.",
                "Incompatible types error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        return true;
    }

    //EFFECTS: calls ErrorDisplay class for displaying errors found oi the provided file. Goes for data source file as
    // well as custom database
    public boolean displayingErrors() {
        errorDisplay = new ErrorsDisplay(errorsArrayList);
        return true;
    }

    //getters
    public ArrayList<Errors> getErrorsArrayList() {
        return errorsArrayList;
    }

}
