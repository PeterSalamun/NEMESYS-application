package model.fileProcessing.XSSFprocessing;

import model.computing.data.Sample;
import model.computing.data.Site;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class SamplesProcessing {


    private XSSFSheet sampleSheet;
    private AppInterface app;

    private XSSFWorkbook resultBook;
    private DiversitySheet diversity;
    private FunctionalSheet function;
    private FootprintsSheet footprint;
    private CPSheet cp;
    private TrophicSheet trophic;
    private DominancySheet dominancy;

    private boolean boxFun, boxFoo, boxDiv;
    private ArrayList<Site> allSitesSamples;

    private final String DIVERSITY_SHEET_NAME = "Diversity Indices";
    private final String FUNCTION_SHEET_NAME = "Functional Indices";
    private final String FOOTPRINT_SHEET_NAME = "Footprint Indices";
    private static final String CP_SHEET_NAME = "CP groups (%)";
    private static final String TROPHIC_SHEET_NAME = "Trophic groups (%)";
    private static final String DOMINANCY_SHEET_NAME = "Taxa dominancy (%)";

    //Constructor takes AppInterface
    public SamplesProcessing(AppInterface app) {
        this.app = app;
        this.sampleSheet = app.getSampleSheet();
        initialize();
    }

    //EFFECTS: initialize fields. Depending on app.isDiversityOn() value and checked BasicCheckBoxes at different panels
    // (Diversity, Footprints, Functional) in OptionsPane the different XSSFSheets for each panel are initialized
    private void initialize() {
        allSitesSamples = new ArrayList<>();
        resultBook = new XSSFWorkbook();
        int sampleSize = getSitesNumber();

        ArrayList<BasicCheckBox> chooseIndices = getChooseIndices("diversity");
        assert chooseIndices != null;
        boxDiv = checkIfChooseBoxes(chooseIndices);

        //EFFECTS: initialize XSSFSheer divSheet if any BasicCheckBox in DiversityPanel in OptionsPane is activated
        if (boxDiv) {
            XSSFSheet divSheet = resultBook.createSheet(DIVERSITY_SHEET_NAME);
            diversity = new DiversitySheet(app, chooseIndices, divSheet, sampleSize);
        }

        //EFFECTS: if user choose to calculate nematode indices, initialize XSSFSheer funSheet and fooSheet if any
        // BasicCheckBox in FunctionalPanel or FootprintsPanel in OptionsPane is activated
        if (!app.isDiversityOn()) {
            chooseIndices = getChooseIndices("function");
            assert chooseIndices != null;
            boxFun = checkIfChooseBoxes(chooseIndices);

            if (boxFun) {
                XSSFSheet funSheet = resultBook.createSheet(FUNCTION_SHEET_NAME);
                function = new FunctionalSheet(app, chooseIndices, funSheet, sampleSize);
            }

            chooseIndices = getChooseIndices("footprint");
            assert chooseIndices != null;
            boxFoo = checkIfChooseBoxes(chooseIndices);

            if (boxFoo) {
                XSSFSheet footSheet = resultBook.createSheet(FOOTPRINT_SHEET_NAME);
                footprint = new FootprintsSheet(app, chooseIndices, footSheet, sampleSize);
            }

            //initialize cpSheet and trophicSheet if user choose to calculate nematode indices
            // (app.isDiversityOn() == true)
            XSSFSheet cpSheet = resultBook.createSheet(CP_SHEET_NAME);
            cp = new CPSheet(app, null, cpSheet, sampleSize);

            XSSFSheet trophicSheet = resultBook.createSheet(TROPHIC_SHEET_NAME);
            trophic = new TrophicSheet(app, null, trophicSheet, sampleSize);
        }

        // initialize XSSFSheet domSheet always
        XSSFSheet domSheet = resultBook.createSheet(DOMINANCY_SHEET_NAME);
        dominancy = new DominancySheet(app, null, domSheet, sampleSize);

        sheetIterator();
    }

    //REQUIRES: ArrayList<BasicCheckBox>
    //EFFECTS: return true if any checkBox is active
    private boolean checkIfChooseBoxes(ArrayList<BasicCheckBox> chooseIndices) {
        for (BasicCheckBox box : chooseIndices)
            if (box.isActive())
                return true;

        return false;
    }

    //EFFECTS: filling ArrayList<Site> allSitesSamples with information about each Sample (cell in sheet) and then fill
    // the results sheets with the indices results calculated from allSitesSamples arrayList information
    protected void sheetIterator() {
        int lastColumn = getSitesNumber();

        for (int i = 1; i <= lastColumn; i++) {
            allSitesSamples.add(getSiteAbundancesList(i));
        }
        fillResultSheets(allSitesSamples);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: XSSFSheets depending on the progress through method
    //EFFECTS: fill in results for particular sheet depending on choose options in the app before starting
    // calculation - nematodes vs. any animals, acitve basic check boxes
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

        autoSizeColumnInBook();
    }

    //MODIFIES: XSSFWorkbook
    //EFFECTS: autoSize columns in all sheets of the XSSFWorkbook resultBook
    private void autoSizeColumnInBook() {
        for (Sheet sheet : resultBook)
            for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 500);
            }
    }

    //REQUIRES: XSSFCell
    //MODIFIES: String
    //EFFECTS: trim content of the XSSFCell - String of any spaces
    protected String formatInput(XSSFCell str) {
        return str.toString().trim();
    }

    //getters
    //REQUIRES: int
    //EFFECTS: return new Site instance fill with new Samples instances. Each new Sample instance is equal to a row in
    // sheet which is going through
    private Site getSiteAbundancesList(int index) {

        ArrayList<Sample> sampleColumn = new ArrayList<>();
        XSSFCell cell;
        String name;
        String site = formatInput(sampleSheet.getRow(0).getCell(index));

        //going through each cell in column marked with int index
        for (int i = 1; i <= sampleSheet.getLastRowNum(); i++) {
            cell = sampleSheet.getRow(i).getCell(index);
            //if the cell is equal to null or the Cell is BLANK the loop is breaked, otherwise new Sample instance is
            // created and added to arrayList

            if (isCellEmpty(cell))
                break;

            name = formatInput(sampleSheet.getRow(i).getCell(0));
            sampleColumn.add(new Sample(name, Double.parseDouble(formatInput(cell)), site, i));
        }

        return new Site(site, sampleColumn);
    }

    //REQUIRES: String - name of a sheet
    //EFFECTS: returns arrayList of BasicCheckBoxes for particular sheet for the app to be able fill correct active
    // check boxes
    private ArrayList<BasicCheckBox> getChooseIndices(String sheet) {
        if (sheet.equals("diversity"))
            return app.getOptionsPane().getDiversityPanel().getCheckBoxesList();
        if (sheet.equals("function"))
            return app.getOptionsPane().getFunctionalPanel().getCheckBoxesList();
        if (sheet.equals("footprint"))
            return app.getOptionsPane().getFootprintsPanel().getCheckBoxesList();

        return null;
    }

    //EFFECTS: return number of columns with content by checking cells in the first row
    private int getSitesNumber() {
        int size = 0;
        XSSFRow row = sampleSheet.getRow(0);

        if (row != null)
            for (Cell cell : row)
                if (!isCellEmpty(cell)) {
                    size++;
                }

        return size;
    }

    //EFFECTS: return true if the cell is null
    private boolean isCellEmpty(Cell cell) {
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell).trim().length() <= 0;
    }

    //getters
    public XSSFWorkbook getResultBook() {
        return resultBook;
    }
}
