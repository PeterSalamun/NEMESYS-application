package model.callsAndExceptions.exceptions;

import model.utilities.inputCheck.ErrorSheetCreator;
import model.utilities.inputCheck.Errors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.util.ArrayList;

public class FileAlreadyExistsCall extends Exception {

    String path;
    ErrorSheetCreator errorSheetCreator;
    int response;

    public FileAlreadyExistsCall() {
    }

    public FileAlreadyExistsCall(String path) {
        this.path = path;
    }

    public void getCall() {
        response = JOptionPane.showConfirmDialog(null, "File already exists! Do you want " +
                        "to rewrite it?",
                "File already exists", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

    }

    public void savingErrors(XSSFWorkbook errorBook, ArrayList<Errors> errors) {
        errorSheetCreator = new ErrorSheetCreator(errorBook, errors);
    }

    public String getPath() {return path;}

    public boolean getResponse() {
        if(response == 0)
            return true;
        return false;
    }
}
