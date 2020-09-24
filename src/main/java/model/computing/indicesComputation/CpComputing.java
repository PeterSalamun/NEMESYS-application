package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.CPResults;
import ui.AppInterface;

public class CpComputing extends GeneralComputing {

    public CpComputing(AppInterface app) {
        super(app);
    }

    @Override
    protected void computeAllIndices() {

        double siteAbu;

        for (Site site : siteArrayList) {

            siteSamples = site.getTaxaList();
            siteAbu = getSiteAbundance(siteSamples);


            double cp1 = 0;
            double cp2 = 0;
            double cp3 = 0;
            double cp4 = 0;
            double cp5 = 0;
            double dauer = 0;

            double genusAbu;
            double genusCP;

            String genusName;

            NematodesDatabase nematode;

            for (Sample s : siteSamples) {

                genusAbu = s.getAbundance();
                genusName = s.getTaxaName();
                nematode = database.get(genusName);
                genusCP = nematode.getCpValue();

                if (genusAbu > 0) {
                    cp1 += getCPsorting(genusAbu, genusCP, 1);
                    cp2 += getCPsorting(genusAbu, genusCP, 2);
                    cp3 += getCPsorting(genusAbu, genusCP, 3);
                    cp4 += getCPsorting(genusAbu, genusCP, 4);
                    cp5 += getCPsorting(genusAbu, genusCP, 5);
                    dauer += getCPsorting(genusAbu, genusCP, 0);
                }
            }
            allSitesResults.add(new CPResults(
                    site.getSiteName(),
                    siteAbu,
                    percentage(cp1, siteAbu),
                    percentage(cp2, siteAbu),
                    percentage(cp3, siteAbu),
                    percentage(cp4, siteAbu),
                    percentage(cp5, siteAbu),
                    percentage(dauer, siteAbu)));
        }
    }

    private double getCPsorting(double genusAbu, double genusCP, int group) {
        if (genusCP == 0 && group == 0)
            return genusAbu;
        else if (genusCP == 1 && group == 1)
            return genusAbu;
        else if (genusCP == 2 && group == 2)
            return genusAbu;
        else if (genusCP == 3 && group == 3)
            return genusAbu;
        else if (genusCP == 4 && group == 4)
            return genusAbu;
        else if (genusCP == 5 && group == 5)
            return genusAbu;

        return 0;
    }

}
