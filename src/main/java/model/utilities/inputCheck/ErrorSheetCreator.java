package model.utilities.inputCheck;

import model.utilities.cellstyles.CommonCellStyle;
import model.utilities.cellstyles.DiversityHeadStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;

public class ErrorSheetCreator {

    CellStyle headerStyle;
    CellStyle commonStyle;
    XSSFWorkbook errorBook;
    ArrayList<Errors> errorsArrayList;
    final String[] errorsHeader = {"Row No.", "Taxon", "Column", "Error value", "Error type"};
    int columnWidth = 2500;

    public ErrorSheetCreator(XSSFWorkbook errorBook, ArrayList<Errors> errorsArrayList) {
        this.errorBook = errorBook;
        this.errorsArrayList = errorsArrayList;
        initialize();
        writeErrors();
    }

    private void initialize() {
        DiversityHeadStyle divStyle = new DiversityHeadStyle(errorBook);
        headerStyle = divStyle.getCellStyle();

        CommonCellStyle comStyle = new CommonCellStyle(errorBook);
        commonStyle = comStyle.getCellStyle();
    }

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

    private void fillErrorsHeader(XSSFRow row) {
        for (int i = 0; i < errorsHeader.length; i++)
            row.createCell(i).setCellValue(errorsHeader[i]);
    }

    private void fillErrorsRow(XSSFRow row, Errors err) {
        row.createCell(0).setCellValue(err.getRowNo());
        row.createCell(1).setCellValue(err.getTaxon());
        row.createCell(2).setCellValue(err.getSite());
        row.createCell(3).setCellValue(err.getErrorValue());
        row.createCell(4).setCellValue(err.getErrorType());
    }


    private void resizingColumns(XSSFSheet sheet, Integer headerLength, int columnWidth) {
        for (int j = 0; j < headerLength; j++) {
            sheet.autoSizeColumn(j);
            if (sheet.getColumnWidth(j) < columnWidth)
                sheet.setColumnWidth(j, columnWidth);
        }
    }

    private void settingStyle(XSSFRow row, CellStyle style) {
        for (int i = 0; i < row.getLastCellNum(); i++)
            row.getCell(i).setCellStyle(style);
    }
}
