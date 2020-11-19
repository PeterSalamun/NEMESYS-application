package model.callsAndExceptions.exceptions;

import model.utilities.inputCheck.ErrorSheetCreator;
import model.utilities.inputCheck.Errors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.util.ArrayList;

public class FileAlreadyExistsExceptionCall extends Exception {

    private final String path;
    ErrorSheetCreator errorSheetCreator;
    int response;

    //Constructor
    public FileAlreadyExistsExceptionCall(String path) {
        this.path = path;
    }

    //REQUIRES: XSSFWorkbook, ArrayList<Errors>
    //EFFECTS: calls ErrorSheetCreator class which creates XSSFSheet in XSSFWorkbook where or found errors in provided
    // data file are written
    public void savingErrors(XSSFWorkbook errorBook, ArrayList<Errors> errors) {
        errorSheetCreator = new ErrorSheetCreator(errorBook, errors);
    }

    //EFFECTS: Shows JOptionPane.showConfirmDialog in case of an exception if the user is trying to save file with name
    // and place where already the same file exists, return true
    public boolean getCall() {
        response = JOptionPane.showConfirmDialog(null, "File already exists! Do you want " +
                        "to rewrite it?",
                "File already exists", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    //getters
    public String getPath() {return path;}

    public boolean getResponse() {
        return response == 0;
    }
}
