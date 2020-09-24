package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FootprintHeadStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.LIGHT_YELLOW.index;

    public FootprintHeadStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }
}
