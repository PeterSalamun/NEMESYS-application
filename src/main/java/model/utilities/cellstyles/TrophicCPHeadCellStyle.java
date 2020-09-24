package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TrophicCPHeadCellStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.AQUA.index;

    public TrophicCPHeadCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

}
