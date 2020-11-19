package model.computing.indicesComputation;

import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.DominancyResults;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.TreeMap;

public class DominancyComputing extends GeneralComputing {

    private ArrayList<DominancyResults> res;

    //Constructor
    public DominancyComputing(AppInterface app) {
        super(app);
        initialize();
    }

    //EFFECTS: initialize arrayList<> res
    private void initialize() {
        res = new ArrayList<>();
    }

    //MODIFIES: ArrayList<GeneralResults> allSitesResults
    //EFFECTS: computes double dominance values from double siteAbu, saves them in
    // res arrayList<DominancyResults>. The whole res arrayList for each site is than add in to
    // allSitesResults ArrayList<DominancyResults>
    @Override
    protected void computeAllIndices() {

        double siteAbu;

        for (Site site : siteArrayList) {

            double genusAbu;
            String genusName;
            TreeMap<String, Double> taxaDominancy = new TreeMap<>();
            siteSamples = site.getTaxaList();
            siteAbu = getSiteAbundance(siteSamples);

            for (Sample s : siteSamples) {

                genusName = s.getTaxaName();
                genusAbu = s.getAbundance();

                taxaDominancy.put(genusName, percentage(genusAbu, siteAbu));
            }

            allSitesResults.add(new DominancyResults(site.getSiteName(), taxaDominancy));
        }
    }
}
