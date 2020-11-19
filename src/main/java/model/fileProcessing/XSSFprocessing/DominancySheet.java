package model.fileProcessing.XSSFprocessing;

import model.computing.data.Site;
import model.computing.indicesComputation.DominancyComputing;
import model.computing.results.DominancyResults;
import model.computing.results.GeneralResults;
import model.utilities.cellstyles.BasicCellStyle;
import model.utilities.cellstyles.DominancyHeadCellStyle;
import model.utilities.cellstyles.GuildCellStyle;
import model.utilities.cellstyles.WeightCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import java.util.ArrayList;
import java.util.TreeMap;

public class DominancySheet extends BasicSheet {

    private static final String WEIGHTFIRSTCELLCONTENT = "Taxon mass (ug)";
    private GuildCellStyle guildStyle;
    private BasicCellStyle weightStyle;
    private final String DOMFIRSTCELLCONTENT = "Sample/Taxon";
    private final String GUILDFIRSTCELLCONTENT = "Guild";

    //Constructor takes as parameters AppInterface, ArrayList<BasicCheckBox>, XSSFSheet, int
    public DominancySheet(AppInterface app, ArrayList<BasicCheckBox> chooseIndices, XSSFSheet sheet, int rowNo) {
        super(app, chooseIndices, sheet);
        style = getHeaderCellStyle();
        guildStyle = new GuildCellStyle(sheet.getWorkbook());
        weightStyle = new WeightCellStyle(sheet.getWorkbook());
        createRows(rowNo);
    }

    //EFFECTS: initializing GeneralComputing by calling DominancyComputing
    @Override
    protected void initialize() {
        generalComputing = new DominancyComputing(app);

        if (!app.isDiversityOn()) {
            fillGuildMassSpecifics(sheet.getRow(0).getLastCellNum(), 1, GUILDFIRSTCELLCONTENT, guildStyle);
            fillGuildMassSpecifics(sheet.getRow(0).getLastCellNum(), 2, WEIGHTFIRSTCELLCONTENT, weightStyle);
        }
    }

    //REQUIRES: XSSFRow
    //MODIFIES: XSSFSheet
    //EFFECTS: filling the first row of a XSSFSheet with different Strings - depending on sheet the cells are fill with
    // the different names of taxa
    protected void fillIndicesNames(XSSFRow row) {
        int rowSize = app.getTaxaList().size();

        for (int i = 0; i <= rowSize; i++) {

            if (i == 0)
                row.createCell(i).setCellValue(DOMFIRSTCELLCONTENT);
            else
                row.createCell(i).setCellValue(app.getTaxaList().get(i - 1));
        }
        customizeRow(row);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: XSSFSheet
    //EFFECTS: obtains results for dominancy distribution and write them in to the XSSFSheet
    protected void addResults(ArrayList<Site> allSitesAbundances) {

        int index = 1;
        XSSFCell cell;
        DominancyResults domRes;
        TreeMap<String, Double> taxaDom;

        initialize();
        ArrayList<GeneralResults> results = generalComputing.computeAllSitesResults(allSitesAbundances);

        if (!app.isDiversityOn())
            index = 3;

        for (int i = 0; i < results.size(); i++) {

            domRes = (DominancyResults) results.get(i);
            taxaDom = domRes.getTaxaDominancy();
            String siteName = domRes.getSiteName();

            if(sheet.getRow(i+index) == null)
                sheet.createRow(i+index);

            cell = sheet.getRow(i + index).createCell(0);
            cell.setCellValue(siteName);
            cell.setCellStyle(firstCellStyle);

            for (int j = 1; j <= taxaDom.size(); j++) {
                String taxon = sheet.getRow(0).getCell(j).toString();
                addIndexValueToTable(j, (i + index), taxaDom.get(taxon));
            }
        }
    }

    //REQUIRES: int, int, String, BasicCellStyle
    //MODIFIES: XSSFSheet
    //EFFECTS: filling guild and mass specifics in to the second and third row of sheet and customize the rows by
    // BasicCellStyle style
    private void fillGuildMassSpecifics(int rowSize, int rowNum, String firstCell, BasicCellStyle style) {
        XSSFCell cell;
        XSSFRow row = sheet.getRow(rowNum);

        for (int i = 0; i < rowSize; i++) {

            cell = row.createCell(i);
            cell.setCellStyle(style.getCellStyle());

            if (i == 0) {
                cell.setCellValue(firstCell);
            } else {
                String taxon = sheet.getRow(0).getCell(i).toString();
                if (taxon.isEmpty())
                    break;
                if (rowNum == 1)
                    cell.setCellValue(app.getDatabaseMap().get(taxon).getGuild());
                else
                    cell.setCellValue(app.getDatabaseMap().get(taxon).getWeight());
            }
        }
    }

    //getters
    protected CellStyle getHeaderCellStyle() {
        headStyle = new DominancyHeadCellStyle(sheet.getWorkbook());
        return headStyle.getCellStyle();
    }
}
