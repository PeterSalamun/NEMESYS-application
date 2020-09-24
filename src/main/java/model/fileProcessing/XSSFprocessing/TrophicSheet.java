package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.TrophicCcomputing;
import model.computing.results.GeneralResults;
import model.computing.results.TrophicResults;
import model.utilities.cellstyles.TrophicCPHeadCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class TrophicSheet extends BasicSheet {


    private static final String[] TROPHIC_GROUPS = {
            "Bacterivore (%)",
            "Fungivore (%)",
            "Herbivore (%)",
            "Omnivore (%)",
            "Predator (%)",
            "Other (%)",
    };

    public TrophicSheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        createRows(rowNo);
    }

    @Override
    protected void initialize() {
        generalComputing = new TrophicCcomputing(app);
    }

    protected CellStyle getHeaderCellStyle() {
        headStyle = new TrophicCPHeadCellStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }

    protected void fillIndicesNames(XSSFRow row) {

        for (int i = 0; i <= TROPHIC_GROUPS.length; i++) {

            if (i == 0) {
                row.createCell(i).setCellValue(FIRSTCELLCONTENT);
                sheet.autoSizeColumn(0);

            } else {
                row.createCell(i).setCellValue(TROPHIC_GROUPS[i-1]);
                sheet.autoSizeColumn(i);
            }

        }
        customizeRow(row);
    }

    protected void addResults(ArrayList<Site> allSitesAbundances) {

        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);
        XSSFCell cell;

        for (int i = 1; i <= results.size(); i++) {
            int count = 0;
            TrophicResults trophicRes = (TrophicResults) results.get(i - 1);
            String siteName = trophicRes.getSiteName();

            cell = sheet.getRow(i).createCell(count);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            addIndexValueToTable(++count, i, trophicRes.getBacterivore());
            addIndexValueToTable(++count, i, trophicRes.getFungivore());
            addIndexValueToTable(++count, i, trophicRes.getHerbivore());
            addIndexValueToTable(++count, i, trophicRes.getOmnivore());
            addIndexValueToTable(++count, i, trophicRes.getPredator());
            addIndexValueToTable(++count, i, trophicRes.getTrophicOther());
        }
    }
}

