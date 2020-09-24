package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BasicCellStyle {

    CellStyle style;
    Font font;
    XSSFWorkbook book;

    private short color;

    public BasicCellStyle(short color, XSSFWorkbook book) {
        this.color = color;
        this.book = book;
        initialize();
    }

    private void initialize() {
        style = book.createCellStyle();
        font = book.createFont();

        customizeStyle();
    }

    private void customizeStyle() {
        setFont();
        setAlignment();
        setBorders();
        setCellFill();
    }

    protected void setFont() {
        font.setBold(true);
        style.setFont(font);
        }

    private void setAlignment() {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    protected void setBorders() {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

    private void setCellFill() {
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public CellStyle getCellStyle() {
        return style;
    }
}
