package model_test.fileProcessing_test.XSSFprocessing_test;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.fileProcessing.XSSFprocessing.SamplesProcessing;
import model.fileProcessing.fileManagement.OpenDatabase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SamplesProcessingTest {

    SamplesProcessing t1, t2, t3;
    AppInterface app;
    XSSFWorkbook book;


    private Site s1, s2, s3;
    private Sample v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15;
    private ArrayList<Sample> sa1, sa2, sa3;
    private ArrayList<Site> sites;

    @Before
    public void setUp() {

        app = new AppInterface();
        book = new XSSFWorkbook();
        sa1 = new ArrayList<>();
        sa2 = new ArrayList<>();
        sa3 = new ArrayList<>();
        sites = new ArrayList<>();

        v1 = new Sample("Rhabditis", 25, "Les", 1);
        v2 = new Sample("Dorylaimus", 5, "Les", 2);
        v3 = new Sample("Helicotylenchus", 15, "Les", 3);
        v4 = new Sample("Mononchus", 7, "Les", 4);
        v5 = new Sample("Aphelenchus", 8, "Les", 5);
        addSamplesToArray(v1, v2, v3, v4, v5, sa1);
        s1 = new Site("Les", sa1);


        v6 = new Sample("Rhabditis", 8, "Luka", 1);
        v7 = new Sample("Dorylaimus", 5, "Luka", 2);
        v8 = new Sample("Helicotylenchus", 1, "Luka", 3);
        v9 = new Sample("Mononchus", 9, "Luka", 4);
        v10 = new Sample("Aphelenchus", 4, "Luka", 5);
        addSamplesToArray(v6, v7, v8, v9, v10, sa2);
        s2 = new Site("Luka", sa2);


        v11 = new Sample("Rhabditis", 18, "Pole", 1);
        v12 = new Sample("Dorylaimus", 65, "Pole", 2);
        v13 = new Sample("Helicotylenchus", 23, "Pole", 3);
        v14 = new Sample("Mononchus", 5, "Pole", 4);
        v15 = new Sample("Aphelenchus", 0, "Pole", 5);
        addSamplesToArray(v11, v12, v13, v14, v15, sa3);
        s3 = new Site("Pole", sa3);

        List<Site> lokality = Arrays.asList(s1, s2, s3);
        sites.addAll(lokality);

        fillInSheet();
    }

    @Test
    public void testOnlyDominancySheet() {
        t1 = new SamplesProcessing(app);
        Workbook testBook = t1.getResultBook();
        //sheetCheck(testBook.getSheetAt(0));

        Sheet sheet = testBook.getSheetAt(0);
        assertEquals(testBook.getNumberOfSheets(), 1);
        assertEquals(testBook.getSheetAt(0).getSheetName(), "Taxa dominancy (%)");

        assertEquals(testBook.getSheetAt(0).getRow(0).getLastCellNum(), 6);
        assertEquals(testBook.getSheetAt(0).getPhysicalNumberOfRows(), 6);

        testStringHeader(sheet, app.getTaxaList(), "Sample/Taxon");
        testFirstColumn(sheet, 1);
        testCellContent(sheet, 1);

    }

    @Test
    public void testDiversitySheet() {

        ArrayList<BasicCheckBox> boxes = app.getOptionsPane().getDiversityPanel().getCheckBoxesList();
        activateBoxes(boxes);

        t2 = new SamplesProcessing(app);
        Workbook testBook = t2.getResultBook();
        assertEquals(testBook.getNumberOfSheets(), 2);
        assertEquals(testBook.getSheetAt(1).getSheetName(), "Taxa dominancy (%)");
        assertEquals(testBook.getSheetAt(0).getSheetName(), "Diversity Indices");

        Sheet sheet = testBook.getSheetAt(0);

        testBasicBoxHeader(sheet, boxes);
        testFirstColumn(sheet, 1);
        testCellContent(sheet, 1);
    }

    @Test
    public void testNematodeSheets() throws IOException {

        String[] CP_GROUPS = {"CP1 (%)", "CP2 (%)", "CP3 (%)", "CP4 (%)", "CP5 (%)", "Other (%)"};
        String[] TROPHIC_GROUPS = {"Bacterivore (%)", "Fungivore (%)", "Herbivore (%)", "Omnivore (%)", "Predator (%)", "Other (%)",};

        ArrayList<BasicCheckBox> div = app.getOptionsPane().getDiversityPanel().getCheckBoxesList();
        ArrayList<BasicCheckBox> foo = app.getOptionsPane().getFootprintsPanel().getCheckBoxesList();
        ArrayList<BasicCheckBox> fun = app.getOptionsPane().getFunctionalPanel().getCheckBoxesList();

        activateBoxes(div);
        activateBoxes(foo);
        activateBoxes(fun);

        app.setDiversityOn(false);
        OpenDatabase data = new OpenDatabase("/fullDatabase.xlsx");
        app.setDatabaseMap(data.getDatabaseMap());
        t3 = new SamplesProcessing(app);

        Workbook testBook = t3.getResultBook();
        assertEquals(testBook.getNumberOfSheets(), 6);
        assertEquals(testBook.getSheetAt(0).getSheetName(), "Diversity Indices");
        assertEquals(testBook.getSheetAt(1).getSheetName(), "Functional Indices");
        assertEquals(testBook.getSheetAt(2).getSheetName(), "Footprint Indices");
        assertEquals(testBook.getSheetAt(3).getSheetName(), "CP groups (%)");
        assertEquals(testBook.getSheetAt(4).getSheetName(), "Trophic groups (%)");
        assertEquals(testBook.getSheetAt(5).getSheetName(), "Taxa dominancy (%)");

        testSheets(testBook.getSheetAt(0), div);
        testSheets(testBook.getSheetAt(1), fun);
        test3Sheet(testBook.getSheetAt(2), foo);
        testGroupsSheet(testBook.getSheetAt(3), CP_GROUPS);
        testGroupsSheet(testBook.getSheetAt(4), TROPHIC_GROUPS);

        testNematodeDominancy(testBook.getSheetAt(5));
    }

    private void testGroupsSheet(Sheet sheet, String[] list) {
        ArrayList<String> l = new ArrayList<>(Arrays.asList(list));
        testStringHeader(sheet, l, "Sample/Index");
        testFirstColumn(sheet, 1);
        testCellContent(sheet, 1);
    }

    private void testNematodeDominancy(Sheet sheet) {
        testStringHeader(sheet, app.getTaxaList(), "Sample/Taxon");
        testGuildRow(sheet);
        testFirstColumn(sheet, 3);
        testCellContent(sheet, 2);
        testMass(sheet);
    }

    private void testSheets(Sheet sheet, ArrayList<BasicCheckBox> list) {
        testBasicBoxHeader(sheet, list);
        testFirstColumn(sheet, 1);
        testCellContent(sheet, 1);
    }

    private void test3Sheet(Sheet sheet, ArrayList<BasicCheckBox> list) {
        testBasicBoxHeader2(sheet, list);
        testFirstColumn(sheet, 1);
        testCellContent(sheet, 1);
    }

    private void testStringHeader(Sheet sheet, ArrayList<String> list, String str) {
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            if (i == 0)
                assertEquals(sheet.getRow(0).getCell(i).toString(), str);
            else
                assertEquals(sheet.getRow(0).getCell(i).toString(), list.get(i - 1));
        }
    }

    private void testBasicBoxHeader(Sheet sheet, ArrayList<BasicCheckBox> boxes) {
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            Cell c = sheet.getRow(0).getCell(i);
            if (i == 0)
                assertEquals(c.toString(), "Sample/Index");
            else
                assertEquals(c.toString(), boxes.get(i - 1).getName());
        }
    }

    private void testBasicBoxHeader2(Sheet sheet, ArrayList<BasicCheckBox> boxes) {
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            Cell c = sheet.getRow(0).getCell(i);
            if (i == 0)
                assertEquals(c.toString(), "Sample/Index");
            else if (i == sheet.getRow(0).getLastCellNum() - 1)
                assertEquals(c.toString(), "Total sample mass (ug)");
            else
                assertEquals(c.toString(), boxes.get(i - 1).getName());
        }
    }

    private void testFirstColumn(Sheet sheet, int start) {
        for (int i = start; i < sites.size() + start; i++) {
            Cell c = sheet.getRow(i).getCell(0);
            assertEquals(c.toString(), sites.get(i-start).getSiteName());
        }
    }

    private void testCellContent(Sheet sheet, int start) {
        for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++) {
            for (int j = start; j < sites.size() + start; j++) {
                Cell c = sheet.getRow(j).getCell(i);
                assertTrue(c != null && c.getCellType() == CellType.NUMERIC);
            }
        }
    }

    private void testGuildRow(Sheet s) {
        Row taxonRow = s.getRow(0);
        Row guildRow = s.getRow(1);
        HashMap<String, NematodesDatabase> dat = app.getDatabaseMap();
        for (int i = 0; i < guildRow.getLastCellNum(); i++) {
            Cell gC = guildRow.getCell(i);

            if (i == 0)
                assertTrue(gC.toString().equals("Guild"));
            else {
                Cell tC = taxonRow.getCell(i);
                assertTrue(dat.containsKey(tC.toString()));
                assertTrue(dat.get(tC.toString()).getGuild().equals(gC.toString()));
            }
        }
    }

    private void testMass(Sheet s) {
        Row massRow = s.getRow(2);
        Row taxonRow = s.getRow(0);
        HashMap<String, NematodesDatabase> dat = app.getDatabaseMap();

        for (int i = 0; i < massRow.getLastCellNum(); i++) {
            Cell mC = massRow.getCell(i);

            if (i == 0)
                assertTrue(mC.toString().equals("Taxon mass (ug)"));
            else {
                Cell tC = taxonRow.getCell(i);
                assertTrue(dat.containsKey(tC.toString()));
                assertEquals(dat.get(tC.toString()).getWeight(), mC.getNumericCellValue(), 0.00001);
            }
        }
    }

    private void activateBoxes(ArrayList<BasicCheckBox> boxes) {
        for (BasicCheckBox b : boxes) {
            b.boxOnOffSwitching(true);
        }
    }

    private void sheetCheck(Sheet sheet) {
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                System.out.print(sheet.getRow(i).getCell(j) + " ");
            }
            System.out.println();
        }

    }

    private void createHeader(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);

        for (int i = 0; i <= sites.size(); i++) {
            if (i == 0)
                row.createCell(i).setCellValue("Sites");
            else {
                row.createCell(i).setCellValue(sites.get(i - 1).getSiteName());
            }
        }
    }

    private void addTaxaNames(XSSFSheet sheet) {
        XSSFRow row;
        ArrayList<String> taxa = new ArrayList<>();
        for (int i = 1; i <= s1.getTaxaList().size(); i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(s1.getTaxaList().get(i - 1).getTaxaName());
            taxa.add(s1.getTaxaList().get(i - 1).getTaxaName());
        }
        app.setTaxaList(taxa);
    }

    private void addAbundances(XSSFSheet sheet) {
        XSSFRow row;
        for (int i = 1; i <= sites.size(); i++) {
            for (int j = 1; j <= s1.getTaxaList().size(); j++) {
                ArrayList<Sample> sample = sites.get(i - 1).getTaxaList();
                row = sheet.getRow(j);
                row.createCell(i).setCellValue(sample.get(j - 1).getAbundance());
            }
        }
    }

    private void addSamplesToArray(Sample s1, Sample s2, Sample s3, Sample s4, Sample s5, ArrayList<Sample> samples) {
        List<Sample> vzorky = Arrays.asList(s1, s2, s3, s4, s5);
        samples.addAll(vzorky);
    }

    private void fillInSheet() {
        XSSFSheet sheet = book.createSheet();

        createHeader(sheet);
        addTaxaNames(sheet);
        addAbundances(sheet);

        app.setSampleSheet(sheet);
        //sheetCheck(sheet);

    }
}

