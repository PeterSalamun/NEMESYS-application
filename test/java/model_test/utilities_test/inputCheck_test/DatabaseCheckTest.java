package model_test.utilities_test.inputCheck_test;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import model.computing.data.NematodesDatabase;
import model.utilities.inputCheck.DatabaseCheck;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class DatabaseCheckTest {

    DatabaseCheck testData;
    AppInterface app;
    NematodesDatabase d1, d2, d3, d4;
    XSSFWorkbook book;
    TreeMap<String, NematodesDatabase> dataMap;


    @Before
    public void setUp() {
        app = new AppInterface();
        dataMap = new TreeMap<>();
        book = new XSSFWorkbook();
        d1 = new NematodesDatabase("ryba", "O5", 0);
        d2 = new NematodesDatabase("lev", "F2", 1.0);
        d3 = new NematodesDatabase("bocian", "F4", 5.0);

        dataMap.put(d1.getTaxaName(), d1);
        dataMap.put(d2.getTaxaName(), d2);
        dataMap.put(d3.getTaxaName(), d3);
    }

    @Test
    public void testInputWithoutErrors() throws IncompatibleTypesExceptionCall {
        fillInSheet();
        testData = new DatabaseCheck((app));
        assertEquals(testData.getErrors().size(), 0);
    }

    @Test
    public void testLookingForWrongGuilds() {
        d2 = new NematodesDatabase("lev", "F8", 1.0);
        dataMap.put(d2.getTaxaName(), d2);
        fillInSheet();
        try {
            testData = new DatabaseCheck(app);
        }catch(IncompatibleTypesExceptionCall e) {
            assertEquals(e.getErrorsArrayList().get(0).getErrorValue(), "F8");
        }
    }

    @Test
    public void testLookingForWrongWeight() {
        d2 = new NematodesDatabase("lev", "F2", -1.0);
        dataMap.put(d2.getTaxaName(), d2);
        fillInSheet();
        try {
            testData = new DatabaseCheck(app);
        }catch(IncompatibleTypesExceptionCall e) {
            assertEquals(e.getErrorsArrayList().get(0).getErrorValue(), "-1.0");
        }
    }

    @Test
    public void testLookingForRepetitionAndWrongNames() {
        d3 = new NematodesDatabase("lev", "F2", 1.0);
        dataMap.put("bocian", d3);
        fillInSheet();
        try {
            testData = new DatabaseCheck(app);
        }catch(IncompatibleTypesExceptionCall e) {
            assertEquals(e.getErrorsArrayList().get(0).getErrorValue(), "bocian");
        }
    }

    @Test
    public void testAllErrors() {
        d3 = new NematodesDatabase("lev", "F8", -1.0);
        dataMap.put("bocian", d3);
        fillInSheet();
        try {
            testData = new DatabaseCheck(app);
        }catch(IncompatibleTypesExceptionCall e) {
            assertEquals(e.getErrorsArrayList().get(0).getErrorValue(), "F8");
            assertEquals(e.getErrorsArrayList().get(1).getErrorValue(), "-1.0");
            assertEquals(e.getErrorsArrayList().get(2).getErrorValue(), "bocian");
        }
    }

    private void fillInSheet() {
        XSSFSheet sheet = book.createSheet();
        for(Map.Entry<String, NematodesDatabase> entry: dataMap.entrySet()){
            int index = sheet.getLastRowNum();
            XSSFRow row = sheet.createRow(++index);
            NematodesDatabase database = entry.getValue();
            row.createCell(0).setCellValue(database.getTaxaName());
            row.createCell(1).setCellValue(database.getGuild());
            row.createCell(2).setCellValue(database.getWeight());
        }
        app.setDatabaseSheet(book.getSheetAt(0));

    }

}
