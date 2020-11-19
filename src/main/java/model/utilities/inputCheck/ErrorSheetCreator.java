package model.utilities.inputCheck;

import model.utilities.cellstyles.CommonCellStyle;
import model.utilities.cellstyles.DiversityHeadStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;

public class ErrorSheetCreator {

    private CellStyle headerStyle;
    private CellStyle commonStyle;
    private XSSFWorkbook errorBook;
    private ArrayList<Errors> errorsArrayList;
    private final String[] errorsHeader = {"Row No.", "Taxon", "Column", "Error value", "Error type"};
    private int columnWidth = 2500;

    //Constructor takes XSSFWorkbook, ArrayList<Errors>
    public ErrorSheetCreator(XSSFWorkbook errorBook, ArrayList<Errors> errorsArrayList) {
        this.errorBook = errorBook;
        this.errorsArrayList = errorsArrayList;
        initialize();
        writeErrors();
    }

    //EFFECTS: initialize fields
    private void initialize() {
        DiversityHeadStyle divStyle = new DiversityHeadStyle(errorBook);
        headerStyle = divStyle.getCellStyle();

        CommonCellStyle comStyle = new CommonCellStyle(errorBook);
        commonStyle = comStyle.getCellStyle();
    }

    //MODIFIES: XSSFWorkbook
    //EFFECTS: writing Errors found in data to the XSSFWorkbook and customizing the cells within the XSSFWorkbook
    public void writeErrors() {

        XSSFSheet errorSheet = errorBook.getSheetAt(0);

        for (int i = 0; i <= errorsArrayList.size(); i++) {
            XSSFRow row = errorSheet.createRow(i);
            if (i == 0) {
                fillErrorsHeader(row);
                settingStyle(row, headerStyle);
            } else {
                fillErrorsRow(row, errorsArrayList.get(i - 1));
                settingStyle(row, commonStyle);
            }
        }

        resizingColumns(errorSheet, errorsHeader.length, columnWidth);
    }

    //REQUIRES: XSSFRow
    //MODIFIES: XSSFRow
    //EFFECTS: adding String value (name of the column - errorHeader) to the first row of XSSFSheet
    private void fillErrorsHeader(XSSFRow row) {
        for (int i = 0; i < errorsHeader.length; i++)
            row.createCell(i).setCellValue(errorsHeader[i]);
    }

    //REQUIRES: XSSFRow, Errors
    //MODIFIES: XSSFRow
    //EFFECTS: fill in the cells within the row with Error specifications - RowNo, Taxon, Site, ErrorValue, ErrorType
    private void fillErrorsRow(XSSFRow row, Errors err) {
        row.createCell(0).setCellValue(err.getRowNo());
        row.createCell(1).setCellValue(err.getTaxon());
        row.createCell(2).setCellValue(err.getSite());
        row.createCell(3).setCellValue(err.getErrorValue());
        row.createCell(4).setCellValue(err.getErrorType());
    }

    //REQUIRES: XSSFSheet, int, int
    //MODIFIES: XSSFSheet
    //EFFECTS: resizing columns  within the sheet to appropriate size
    private void resizingColumns(XSSFSheet sheet, int headerLength, int columnWidth) {
        for (int j = 0; j < headerLength; j++) {
            sheet.autoSizeColumn(j);
            if (sheet.getColumnWidth(j) < columnWidth)
                sheet.setColumnWidth(j, columnWidth);
        }
    }

    //REQUIRES: XSSFRow, CellStyle
    //MODIFIES: XSSFRow
    //EFFECTS: setting cell style in the whole row
    private void settingStyle(XSSFRow row, CellStyle style) {
        for (int i = 0; i < row.getLastCellNum(); i++)
            row.getCell(i).setCellStyle(style);
    }

    //getters
    public XSSFWorkbook getErrorBook() {
        return errorBook;
    }
}
