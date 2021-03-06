package model.computing.results;

public class FunctionalResults extends GeneralResults {

    private double maturity;
    private double maturity25;
    private double plant;
    private double sumMI;
    private double sumMI25;
    private double si;
    private double ei;
    private double bi;
    private double ci;

    //Constructor
    public FunctionalResults(String siteName, double maturity, double maturity25, double plant, double sumMI, double sumMI25, double si,
                             double ei, double bi, double ci) {
        super(siteName);
        this.siteName = siteName;
        this.maturity = maturity;
        this.maturity25 = maturity25;
        this.plant = plant;
        this.sumMI = sumMI;
        this.sumMI25 = sumMI25;
        this.si = si;
        this.ei = ei;
        this.bi = bi;
        this.ci = ci;
    }

    //getters
    public double getMaturity() {
        return maturity;
    }

    public double getMaturity25() {
        return maturity25;
    }

    public double getPlant() {
        return plant;
    }

    public double getSumMI() {
        return sumMI;
    }

    public double getSumMI25() {
        return sumMI25;
    }

    public double getSi() {
        return si;
    }

    public double getEi() {
        return ei;
    }

    public double getBi() {
        return bi;
    }

    public double getCi() {
        return ci;
    }
}
