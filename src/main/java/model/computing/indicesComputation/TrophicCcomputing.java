package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.TrophicResults;
import ui.AppInterface;

public class TrophicCcomputing extends GeneralComputing {

    public TrophicCcomputing(AppInterface app) {
        super(app);
    }

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
                    bacterivore += getTrophicSorting(genusAbu, genusPreference, "b");
                    fungivore += getTrophicSorting(genusAbu, genusPreference, "f");
                    herbivore += getTrophicSorting(genusAbu, genusPreference, "h");
                    omnivore += getTrophicSorting(genusAbu, genusPreference, "o");
                    predator += getTrophicSorting(genusAbu, genusPreference, "p");
                    other += getTrophicSorting(genusAbu, genusPreference, "x");
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


    private double getTrophicSorting(double genusAbu, String preference, String group) {

        if (preference.equals("B") && group.equals("b"))
            return genusAbu;
        else if (preference.equals("F") && group.equals("f"))
            return genusAbu;
        else if (preference.equals("H") && group.equals("h"))
            return genusAbu;
        else if (preference.equals("O") && group.equals("o"))
            return genusAbu;
        else if (preference.equals("P") && group.equals("p"))
            return genusAbu;
        else if(preference.equals("D") && group.equals("x"))
            return genusAbu;
        return 0;
    }

}
