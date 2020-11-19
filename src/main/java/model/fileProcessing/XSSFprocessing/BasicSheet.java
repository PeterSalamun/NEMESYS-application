package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.GeneralComputing;
import model.utilities.cellstyles.BasicCellStyle;
import model.utilities.cellstyles.CommonCellStyle;
import model.utilities.cellstyles.FirstCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;
import ui.tools.panels.BasicPanel;

import java.util.ArrayList;

public abstract class BasicSheet {

    protected BasicCellStyle headStyle;
    protected GeneralComputing generalComputing;
    protected ArrayList<BasicCheckBox> chooseIndices;
    protected XSSFSheet sheet;
    protected CellStyle style;
    protected int rowCount;
    protected final String FIRSTCELLCONTENT = "Sample/Index";

    protected BasicPanel panel;
    protected AppInterface app;
    protected CellStyle firstCellStyle;
    protected CellStyle commonCellStyle;

    //Constructor takes as parameters AppInterface, ArrayList<BasicCheckBox>, XSSFSheet
    public BasicSheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet) {
        this.app = app;
        this.chooseIndices = chooseIndices;
        this.rowCount = 1;
        this.sheet = sheet;
        setOtherCellStyles();
    }

    //REQUIRES: int
    //MODIFIES: XSSFSheet
    //EFFECTS: creates appropriate number of rows (based on int rowNo) in XSSFSheet
    protected void createRows(int rowNo) {
        for (int i = 0; i <= rowNo + 1; i++) {

            XSSFRow row = sheet.createRow(i);

            if (i == 0)
                fillIndicesNames(row);
        }
    }

    //REQUIRES: XSSFRow
    //MODIFIES: XSSFSheet
    //EFFECTS: filling the first row of a XSSFSheet with different Strings - depending on sheet the cells are fill with
    // the different names of indices
    protected void fillIndicesNames(XSSFRow row) {
        int lastCell = 0;

        for (int i = 0; i <= chooseIndices.size(); i++) {

            if (i == 0) {
                row.createCell(i).setCellValue(FIRSTCELLCONTENT);

            } else if (chooseIndices.get(i - 1).isActive()) {
                lastCell = row.getLastCellNum();
                row.createCell(lastCell).setCellValue(chooseIndices.get(i - 1).getName());
            }
        }
        customizeRow(row);
    }

    //EFFECTS: setting CommonCellStyle and FirstCellStyle
    private void setOtherCellStyles() {
        CommonCellStyle common = new CommonCellStyle(sheet.getWorkbook());
        commonCellStyle = common.getCellStyle();

        FirstCellStyle first = new FirstCellStyle(sheet.getWorkbook());
        firstCellStyle = first.getCellStyle();
    }

    //REQUIRES: XSSFRow
    //MODIFIES: XSSFRow
    //EFFECTS: customize XSSFRow according to actual CellStyle
    protected void customizeRow(XSSFRow row) {
        for (Cell cell : row) {
            cell.setCellStyle(style);
        }
    }

    //REQUIRES: int, int, double
    //MODIFIES: XSSFSheet sheet
    //EFFECTS: add double to XSSFCell specified by int column and int row in XSSFSheet
    protected void addIndexValueToTable(int column, int row, double value) {
        XSSFCell cell = sheet.getRow(row).createCell(column);
        cell.setCellStyle(commonCellStyle);
        cell.setCellValue(value);
    }

    //EFFECTS: abstract or overridden methods without bodies
    protected abstract void initialize();

    protected void addResults(ArrayList<Site> allSitesAbundances) {
    }

    //getters
    protected boolean activeChooseIndex(int count) {
        return chooseIndices.get(count).isActive();
    }

    protected CellStyle getHeaderCellStyle() {
        return style;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }
}
