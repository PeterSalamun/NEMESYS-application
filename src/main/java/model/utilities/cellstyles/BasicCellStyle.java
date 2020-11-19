package model.utilities.cellstyles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BasicCellStyle {

    protected CellStyle style;
    protected Font font;
    protected XSSFWorkbook book;
    private short color;

    //Constructor takes short, XSSFWorkbook as parameters
    public BasicCellStyle(short color, XSSFWorkbook book) {
        this.color = color;
        this.book = book;
        initialize();
    }

    // EFFECTS: initialize fields
    protected void initialize() {
        style = book.createCellStyle();
        font = book.createFont();

        customizeStyle();
    }

    //MODIFIES: this
    //EFFECTS: set different features of this
    protected void customizeStyle() {
        setFont();
        setAlignment();
        setBorders();
        setCellFill();
    }

    //MODIFIES: this
    //EFFECTS: sets font of this
    protected void setFont() {
        font.setBold(true);
        style.setFont(font);
    }

    //MODIFIES: this
    //EFFECTS: sets alignment of this
    protected void setAlignment() {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: sets borders of this
    protected void setBorders() {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

    //MODIFIES: this
    //EFFECTS: sets color fill of this
    protected void setCellFill() {
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    //getters
    public CellStyle getCellStyle() {
        return style;
    }
}
