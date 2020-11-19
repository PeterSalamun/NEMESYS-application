package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TrophicCPHeadCellStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.AQUA.index;

    //Constructor takes short, XSSFWorkbook as parameters
    public TrophicCPHeadCellStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

}
