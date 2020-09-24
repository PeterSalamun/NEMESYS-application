package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.FunctionalResults;
import ui.AppInterface;

import java.util.ArrayList;

public class FunctionalIndicesComputing extends GeneralComputing {


    public FunctionalIndicesComputing(AppInterface app) {
        super(app);
    }


    public void computeAllIndices() {

        double siteAbu = 0;

        for (Site site : siteArrayList) {
            double abuMI = 0;
            double abuMI25 = 0;
            double abuSumMI25 = 0;
            double abuPlant = 0;
            double maturity = 0;
            double maturity25 = 0;
            double plant = 0;
            double sumMI = 0;
            double sumMI25 = 0;
            double structure = 0;
            double enrichment = 0;
            double fungi = 0;
            double basal = 0;
            double genusAbu;
            double cp;
            String guild;

            siteSamples = site.getTaxaList();
            siteAbu = getSiteAbundance(siteSamples);

            for (Sample s : siteSamples) {

                NematodesDatabase nematode = database.get(s.getTaxaName());
                genusAbu = s.getAbundance();
                cp = nematode.getCpValue();
                guild = nematode.getGuild();


                structure += sortTrajectories(guild, genusAbu, "s");
                enrichment += sortTrajectories(guild, genusAbu, "e");
                basal += sortTrajectories(guild, genusAbu, "b");
                fungi += sortTrajectories(guild, genusAbu, "c");


                double suma = genusAbu * cp;


                if (cp > 1) {
                    sumMI25 += suma;
                    abuSumMI25 += genusAbu;
                }
                if (checkNotHerbivoryGuild(guild)) {
                    maturity += suma;
                    abuMI += genusAbu;
                }
                if (checkNotHerbivoryGuild(guild) && cp > 1) {
                    maturity25 += suma;
                    abuMI25 += genusAbu;
                }
                if (!checkNotHerbivoryGuild(guild) && cp > 0) {
                    plant += suma;
                    abuPlant += genusAbu;
                }

                sumMI += suma;


            }

            allSitesResults.add(new FunctionalResults(site.getSiteName(),
                    computeMaturityIndices(maturity, abuMI),
                    computeMaturityIndices(maturity25, abuMI25),
                    computeMaturityIndices(plant, abuPlant),
                    computeMaturityIndices(sumMI, siteAbu),
                    computeMaturityIndices(sumMI25, abuSumMI25),
                    computeFoodWebIndices(structure, basal),
                    computeFoodWebIndices(enrichment, basal),
                    computeBasal(basal, structure, enrichment),
                    computeChannel(fungi, enrichment)));
        }
    }

    private boolean checkNotHerbivoryGuild(String guild) {
        String sub1 = guild.substring(0, 1);
        int sub2 = Integer.parseInt(guild.substring(1));

        if (sub1.equals("B") || sub1.equals("F") || sub1.equals("P") || sub1.equals("O"))
            return sub2 > 0 && sub2 < 6;

        return false;
    }

    private double sortTrajectories(String guild, double genusAbu, String trajectory) {
        String trophic = guild.substring(0, 1);
        int cp = Integer.parseInt(guild.substring(1));

        if (guild.equals("B1") && trajectory.equals("e"))
            return genusAbu * 3.2;

        if (guild.equals("B2") && trajectory.equals("b"))
            return genusAbu * 0.8;

        if (guild.equals("F2") && (trajectory.equals("b") || trajectory.equals("e") || trajectory.equals("c")))
            return genusAbu * 0.8;

        if (!trophic.equals("H") && trajectory.equals("s")) {

            if (cp == 3)
                return genusAbu * 1.8;
            if (cp == 4)
                return genusAbu * 3.2;
            if (cp == 5)
                return genusAbu * 5.0;
        }
        return 0;
    }

    private double computeMaturityIndices(double indexValue, double siteAbu) {
        return roundValue(indexValue / siteAbu, 3);
    }

    private double computeFoodWebIndices(double value1, double value2) {
        return roundValue((100 * value1) / (value1 + value2), 3);

    }

    private double computeChannel(double fungi, double enrichment) {
        return roundValue((100 * fungi) / enrichment, 3);
    }

    private double computeBasal(double basal, double structure, double enrichment) {
        return roundValue((100 * basal) / (structure + basal + enrichment), 3);
    }

    protected double getSiteAbundance(ArrayList<Sample> samplesList) {
        double siteAbu = 0;
        for (Sample s : samplesList)
            if (database.get(s.getTaxaName()).getCpValue() > 0)
                siteAbu += s.getAbundance();

        return siteAbu;
    }

}
