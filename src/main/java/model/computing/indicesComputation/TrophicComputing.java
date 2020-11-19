package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.TrophicResults;
import ui.AppInterface;

public class TrophicComputing extends GeneralComputing {

    //Constructor
    public TrophicComputing(AppInterface app) {
        super(app);
    }

    //MODIFIES: ArrayList<GeneralResults> allSitesResults
    //EFFECTS: computes double functional indices values from double siteAbu and saves them in allSitesResults
    // arrayList<TrophicResults>
    @Override
    protected void computeAllIndices() {

        double siteAbu;

        for (Site site : siteArrayList) {

            siteSamples = site.getTaxaList();
            siteAbu = getSiteAbundance(siteSamples);


            double bacterivore = 0;
            double fungivore = 0;
            double herbivore = 0;
            double omnivore = 0;
            double predator = 0;
            double other = 0;

            double genusAbu;


            String genusName;
            String genusPreference;

            NematodesDatabase nematode;

            for (Sample s : site.getTaxaList()) {

                genusAbu = s.getAbundance();
                genusName = s.getTaxaName();
                nematode = database.get(genusName);
                genusPreference = nematode.getFoodShort();


                if (genusAbu > 0) {
                    bacterivore += getTrophicSorting(genusAbu, genusPreference, "B");
                    fungivore += getTrophicSorting(genusAbu, genusPreference, "F");
                    herbivore += getTrophicSorting(genusAbu, genusPreference, "H");
                    omnivore += getTrophicSorting(genusAbu, genusPreference, "O");
                    predator += getTrophicSorting(genusAbu, genusPreference, "P");
                    other += getTrophicSorting(genusAbu, genusPreference, "D");
                }
            }

            allSitesResults.add(new TrophicResults(
                    site.getSiteName(),
                    siteAbu,
                    percentage(herbivore, siteAbu),
                    percentage(bacterivore, siteAbu),
                    percentage(fungivore, siteAbu),
                    percentage(predator, siteAbu),
                    percentage(omnivore, siteAbu),
                    percentage(other, siteAbu)));
        }
    }

    //REQUIRES: double, String, String
    //EFFECTS: return double is String preference equal String group or 0
    private double getTrophicSorting(double genusAbu, String preference, String group) {

        if (preference.equals(group))
            return genusAbu;

        return 0;
    }

}
