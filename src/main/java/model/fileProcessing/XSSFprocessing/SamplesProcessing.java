package model.fileProcessing.XSSFprocessing;

import model.computing.data.Sample;
import model.computing.data.Site;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class SamplesProcessing {


    private XSSFSheet sampleSheet;
    private AppInterface app;
    private ArrayList<BasicCheckBox> chooseIndices;

    private XSSFWorkbook resultBook;
    private DiversitySheet diversity;
    private FunctionalSheet function;
    private FootprintsSheet footprint;
    private CPSheet cp;
    private TrophicSheet trophic;
    private DominancySheet dominancy;

    private int sampleSize;
    private boolean boxFun, boxFoo, boxDiv;
    private ArrayList<Site> allSitesSamples;

    private final String DIVERSITY_SHEET_NAME = "Diversity Indices";
    private final String FUNCTION_SHEET_NAME = "Functional Indices";
    private final String FOOTPRINT_SHEET_NAME = "Footprint Indices";
    private static final String CP_SHEET_NAME = "CP groups (%)";
    private static final String TROPHIC_SHEET_NAME = "Trophic groups (%)";
    private static final String DOMINANCY_SHEET_NAME = "Taxa dominancy (%)";

    public SamplesProcessing(AppInterface app) {
        this.app = app;
        this.sampleSheet = app.getSampleSheet();
        initialize();
    }

    private void initialize() {
        allSitesSamples = new ArrayList<>();
        resultBook = new XSSFWorkbook();
        sampleSize = sampleSheet.getRow(0).getLastCellNum();

        chooseIndices = getChooseIndices("diversity");
        boxDiv = checkIfChooseBoxes(chooseIndices);

        if (boxDiv) {
            XSSFSheet divSheet = resultBook.createSheet(DIVERSITY_SHEET_NAME);
            diversity = new DiversitySheet(app, chooseIndices, divSheet, sampleSize);
        }

        if (!app.isDiversityOn()) {
            chooseIndices = getChooseIndices("function");
            boxFun = checkIfChooseBoxes(chooseIndices);

            if (boxFun) {
                XSSFSheet funSheet = resultBook.createSheet(FUNCTION_SHEET_NAME);
                function = new FunctionalSheet(app, chooseIndices, funSheet, sampleSize);
            }

            chooseIndices = getChooseIndices("footprint");
            boxFoo = checkIfChooseBoxes(chooseIndices);

            if (boxFoo) {
                XSSFSheet footSheet = resultBook.createSheet(FOOTPRINT_SHEET_NAME);
                footprint = new FootprintsSheet(app, chooseIndices, footSheet, sampleSize);
            }

            XSSFSheet cpSheet = resultBook.createSheet(CP_SHEET_NAME);
            cp = new CPSheet(app, null, cpSheet, sampleSize);

            XSSFSheet trophicSheet = resultBook.createSheet(TROPHIC_SHEET_NAME);
            trophic = new TrophicSheet(app, null, trophicSheet, sampleSize);
        }

        XSSFSheet domSheet = resultBook.createSheet(DOMINANCY_SHEET_NAME);
        dominancy = new DominancySheet(app, null, domSheet, sampleSize);

        sheetIterator();
    }

    private boolean checkIfChooseBoxes(ArrayList<BasicCheckBox> chooseIndices) {
        for (BasicCheckBox box : chooseIndices)
            if (box.isActive())
                return true;

        return false;
    }

    public XSSFWorkbook getResultBook() {
        return resultBook;
    }

    protected void sheetIterator() {
        int lastColumn = sampleSheet.getRow(0).getLastCellNum();

        for (int i = 1; i < lastColumn; i++) {
            allSitesSamples.add(getSiteAbundancesList(i));
        }

        fillResultSheets(allSitesSamples);
    }

    private void fillResultSheets(ArrayList<Site> sites) {

        if (boxDiv)
            diversity.addResults(sites);

        if (!app.isDiversityOn()) {
            if (boxFun)
                function.addResults(sites);
            if (boxFoo)
                footprint.addResults(sites);

            cp.addResults(sites);
            trophic.addResults(sites);
        }
        dominancy.addResults(sites);
    }

    protected String formatInput(XSSFCell str) {
        return str.toString().trim();
    }

    private Site getSiteAbundancesList(int index) {

        ArrayList<Sample> sampleColumn = new ArrayList<>();
        XSSFCell cell;
        String name = "";
        String site = formatInput(sampleSheet.getRow(0).getCell(index));

        for (int i = 1; i <= sampleSheet.getLastRowNum(); i++) {
            cell = sampleSheet.getRow(i).getCell(index);

            if ((cell == null || cell.getCellType().equals(CellType.BLANK)))
                break;
            name = formatInput(sampleSheet.getRow(i).getCell(0));
            sampleColumn.add(new Sample(name, Double.parseDouble(formatInput(cell)), site, i));
        }

        return new Site(site, sampleColumn);
    }

    private ArrayList<BasicCheckBox> getChooseIndices(String sheet) {
        if (sheet.equals("diversity"))
            return app.getOptionsPane().getDiversityPanel().getCheckBoxesList();
        if (sheet.equals("function"))
            return app.getOptionsPane().getFunctionalPanel().getCheckBoxesList();
        if (sheet.equals("footprint"))
            return app.getOptionsPane().getFootprintsPanel().getCheckBoxesList();

        return null;
    }
}
