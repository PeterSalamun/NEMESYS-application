package model.computing.results;

import java.util.TreeMap;

public class
DominancyResults extends GeneralResults{

    private TreeMap<String, Double> taxaDominancy;

    //Constructor
    public DominancyResults(String name, TreeMap<String, Double> taxaDominancy) {
        super(name);
        this.taxaDominancy = taxaDominancy;
    }

    //getters
    public TreeMap<String, Double> getTaxaDominancy() {return taxaDominancy;}
}
