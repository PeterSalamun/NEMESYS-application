package model.computing.data;

import java.util.ArrayList;

public class Site {

    ArrayList<Sample> creatures;
    String siteName;

    public Site(String siteName, ArrayList<Sample> creatures) {
        this.siteName = siteName;
        this.creatures = creatures;
    }

    public ArrayList<Sample> getTaxaList() {
        return creatures;
    }

    public String getSiteName() {
        return siteName;
    }

}
