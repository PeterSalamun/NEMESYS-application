package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonCellStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.WHITE.index;

    //Constructor takes XSSFWorkbook
    public CommonCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

    //MODIFIES: this
    //EFFECTS: sets font of this
    protected void setFont() {
        font.setBold(false);
        style.setFont(font);
    }

    //MODIFIES: this
    //EFFECTS: sets borders of this
    protected void setBorders() {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}
