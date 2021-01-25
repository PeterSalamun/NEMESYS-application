package model_test.utilities_test.inputCheck_test;

import model.callsAndExceptions.exceptions.IncompatibleTypesExceptionCall;
import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.utilities.inputCheck.DataInputCheck;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DataInputCheckTest {

    DataInputCheck testData;
    AppInterface app;
    XSSFWorkbook book;
    Sample sa1, sa2, sa3, sa4, sa5, sa6, sa7, sa8, sa9;
    Site si1, si2, si3;
    String siteName;
    ArrayList<Site> sites;
    NematodesDatabase d1, d2, d3;
    HashMap<String, NematodesDatabase> database;

    @Before
    public void setUp() {
        book = new XSSFWorkbook();
        app = new AppInterface();
        sites = new ArrayList<>();

        d1 = new NematodesDatabase("Rhabditis", "B1", 0.8);
        d2 = new NematodesDatabase("Dorylaimus", "O4", 0.7);
        d3 = new NematodesDatabase("Helicotylenchus", "H2", 0.6);

        database = new HashMap<>();

        database.put(d1.getTaxaName(), d1);
        database.put(d2.getTaxaName(), d2);
        database.put(d3.getTaxaName(), d3);

        app.setDatabaseMap(database);

    }

    @Test
    public void testCorrectInput() {
        fillingUpNoError();
        try {
            testData = new DataInputCheck(app);
            assertEquals(testData.getErrors().size(), 0);
        } catch (IncompatibleTypesExceptionCall e) {
        }
    }

    @Test
    public void testWrongAbundances() {
        fillingUpAbundanceError();
        try {
            testData = new DataInputCheck(app);
        } catch (IncompatibleTypesExceptionCall e) {
            assertEquals(e.getErrorsArrayList().size(), 1);
        }
    }

    @Test
    public void testWrongNamesDiversity() {
        fillingUpNamesError();
        try {
            testData = new DataInputCheck(app);
        } catch (IncompatibleTypesExceptionCall e) {
            e.displayingErrors();
            assertEquals(e.getErrorsArrayList().size(), 1);
        }
    }

    @Test
    public void testWrongNamesNematodes() {
        fillingUpNamesError();
        app.setDiversityOn(false);
        try {
            testData = new DataInputCheck(app);
        } catch (IncompatibleTypesExceptionCall e) {
            e.displayingErrors();
            assertEquals(e.getErrorsArrayList().size(), 2);
        }
    }

    private ArrayList<Sample> fillArrayLists(Sample s1, Sample s2, Sample s3) {
        ArrayList<Sample> samples = new ArrayList<>();

        samples.add(s1);
        samples.add(s2);
        samples.add(s3);

        return samples;
    }

    private void fillInSheet() {
        XSSFSheet sheet = book.createSheet();
        XSSFRow row = sheet.createRow(0);

        for (int i = 0; i <= sites.size(); i++) {
            if (i == 0)
                row.createCell(i).setCellValue("Sites");
            else {
                row.createCell(i).setCellValue(sites.get(i - 1).getSiteName());

            }
        }


        for (int i = 1; i <= si1.getTaxaList().size(); i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(si1.getTaxaList().get(i - 1).getTaxaName());
        }

        for (int i = 1; i <= sites.size(); i++) {
            for (int j = 1; j <= si1.getTaxaList().size(); j++) {
                ArrayList<Sample> sample = sites.get(i - 1).getTaxaList();
                row = sheet.getRow(j);
                row.createCell(i).setCellValue(sample.get(j - 1).getAbundance());
            }
        }
        app.setSampleSheet(sheet);

    }

    private void testSheet(XSSFSheet sheet) {
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            testRow(sheet.getRow(i));
            System.out.println();
        }
    }

    private void testRow(Row row) {
        for (Cell c : row) {
            System.out.print(c + ", ");
        }
    }

    private void fillingUpNoError() {

        siteName = "Les";
        sa1 = new Sample("Rhabditis", 10, siteName, 1);
        sa2 = new Sample("Dorylaimus", 5, siteName, 2);
        sa3 = new Sample("Helicotylenchus", 7, siteName, 3);

        si1 = new Site(siteName, fillArrayLists(sa1, sa2, sa3));
        sites.clear();
        sites.add(si1);
        fillInSheet();
    }

    private void fillingUpAbundanceError() {
        siteName = "Luka";
        sa4 = new Sample("Rhabditis", 8, siteName, 1);
        sa5 = new Sample("Dorylaimus", -15, siteName, 2);
        sa6 = new Sample("Helicotylenchus", 0, siteName, 3);

        si1 = new Site(siteName, fillArrayLists(sa4, sa5, sa6));
        sites.clear();
        sites.add(si1);
        fillInSheet();

    }

    private void fillingUpNamesError() {
        siteName = "Pole";
        sa7 = new Sample("Rhabitis", 0, siteName, 1);
        sa8 = new Sample("Dorylaimus", 1, siteName, 2);
        sa9 = new Sample("Dorylaimus", 37.0, siteName, 3);

        si1 = new Site(siteName, fillArrayLists(sa7, sa8, sa9));
        sites.clear();
        sites.add(si1);

        fillInSheet();

    }


}
