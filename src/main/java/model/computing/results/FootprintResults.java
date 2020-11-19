package model.computing.results;

public class FootprintResults extends GeneralResults{

    private double structure;
    private double enrichment;
    private double functional;
    private double herbivore;
    private double bacterial;
    private double fungal;
    private double omnivore;
    private double predator;
    private double composite;
    private double weight;

    //Constructor
    public FootprintResults(String name, double structure, double enrichment, double functional, double herbivore,
                            double bacterial, double fungal, double omnivore, double predator, double composite, double weight) {
        super(name);
        this.structure = structure;
        this.enrichment = enrichment;
        this.functional = functional;
        this.herbivore = herbivore;
        this.bacterial = bacterial;
        this.fungal = fungal;
        this.omnivore = omnivore;
        this.predator = predator;
        this.composite = composite;
        this.weight = weight;
    }

    //getters
    public double getStructure() {
        return structure;
    }

    public double getEnrichment() {
        return enrichment;
    }

    public double getFunctional() {
        return functional;
    }

    public double getHerbivore() {
        return herbivore;
    }

    public double getBacterial() {
        return bacterial;
    }

    public double getFungal() {
        return fungal;
    }

    public double getOmnivore() {
        return omnivore;
    }

    public double getPredator() {
        return predator;
    }

    public double getComposite() {
        return composite;
    }

    public double getWeight() {
        return weight;
    }
}
