package model.computing.results;

import java.util.ArrayList;

public abstract class GeneralResults {

    String siteName;
    ArrayList<Boolean> boxesUsed;

    public GeneralResults(String name) {
        this.siteName = name;

        initialize();
    }

    protected void initialize() {
        boxesUsed = new ArrayList<>();
    }

    public String getSiteName() {
        return siteName;
    }
}
