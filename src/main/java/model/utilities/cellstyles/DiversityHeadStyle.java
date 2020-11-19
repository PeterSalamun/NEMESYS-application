package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DiversityHeadStyle extends BasicCellStyle {

    private static final short COLOR = IndexedColors.LIGHT_ORANGE.index;

    //Constructor takes XSSFWorkbook sa parameter
    public DiversityHeadStyle(XSSFWorkbook book) {
        super(COLOR, book);
    }

}
