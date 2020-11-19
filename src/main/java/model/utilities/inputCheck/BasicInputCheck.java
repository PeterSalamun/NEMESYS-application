package model.utilities.inputCheck;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import model.computing.data.NematodesDatabase;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class BasicInputCheck {

    protected String abundanceError;
    protected String guildError;
    protected AppInterface app;
    protected XSSFSheet sheet;
    protected HashMap<String, NematodesDatabase> database;
    protected ArrayList<Errors> errors;
    private final String duplicateError = "Duplicity error";

    //Constructor takes AppInterface as parameter
    public BasicInputCheck(AppInterface app) throws IncompatibleTypesExceptionCall {
        this.app = app;
        initialize();
        lookingForErrors();
    }

    //REQUIRES: boolean
    //EFFECTS: check the first column of a sheet for Strings that repeats or could not be found in the database
    // if isDiversityOn == false
    protected void lookingForRepetitionAndWrongNames(boolean databaseFlag) {
        HashSet<String> namesSet = new HashSet<>();
        String column = trimming(sheet.getRow(0).getCell(0));

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFCell cell = sheet.getRow(i).getCell(0);
            if (isCellEmpty(cell))
                break;

            else {
                String taxon = trimming(cell);

                if (databaseFlag && !database.containsKey(taxon) && !app.isDiversityOn()) {
                    errors.add(new Errors(column, guildError, taxon, Integer.toString(i + 1), taxon));
                }

                if (!namesSet.add(taxon)) {
                    errors.add(new Errors(column, duplicateError, taxon, Integer.toString(i + 1), taxon));
                }
            }
        }
    }

    //EFFECTS: abstract methods
    protected abstract void initialize();

    protected abstract void lookingForErrors() throws IncompatibleTypesExceptionCall;

    //REQUIRES: XSSFCell
    //MODIFIES: String
    //EFFECTS: trim content of the XSSFCell - String of any spaces
    protected String trimming(XSSFCell c) {
        return c.toString().trim();
    }

    //REQUIRES: XSSFCell
    //EFFECTS: return true if XSSFCell is empty
    protected boolean isCellEmpty(XSSFCell cell) {
        return (cell == null || cell.getCellType().equals(CellType.BLANK));
    }

    //getters
    public ArrayList<Errors> getErrors() {
        return errors;
    }
}
