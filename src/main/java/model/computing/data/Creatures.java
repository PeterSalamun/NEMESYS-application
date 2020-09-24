package model.computing.data;

public class Creatures {

    String taxaName;
    Double abundance;
    String siteName;
    int row;

    public Creatures(String taxaName) {
        this.taxaName = taxaName;
    }

    public String getTaxaName() {
        return taxaName;
    }

    public Double getAbundance() {
        return abundance;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
