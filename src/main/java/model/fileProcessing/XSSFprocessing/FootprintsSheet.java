package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.FootprintsComputing;
import model.computing.results.FootprintResults;
import model.computing.results.GeneralResults;
import model.utilities.cellstyles.FootprintHeadStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;

public class FootprintsSheet extends BasicSheet {

    private final String massName = "Total sample mass (ug)";

    public FootprintsSheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        createRows(rowNo);
    }

    @Override
    protected void initialize() {
        generalComputing = new FootprintsComputing(app);
    }

    protected CellStyle getHeaderCellStyle() {
        headStyle = new FootprintHeadStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }

    protected void addResults(ArrayList<Site> allSitesAbundances) {

        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);
        XSSFCell cell;

        addMassName(sheet.getRow(0));

        for (int i = 1; i <= results.size(); i++) {
            int count = 0;
            int checkBox = 0;
            FootprintResults fooRes = (FootprintResults) results.get(i - 1);
            String siteName = fooRes.getSiteName();

            cell = sheet.getRow(i).createCell(count);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getStructure());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getEnrichment());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getFunctional());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getComposite());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getHerbivore());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getBacterial());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getFungal());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getOmnivore());
            }
            if (activeChooseIndex(checkBox++)) {
                addIndexValueToTable(++count, i, fooRes.getPredator());
            }
            addIndexValueToTable(++count, i, fooRes.getWeight());

        }
    }

    private void addMassName(XSSFRow row) {
        XSSFCell cell = row.createCell(row.getLastCellNum());
        cell.setCellValue(massName);
        cell.setCellStyle(style);
        sheet.autoSizeColumn(cell.getColumnIndex());
    }
}
