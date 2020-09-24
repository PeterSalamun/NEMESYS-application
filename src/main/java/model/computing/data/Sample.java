package model.computing.data;

public class Sample extends Creatures {

    public Sample(String taxaName, double abundance, String siteName, int row) {
        super(taxaName);
        this.abundance = abundance;
        this.siteName = siteName;
        this.row = row;
    }

}
