package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.FootprintResults;
import ui.AppInterface;

public class FootprintsComputing extends GeneralComputing {

    //Constructor
    public FootprintsComputing(AppInterface app) {
        super(app);
    }

    //MODIFIES: ArrayList<GeneralResults> allSitesResults
    //EFFECTS: computes double footprints values from double siteAbu and saves them in allSitesResults
    // arrayList<FootprintResults>
    public void computeAllIndices() {
        double weight;
        double cp;
        double genusAbu;
        String guild;
        double footprint;

        for (Site site : siteArrayList) {
            siteSamples = site.getTaxaList();

            double structure = 0;
            double enrichment = 0;
            double functional;
            double herbivore = 0;
            double bacterial = 0;
            double predator = 0;
            double omnivore = 0;
            double fungal = 0;
            double composite = 0;
            double totalWeight = 0;

            for (Sample s : siteSamples) {

                NematodesDatabase nematode = database.get(s.getTaxaName());
                genusAbu = s.getAbundance();
                weight = nematode.getWeight();
                guild = nematode.getGuild();
                cp = nematode.getCp();
                footprint = computeFootprint(weight, cp, genusAbu);

                structure += addingValuesToFootprints(footprint, "S", guild);
                enrichment += addingValuesToFootprints(footprint, "E", guild);
                herbivore += addingValuesToFootprints(footprint, "H", guild);
                bacterial += addingValuesToFootprints(footprint, "B", guild);
                fungal += addingValuesToFootprints(footprint, "F", guild);
                omnivore += addingValuesToFootprints(footprint, "O", guild);
                predator += addingValuesToFootprints(footprint, "P", guild);

                if(genusAbu > 0)
                    totalWeight += weight*genusAbu;

                composite += footprint;
            }

            functional = structure + enrichment;

            allSitesResults.add(new FootprintResults(site.getSiteName(), structure, enrichment, functional, herbivore,
                    bacterial, fungal, omnivore, predator, composite, totalWeight));
        }
    }

    //REQUIRES: Double, String, String
    //EFFECTS: returning either double footprint value if foot equals to first letter in guild or 0
    private double addingValuesToFootprints(Double footprint, String foot, String guild) {
        String sub = guild.substring(0, 1);
        int cp = Integer.parseInt(guild.substring(1));

        if (sub.equals(foot)) {
            return footprint;
        }

        if ((guild.equals("B1") || guild.equals("F2")) && foot.equals("E")) {
            return footprint;
        }

        if (!sub.equals("H") && cp > 2 && foot.equals("S")) {
            return footprint;
        }

        return 0;
    }

    //REQUIRES: Double, Double, Double
    //EFFECTS: computes and returns double value based on the input double values
    private double computeFootprint(Double weight, Double cp, Double abundance) {
        double footprint;

        if (cp > 0 && cp < 6 && abundance > 0) {
            footprint = abundance * (0.1 * (weight / cp) + 0.273 * (Math.pow(weight, 0.75)));
            return roundValue(footprint,3);
        }
        return 0.0;
    }
}
