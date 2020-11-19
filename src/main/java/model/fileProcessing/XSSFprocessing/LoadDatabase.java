package model.fileProcessing.XSSFprocessing;

import model.computing.data.NematodesDatabase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.HashMap;

public class LoadDatabase {

    private HashMap<String, NematodesDatabase> database;
    private XSSFSheet sheet;

    //Constructor takes XSSFSheet as parameter
    public LoadDatabase(XSSFSheet sheet) {
        this.sheet = sheet;
        initialize();
        sheetIterator();
    }

    //EFFECTS: initialize field HashMap<String, NematodeDatabase>
    private void initialize() {
        database = new HashMap<>();
    }

    //MODIFIES: HashMap<String, NematodeDatabase>
    //EFFECTS: going through XSSFSheet and adding new input to the HashMap<String, NematodesDatabase> database
    protected void sheetIterator() {

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (isRowEmpty(row))
                break;
            String name = formatInput(row.getCell(0));
            database.put(name, addDatabaseInput(row, name));
        }
    }

    //REQUIRES: XSSFRow, String
    //EFFECTS: returns new NematodesDatabase instance with information obtained from the XSSFRow.
    private NematodesDatabase addDatabaseInput(XSSFRow row, String name) {
        double weight = 0;

        String guild = formatInput(row.getCell(1));
        if (row.getCell(2).getCellType() == CellType.NUMERIC)
            weight = Double.parseDouble(formatInput(row.getCell(2)));

        return new NematodesDatabase(name, guild, weight);
    }

    //REQUIRES: XSSFCell
    //MODIFIES: String
    //EFFECTS: trim content of the XSSFCell - String of any spaces
    protected String formatInput(XSSFCell str) {
        return str.toString().trim();
    }

    //REQUIRES: XSSFRow
    //EFFECTS: returns true if the row is empty, otherwise return false
    protected boolean isRowEmpty(XSSFRow row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();

        if (row != null)
            for (Cell cell : row)
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }


        return isEmpty;
    }

    //getters
    public HashMap<String, NematodesDatabase> getDatabaseMap() {
        return database;
    }
}
