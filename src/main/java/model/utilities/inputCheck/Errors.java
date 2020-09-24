package model.utilities.inputCheck;

public class Errors {

    String column, errorType, taxon, rowNo, errorValue;

    public Errors(String column, String errorType, String taxon, String rowNo, String errorValue) {
        this.column = column;
        this.errorType = errorType;
        this.taxon = taxon;
        this.rowNo = rowNo;
        this.errorValue = errorValue;
    }

    public String getSite() {
        return column;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getTaxon() {
        return taxon;
    }

    public String getRowNo() {
        return rowNo;
    }

    public String getErrorValue() {
        return errorValue;
    }
}
