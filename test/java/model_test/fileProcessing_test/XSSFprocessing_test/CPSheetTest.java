package model_test.fileProcessing_test.XSSFprocessing_test;

import model.computing.data.Sample;
import model.computing.data.Site;
import model.fileProcessing.XSSFprocessing.CPSheet;
import model.utilities.cellstyles.TrophicCPHeadCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CPSheetTest {

    private CPSheet testSheet;
    private AppInterface app;
    private ArrayList<BasicCheckBox> indices;
    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private int rowNo;

    private Site s1, s2, s3;
    private Sample v1, v2, v3, v4, v5;
    private ArrayList<Sample> samples;
    private ArrayList<Site> sites;

    private String[] CP_GROUPS;

    @Before
    public void setUP() {
        app = new AppInterface();
        indices = null;
        book = new XSSFWorkbook();
        sheet = book.createSheet("CP groups (%)");
        rowNo = 6;
        samples = new ArrayList<>();
        sites = new ArrayList<>();
        CP_GROUPS = new String[]{"CP1 (%)", "CP2 (%)", "CP3 (%)", "CP4 (%)", "CP5 (%)", "Other (%)"};

        v1 = new Sample("Rhabditis", 25, "Les", 1);
        v2 = new Sample("Dorylaimus", 5, "Les", 2);
        v3 = new Sample("Helicotylenchus", 15, "Les", 3);
        v4 = new Sample("Mononchus", 7, "Les", 4);
        v5 = new Sample("Aphelenchus", 8, "Les", 5);
        addSamplesToArray();
        s1 = new Site("Les", samples);

        v1 = new Sample("Rhabditis", 8, "Luka", 1);
        v2 = new Sample("Dorylaimus", 5, "Luka", 2);
        v3 = new Sample("Helicotylenchus", 1, "Luka", 3);
        v4 = new Sample("Mononchus", 9, "Luka", 4);
        v5 = new Sample("Aphelenchus", 4, "Luka", 5);
        addSamplesToArray();
        s2 = new Site("Luka", samples);

        v1 = new Sample("Rhabditis", 18, "Pole", 1);
        v2 = new Sample("Dorylaimus", 65, "Pole", 2);
        v3 = new Sample("Helicotylenchus", 23, "Pole", 3);
        v4 = new Sample("Mononchus", 0, "Pole", 4);
        v5 = new Sample("Aphelenchus", 0, "Pole", 5);
        addSamplesToArray();
        s3 = new Site("Pole", samples);

        List<Site> lokality = Arrays.asList(s1, s2, s3);
        sites.addAll(lokality);

        testSheet = new CPSheet(app, indices, sheet, rowNo);

    }

    @Test
    public void testGetSheet() {
        XSSFSheet testS = testSheet.getSheet();
        assertEquals(testS.getLastRowNum(), 7);
        assertEquals(testS.getRow(0).getLastCellNum(), 7);
        assertEquals(testS.getRow(0).getCell(0).getStringCellValue(), "Sample/Index");

        for (int i = 1; i < 7; i++){
            assertEquals(testS.getRow(0).getCell(i).getStringCellValue(), CP_GROUPS[i-1]);}

    }

    private void addSamplesToArray() {
        samples.clear();
        List<Sample> vzorky = Arrays.asList(v1, v2, v3, v4, v5);
        samples.addAll(vzorky);
    }

}
