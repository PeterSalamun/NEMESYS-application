package model_test.utilities_test.inputCheck_test;

import model.utilities.cellstyles.CommonCellStyle;
import model.utilities.cellstyles.DiversityHeadStyle;
import model.utilities.inputCheck.ErrorSheetCreator;
import model.utilities.inputCheck.Errors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ErrorSheetCreatorTest {

    private ErrorSheetCreator testCreator;
    private ArrayList<Errors> errors;
    private Errors e1, e2, e3;
    private XSSFWorkbook book, workbook;
    private CommonCellStyle common;
    private DiversityHeadStyle header;

    @Before
    public void setUp() {
        e1 = new Errors("a", "b", "c", "d", "e");
        e2 = new Errors("1", "2", "3", "4", "5");
        e3 = new Errors("/", "*", "-", "+", "0");
        errors = new ArrayList<>();
        errors.add(e1);
        errors.add(e2);
        errors.add(e3);

        book = new XSSFWorkbook();
        workbook = new XSSFWorkbook();
        book.createSheet();
        testCreator = new ErrorSheetCreator(book, errors);

        common = new CommonCellStyle(workbook);
        header = new DiversityHeadStyle(workbook);
    }

    @Test
    public void testWriteErrors() {
        XSSFSheet sheet = book.getSheetAt(0);
        assertEquals(sheet.getPhysicalNumberOfRows(), 4);
        assertEquals(sheet.getRow(0).getLastCellNum(), 5);
        assertEquals(sheet.getColumnWidth(0), 2500);

        testCellStyles(sheet);
        testHeader(sheet);
        testCommonRow(sheet);

    }

    private void testCommonRow(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(1);

        assertEquals(row.getCell(0).getStringCellValue(), e1.getRowNo());
        assertEquals(row.getCell(1).getStringCellValue(), e1.getTaxon());
        assertEquals(row.getCell(2).getStringCellValue(), e1.getSite());
        assertEquals(row.getCell(3).getStringCellValue(), e1.getErrorValue());
        assertEquals(row.getCell(4).getStringCellValue(), e1.getErrorType());

        row = sheet.getRow(2);

        assertEquals(row.getCell(0).getStringCellValue(), e2.getRowNo());
        assertEquals(row.getCell(1).getStringCellValue(), e2.getTaxon());
        assertEquals(row.getCell(2).getStringCellValue(), e2.getSite());
        assertEquals(row.getCell(3).getStringCellValue(), e2.getErrorValue());
        assertEquals(row.getCell(4).getStringCellValue(), e2.getErrorType());

        row = sheet.getRow(3);

        assertEquals(row.getCell(0).getStringCellValue(), e3.getRowNo());
        assertEquals(row.getCell(1).getStringCellValue(), e3.getTaxon());
        assertEquals(row.getCell(2).getStringCellValue(), e3.getSite());
        assertEquals(row.getCell(3).getStringCellValue(), e3.getErrorValue());
        assertEquals(row.getCell(4).getStringCellValue(), e3.getErrorType());
    }

    private void testHeader(XSSFSheet sheet) {
        String[] errorsHeader = {"Row No.", "Taxon", "Column", "Error value", "Error type"};

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            assertEquals(sheet.getRow(0).getCell(i).getStringCellValue(), errorsHeader[i]);
        }

    }

    private void testCellStyles(XSSFSheet sheet) {

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            CellStyle c1 = sheet.getRow(0).getCell(i).getCellStyle();
            CellStyle c2 = header.getCellStyle();

            assertEquals(c1.getFillForegroundColor(), c2.getFillForegroundColor());
            assertEquals(c1.getBorderBottom(), c2.getBorderBottom());
            assertEquals(c1.getAlignment(), c2.getAlignment());
            assertEquals(c1.getVerticalAlignment(), c2.getVerticalAlignment());
        }

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            CellStyle c1 = sheet.getRow(2).getCell(i).getCellStyle();
            CellStyle c2 = common.getCellStyle();

            assertEquals(c1.getFillForegroundColor(), c2.getFillForegroundColor());
            assertEquals(c1.getBorderBottom(), c2.getBorderBottom());
            assertEquals(c1.getAlignment(), c2.getAlignment());
            assertEquals(c1.getVerticalAlignment(), c2.getVerticalAlignment());
        }
    }
}
