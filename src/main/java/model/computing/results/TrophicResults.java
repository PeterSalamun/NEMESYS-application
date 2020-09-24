package model.computing.results;

public class TrophicResults extends GeneralResults {

    private double siteAbundance;
    private double herbivore;
    private double bacterivore;
    private double fungivore;
    private double predator;
    private double omnivore;
    private double trophicOther;


    public TrophicResults(String name, double siteAbundance, double herbivore, double bacterivore, double fungivore, double predator,
                          double omnivore, double other) {
        super(name);
        this.siteAbundance = siteAbundance;
        this.herbivore = herbivore;
        this.bacterivore = bacterivore;
        this.fungivore = fungivore;
        this.predator = predator;
        this.omnivore = omnivore;
        this.trophicOther = other;
    }

    public double getSiteAbundance() {
        return siteAbundance;
    }

    public double getHerbivore() {
        return herbivore;
    }

    public double getBacterivore() {
        return bacterivore;
    }

    public double getFungivore() {
        return fungivore;
    }

    public double getPredator() {
        return predator;
    }

    public double getOmnivore() {
        return omnivore;
    }

    public double getTrophicOther() {
        return trophicOther;
    }

}
