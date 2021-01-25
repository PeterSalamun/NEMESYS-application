package ui.tools_tests.buttons_tests;

import model.computing.data.Sample;
import model.computing.data.Site;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.buttons.CalculateButton;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CalculateButtonTest {

    private CalculateButton testT;
    private AppInterface app;
    private JComponent parent;
    private int x, y, h, w;
    private int[] insets;

    private XSSFWorkbook book;
    private Site s1, s2, s3;
    private Sample v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15;
    private ArrayList<Sample> sa1, sa2, sa3;
    private ArrayList<Site> sites;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();

        x = 10;
        y = 10;
        h = 10;
        w = 10;
        insets = new int[]{10, 10, 10, 10};

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
    public void testAddToParent() {
        assertEquals(parent.getComponents().length, 0);
        testT = new CalculateButton(app, parent);
        assertEquals(parent.getComponents().length, 1);
    }

    @Test
    public void testSwitchOnOffButton() {
        testT = new CalculateButton(app, parent);
        assertFalse(testT.isActive());
        testT.switchOnButton();
        assertTrue(testT.isActive());
        testT.switchOffButton();
        assertFalse(testT.isActive());
    }

    @Test
    public void testActivateDeactiveButton() {
        testT = new CalculateButton(app, parent);
        assertFalse(testT.isActive());
        testT.activate();
        assertTrue(testT.isActive());
        testT.deactivate();
        assertFalse(testT.isActive());
    }

    @Test
    public void testSetSpecificGrid() {
        testT = new CalculateButton(app, parent);
        assertEquals(testT.setSpecificGrid(x, y, h, w, insets).getClass(), GridBagConstraints.class);
    }

    @Test
    public void testClickCalculateButton() {
        assertTrue(app.getDatabaseMap().isEmpty());
        testT = new CalculateButton(app, parent);
        testT.switchOnButton();
        assertTrue(testT.isActive());
        app.setDiversityOn(false);
        testT.getButton().doClick();
        assertEquals(app.getDatabaseMap().isEmpty(), false);
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
}
