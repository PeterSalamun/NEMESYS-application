package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DominancyHeadCellStyle extends BasicCellStyle {


    private static final short COLOR = IndexedColors.CORAL.index;

    public DominancyHeadCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

    protected void setFont() {
        font.setBold(true);
        style.setFont(font);
    }
}
