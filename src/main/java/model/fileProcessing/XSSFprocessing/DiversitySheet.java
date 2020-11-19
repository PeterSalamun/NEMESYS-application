package model.fileProcessing.XSSFprocessing;

import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.indicesComputation.DiversityComputing;
import model.computing.results.DiversityResults;
import model.computing.results.GeneralResults;
import model.utilities.cellstyles.DiversityHeadStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class DiversitySheet extends BasicSheet {

    //Constructor takes as parameters AppInterface, ArrayList<BasicCheckBox>, XSSFSheet, int
    public DiversitySheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        createRows(rowNo);
    }

    //EFFECTS: initializing GeneralComputing by calling DiversityComputing
    protected void initialize() {
        generalComputing = new DiversityComputing(app);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: XSSFSheet
    //EFFECTS: obtains results for diversity indices and write them in to the XSSFSheet
    protected void addResults(ArrayList<Site> allSitesAbundances) {

        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);
        XSSFCell cell;

        for (int i = 1; i <= results.size(); i++) {
            int count = 0;
            int checkBox = 0;
            DiversityResults divRes = (DiversityResults) results.get(i - 1);
            String siteName = divRes.getSiteName();

            cell = sheet.getRow(i).createCell(count);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getAbundance());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getSpeciesNo());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBrillDiversity());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBrillMaxDiv());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBrillMinDiv());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBrillEven());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getBrillRelEven());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getHeip());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getHillEven());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getHillN1());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getHillN2());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getMargalef());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getMenhinick());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getShannon());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, divRes.getSimpson());
            }
        }
    }

    //getters
    protected CellStyle getHeaderCellStyle() {
        headStyle = new DiversityHeadStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }
}