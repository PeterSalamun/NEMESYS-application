package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FunctionalHeadStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.LIGHT_GREEN.index;

    public FunctionalHeadStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

}
