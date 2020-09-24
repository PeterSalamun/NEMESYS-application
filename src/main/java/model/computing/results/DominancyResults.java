package model.computing.results;

import java.util.TreeMap;

public class
DominancyResults extends GeneralResults{

    TreeMap<String, Double> taxaDominancy;

    public DominancyResults(String name, TreeMap<String, Double> taxaDominancy) {
        super(name);
        this.taxaDominancy = taxaDominancy;
    }

    public TreeMap<String, Double> getTaxaDominancy() {return taxaDominancy;}
}
