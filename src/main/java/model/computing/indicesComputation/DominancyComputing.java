package model.computing.indicesComputation;

import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.DominancyResults;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.TreeMap;

public class DominancyComputing extends GeneralComputing {

    ArrayList<DominancyResults> res;

    public DominancyComputing(AppInterface app) {
        super(app);
        res = new ArrayList<>();
    }

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
