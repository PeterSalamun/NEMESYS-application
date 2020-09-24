package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FirstCellStyle extends BasicCellStyle {


    private static final short COLOR = IndexedColors.GREY_25_PERCENT.index;

    public FirstCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

    protected void setBorders() {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}
