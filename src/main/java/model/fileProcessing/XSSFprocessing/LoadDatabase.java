package model.fileProcessing.XSSFprocessing;

import model.computing.data.NematodesDatabase;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.HashMap;

public class LoadDatabase {

    HashMap<String, NematodesDatabase> database;
    XSSFSheet sheet;

    public LoadDatabase(XSSFSheet sheet) {
        this.sheet = sheet;
        initialize();
        sheetIterator();
    }

    private void initialize() {
        database = new HashMap<>();
    }

    protected void sheetIterator() {

        int rowLength = sheet.getRow(0).getLastCellNum();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (isRowEmpty(row))
                break;
            String name = formatInput(row.getCell(0));
            database.put(name, addDatabaseInput(row, rowLength, name));
        }
    }

    private NematodesDatabase addDatabaseInput(XSSFRow row, int cellCount, String name){
        double weight = 0;

        String guild = formatInput(row.getCell(1));
        if(row.getCell(2).getCellType() == CellType.NUMERIC)
            weight = Double.parseDouble(formatInput(row.getCell(2)));

        return new NematodesDatabase(name, guild, weight);
    }

    protected String formatInput(XSSFCell str) {
        return str.toString().trim();
    }

    protected boolean isRowEmpty(XSSFRow row) {
        return row.getCell(0).getCellType() == CellType.BLANK || row.getCell(0) == null;
    }

    public HashMap<String, NematodesDatabase> getDatabaseMap() {return database;}
}
