package model.computing.indicesComputation;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.FootprintResults;
import ui.AppInterface;

public class FootprintsComputing extends GeneralComputing {


    public FootprintsComputing(AppInterface app) {
        super(app);
    }


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
                cp = nematode.getCpValue();
                footprint = computeFootprint(weight, cp, genusAbu);

                structure += addingValuesToFootprints(footprint, "s", guild);
                enrichment += addingValuesToFootprints(footprint, "e", guild);
                herbivore += addingValuesToFootprints(footprint, "h", guild);
                bacterial += addingValuesToFootprints(footprint, "b", guild);
                fungal += addingValuesToFootprints(footprint, "f", guild);
                omnivore += addingValuesToFootprints(footprint, "o", guild);
                predator += addingValuesToFootprints(footprint, "p", guild);

                if(genusAbu > 0)
                    totalWeight += weight*genusAbu;

                composite += footprint;
            }

            functional = structure + enrichment;

            allSitesResults.add(new FootprintResults(site.getSiteName(), structure, enrichment, functional, herbivore,
                    bacterial, fungal, omnivore, predator, composite, totalWeight));
        }
    }


    private double addingValuesToFootprints(Double footprint, String foot, String guild) {
        String sub = guild.substring(0, 1);
        int cp = Integer.parseInt(guild.substring(1));

        if (sub.equals("H") && foot.equals("h")) {
            return footprint;
        }

        if (sub.equals("B") && foot.equals("b")) {
            return footprint;
        }

        if (sub.equals("F") && foot.equals("f")) {
            return footprint;
        }

        if (sub.equals("O") && foot.equals("o")) {
            return footprint;
        }

        if (sub.equals("P") && foot.equals("p")) {
            return footprint;
        }

        if ((guild.equals("B1") || guild.equals("F2")) && foot.equals("e")) {
            return footprint;
        }

        if (!sub.equals("H") && cp > 2 && foot.equals("s")) {
            return footprint;
        }

        return 0;
    }

    private double computeFootprint(Double weight, Double cp, Double abundance) {
        double footprint;

        if (cp > 0 && cp < 6 && abundance > 0) {
            footprint = abundance * (0.1 * (weight / cp) + 0.273 * (Math.pow(weight, 0.75)));
            return roundValue(footprint,3);
        }
        return 0.0;
    }
}
