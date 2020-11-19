package model.computing.data;

import java.util.ArrayList;

public class Site {

    private ArrayList<Sample> creatures;
    private String siteName;

    //Constructor
    public Site(String siteName, ArrayList<Sample> creatures) {
        this.siteName = siteName;
        this.creatures = creatures;
    }

    //getters
    public ArrayList<Sample> getTaxaList() {
        return creatures;
    }

    public String getSiteName() {
        return siteName;
    }

}
