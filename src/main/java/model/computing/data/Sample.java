package model.computing.data;

public class Sample extends Creatures {

    private Double abundance;
    private String siteName;
    private int row;

    //Constructor
    public Sample(String taxaName, double abundance, String siteName, int row) {
        super(taxaName);
        this.abundance = abundance;
        this.siteName = siteName;
        this.row = row;
    }

    //getters
    public Double getAbundance() {
        return abundance;
    }

    public String getSiteName() {
        return siteName;
    }

    public int getRow() {
        return row;
    }

    //setters
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
