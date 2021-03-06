package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.CpComputing;
import model.computing.results.CPResults;
import model.computing.results.GeneralResults;
import model.utilities.cellstyles.TrophicCPHeadCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class CPSheet extends BasicSheet {

    private TrophicCPHeadCellStyle headStyle;
    private static final String[] CP_GROUPS = {
            "CP1 (%)", "CP2 (%)", "CP3 (%)", "CP4 (%)", "CP5 (%)", "Other (%)"
    };

    //Constructor takes as parameters AppInterface, ArrayList<BasicCheckBox>, XSSFSheet, int
    public CPSheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        createRows(rowNo);
    }

    //EFFECTS: initializing GeneralComputing by calling CpComputing
    @Override
    protected void initialize() {
        generalComputing = new CpComputing(app);
    }

    //REQUIRES: XSSFRow
    //MODIFIES: XSSFSheet
    //EFFECTS: filling the first row of a XSSFSheet with different Strings - depending on sheet the cells are fill with
    // the different names of indices
    protected void fillIndicesNames(XSSFRow row) {

        for (int i = 0; i <= CP_GROUPS.length; i++) {

            if (i == 0)
                row.createCell(i).setCellValue(FIRSTCELLCONTENT);
            else
                row.createCell(i).setCellValue(CP_GROUPS[i - 1]);
        }
        customizeRow(row);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: XSSFSheet
    //EFFECTS: obtains results for cp distribution and write them in to the XSSFSheet
    protected void addResults(ArrayList<Site> allSitesAbundances) {
        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);
        XSSFCell cell;

        for (int i = 1; i <= results.size(); i++) {
            int count = 0;
            CPResults cpRes = (CPResults) results.get(i - 1);
            String siteName = cpRes.getSiteName();

            cell = sheet.getRow(i).createCell(count);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            addIndexValueToTable(++count, i, cpRes.getCp1());
            addIndexValueToTable(++count, i, cpRes.getCp2());
            addIndexValueToTable(++count, i, cpRes.getCp3());
            addIndexValueToTable(++count, i, cpRes.getCp4());
            addIndexValueToTable(++count, i, cpRes.getCp5());
            addIndexValueToTable(++count, i, cpRes.getCpOther());
        }
    }

    //getters
    protected CellStyle getHeaderCellStyle() {
        headStyle = new TrophicCPHeadCellStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }

}
