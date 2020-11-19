package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.FunctionalIndicesComputing;
import model.computing.results.FunctionalResults;
import model.computing.results.GeneralResults;
import model.utilities.cellstyles.FunctionalHeadStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class FunctionalSheet extends BasicSheet {

    //Constructor takes as parameters AppInterface, ArrayList<BasicCheckBox>, XSSFSheet, int
    public FunctionalSheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        createRows(rowNo);
    }

    //EFFECTS: initializing GeneralComputing by calling FunctionalIndicesComputing
    @Override
    protected void initialize() {
        generalComputing = new FunctionalIndicesComputing(app);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: XSSFSheet
    //EFFECTS: obtains results for functional indices and write them in to the XSSFSheet
    protected void addResults(ArrayList<Site> allSitesAbundances) {

        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);
        XSSFCell cell;

        for (int i = 1; i <= results.size(); i++) {
            int count = 0;
            int checkBox = 0;
            FunctionalResults divRes = (FunctionalResults) results.get(i - 1);
            String siteName = divRes.getSiteName();

            cell = sheet.getRow(i).createCell(count);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getMaturity());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getMaturity25());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getPlant());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getSumMI());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getSumMI25());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getSi());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getEi());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getCi());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBi());
            }
        }
    }

    //getters
    protected CellStyle getHeaderCellStyle() {
        headStyle = new FunctionalHeadStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }
}
