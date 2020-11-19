package model.computing.results;

public class DiversityResults extends GeneralResults{

    private double abundance;
    private double speciesNo;
    private double brillDiversity;
    private double brillMaxDiv;
    private double brillMinDiv;
    private double brillEven;
    private double brillRelEven;
    private double heip;
    private double hillN1;
    private double hillN2;
    private double hillEven;
    private double margalef;
    private double menhinick;
    private double shannon;
    private double simpson;

    //Constructor
    public DiversityResults(String siteName, double abundance, double speciesNo, double brillDiversity,
                            double brillMaxDiv, double brillMinDiv, double brillEven, double brillRelEven, double heip,
                            double hillEven, double hillN1, double hillN2, double margalef, double menhinick,
                            double shannon, double simpson) {
        super(siteName);
        this.abundance = abundance;
        this.speciesNo = speciesNo;
        this.brillDiversity = brillDiversity;
        this.brillMaxDiv = brillMaxDiv;
        this.brillMinDiv = brillMinDiv;
        this.brillEven = brillEven;
        this.brillRelEven = brillRelEven;
        this.heip = heip;
        this.hillEven = hillEven;
        this.hillN1 = hillN1;
        this.hillN2 = hillN2;
        this.margalef = margalef;
        this.menhinick = menhinick;
        this.shannon = shannon;
        this.simpson = simpson;
    }

    //getters
    public double getAbundance() {
        return abundance;
    }

    public double getSpeciesNo() {
        return speciesNo;
    }

    public double getBrillDiversity() {
        return brillDiversity;
    }

    public double getBrillMaxDiv() {
        return brillMaxDiv;
    }

    public double getBrillMinDiv() {
        return brillMinDiv;
    }

    public double getBrillEven() {
        return brillEven;
    }

    public double getBrillRelEven() {
        return brillRelEven;
    }

    public double getHeip() {
        return heip;
    }

    public double getHillEven() {
        return hillEven;
    }

    public double getHillN1() {
        return hillN1;
    }

    public double getHillN2() {
        return hillN2;
    }

    public double getMargalef() {
        return margalef;
    }

    public double getMenhinick() {
        return menhinick;
    }

    public double getShannon() {
        return shannon;
    }

    public double getSimpson() {
        return simpson;
    }

}
