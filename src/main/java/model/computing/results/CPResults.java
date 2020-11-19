package model.computing.results;

public class CPResults extends GeneralResults {

    private double siteAbundance;
    private double cp1;
    private double cp2;
    private double cp3;
    private double cp4;
    private double cp5;
    private double cpOther;

    //Constructor
    public CPResults(String name, double siteAbundance, double cp1, double cp2, double cp3, double cp4, double cp5, double cpOther) {
        super(name);
        this.siteAbundance = siteAbundance;
        this.cp1 = cp1;
        this.cp2 = cp2;
        this.cp3 = cp3;
        this.cp4 = cp4;
        this.cp5 = cp5;
        this.cpOther = cpOther;
    }

    //getters
    public double getCp1() {
        return cp1;
    }

    public double getCp2() {
        return cp2;
    }

    public double getCp3() {
        return cp3;
    }

    public double getCp4() {
        return cp4;
    }

    public double getCp5() {
        return cp5;
    }

    public double getCpOther() {
        return cpOther;
    }
}
