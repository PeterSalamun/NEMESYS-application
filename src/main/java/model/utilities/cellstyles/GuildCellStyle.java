package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GuildCellStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.LIGHT_CORNFLOWER_BLUE.index;

    //Constructor takes short, XSSFWorkbook as parameters
    public GuildCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
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
