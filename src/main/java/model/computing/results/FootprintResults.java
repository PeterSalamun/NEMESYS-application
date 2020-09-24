package model.computing.results;

public class FootprintResults extends GeneralResults{

    double structure;
    double enrichment;
    double functional;
    double herbivore;
    double bacterial;
    double fungal;
    double omnivore;
    double predator;
    double composite;
    double weight;

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
