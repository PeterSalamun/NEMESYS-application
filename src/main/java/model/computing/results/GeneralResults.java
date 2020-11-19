package model.computing.results;

import java.util.ArrayList;

public abstract class GeneralResults {

    protected String siteName;
    protected ArrayList<Boolean> boxesUsed;

    //Constuctor
    public GeneralResults(String name) {
        this.siteName = name;
        initialize();
    }

    //MODIFIES: ArrayList<>()
    //EFFECTS: initilaize arrayList<Boolean> boxesUsed;
    protected void initialize() {
        boxesUsed = new ArrayList<>();
    }

    //getters
    public String getSiteName() {
        return siteName;
    }
}
