package model.utilities.inputCheck;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import ui.AppInterface;

import java.util.ArrayList;

public class DataInputCheck extends BasicInputCheck {

    //Constructor tales AppInterface as parameter
    public DataInputCheck(AppInterface app) throws IncompatibleTypesExceptionCall {
        super(app);
    }

    //EFFECTS: initialize fields
    protected void initialize() {
        abundanceError = "Wrong abundance value.";
        guildError = "Taxon missing in database.";
        this.sheet = app.getSampleSheet();
        this.database = app.getDatabaseMap();
        errors = new ArrayList<>();
    }

    //EFFECTS: starts looking for possible errors in sheet - see lookingForWrongAbundances()
    // and lookingForRepetitionAndWrongNames(false) methods
    protected void lookingForErrors() throws IncompatibleTypesExceptionCall {

        lookingForRepetitionAndWrongNames(true);
        lookingForWrongAbundances();

        if (!errors.isEmpty())
            throw new IncompatibleTypesExceptionCall(errors);

        app.setSampleSourceChecked(true);
    }

    //EFFECTS: looking for wrong formatting of abundances data in the sheet. If any errors found new Errors instance is
    // created and add to ArrayList<> errors
    protected void lookingForWrongAbundances() {
        int columnNo = sheet.getRow(0).getLastCellNum();
        XSSFCell cell;
        String taxon;

        for (int i = 1; i < columnNo; i++) {
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {

                cell = sheet.getRow(j).getCell(i);

                if (isCellEmpty(cell))
                    break;

                String column = trimming(sheet.getRow(0).getCell(i));
                taxon = trimming(sheet.getRow(j).getCell(0));

                if (!cell.getCellType().equals(CellType.NUMERIC) || Double.parseDouble(trimming(cell)) < 0)
                    errors.add(new Errors(column, abundanceError, taxon, Integer.toString(j + 1), trimming(cell)));
            }
        }
    }
}
