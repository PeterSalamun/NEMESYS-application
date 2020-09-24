package model.utilities.inputCheck;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseCheck extends BasicInputCheck {

    private static final String[] abbreviations = {"B", "F", "H", "O", "P"};
    private static List<String> guildsList;

    public DatabaseCheck(AppInterface app) throws IncompatibleTypesExceptionCall {
        super(app);
    }

    @Override
    protected void initialize() {
        abundanceError = "Wrong weighting factor.";
        guildError = "Wrong guild.";
        guildsList = new ArrayList<>();
        guildsList = Arrays.asList(abbreviations);
        sheet = app.getDatabaseSheet();
        errors = new ArrayList<>();
    }

    @Override
    protected void lookingForErrors() throws IncompatibleTypesExceptionCall {

        lookingForWrongGuilds();
        lookingForWrongWeight();
        lookingForRepetitionAndWrongNames(false); //TODO zbytocne?

        if (!errors.isEmpty())
            throw new IncompatibleTypesExceptionCall(errors);

        app.setDatabaseChecked(true);
    }

    private void lookingForWrongGuilds() {
        String column = trimming(sheet.getRow(0).getCell(1));

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFCell taxonCell = sheet.getRow(i).getCell(0);
            XSSFCell guildCell = sheet.getRow(i).getCell(1);

            if (isCellEmpty(taxonCell))
                break;

            String taxon = trimming(taxonCell);
            String guild = trimming(guildCell);

            if (guild.length() != 2) {
                errors.add(new Errors(column, guildError, taxon, Integer.toString(i + 1), guild));
            } else {

                String sub1 = guild.substring(0, 1).toUpperCase();
                String sub2 = guild.substring(1);
                if (!NumberUtils.isCreatable(sub2) || !guildsList.contains(sub1) ||
                        (Integer.parseInt(sub2) < 1 || Integer.parseInt(sub2) > 5)) {
                    errors.add(new Errors(column, guildError, taxon, Integer.toString(i + 1), guild));

                }
            }
        }
    }

    protected void lookingForWrongWeight() {
        XSSFCell cell;
        String taxon = "";

        String column = trimming(sheet.getRow(0).getCell(2));

        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            cell = sheet.getRow(j).getCell(2);

            if (isCellEmpty(cell))
                break;

            if (!cell.getCellType().equals(CellType.NUMERIC) || Double.parseDouble(trimming(cell)) < 0) {
                taxon = trimming(sheet.getRow(j).getCell(0));
                errors.add(new Errors(column, abundanceError, taxon, Integer.toString(j + 1), trimming(cell)));
            }
        }
    }
}

