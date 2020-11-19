package model.computing.indicesComputation;


import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.DiversityResults;
import ui.AppInterface;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class DiversityComputing extends GeneralComputing {

    //Constructor
    public DiversityComputing(AppInterface app) {
        super(app);
    }

    //MODIFIES: ArrayList<GeneralResults> allSitesResults
    //EFFECTS: computes double diversity indices values from double siteAbu and double siteSpeciesNo and saves them in
    // allSitesResults arrayList<DiversityResults>
    protected void computeAllIndices() {

        double genusAbu;

        for (Site site : siteArrayList) {

            double siteAbu = 0;
            double siteSpeciesNo = 0;
            double partBrill = 0;
            double partHill = 0;
            double partShannon = 0;
            double partSimpson = 0;

            siteSamples = site.getTaxaList();
            siteAbu = getSiteAbundance(siteSamples);

            for (Sample s : siteSamples) {
                genusAbu = s.getAbundance();

                if (genusAbu > 0) {
                    siteSpeciesNo++;
                    partBrill += Math.log(factorial(genusAbu).doubleValue());
                    partSimpson += genusAbu * (genusAbu - 1);

                    partHill += Math.pow((genusAbu / siteAbu), 2.0);
                    partShannon += ((genusAbu / siteAbu) * (Math.log((genusAbu / siteAbu))));
                }
            }

            double shannon = computeShannon(partShannon);
            double simpson = computeSimpson(partSimpson, siteAbu);
            double brillDiversity = computeBrillDiversity(partBrill, siteAbu);
            double brillMaxDiv = computeBrillMaxDiv(siteAbu, siteSpeciesNo);
            double brillMinDiv = computeBrillMinDiv(siteAbu, siteSpeciesNo);
            double brillEven = computeBrillEven(brillDiversity, brillMaxDiv);
            double brillRelEven = computeBrillRelEven(brillDiversity, brillMaxDiv, brillMinDiv);
            double heip = computeHeip(shannon, siteSpeciesNo);
            double hillN1 = computeHillN1(shannon);
            double hillN2 = computeHillN2(partHill);
            double hillEven = computeHillEven(hillN2, hillN1);
            double margalef = computeMargalef(siteAbu, siteSpeciesNo);
            double menhinick = computeMenhinick(siteAbu, siteSpeciesNo);

            allSitesResults.add(new DiversityResults(site.getSiteName(), siteAbu, siteSpeciesNo, brillDiversity, brillMaxDiv,
                    brillMinDiv, brillEven, brillRelEven, heip, hillEven, hillN1, hillN2, margalef, menhinick, shannon,
                    simpson));
        }
    }

    //EFFECTS: each method bellow computes different index and return its double value rounded to three decimal
    // places (roundValue(double, int) method)
    //Equations according to https://cran.r-project.org/web/packages/tabula/vignettes/diversity.html
    private double computeBrillDiversity(double partBrill, double siteAbu) {
        return roundValue((naturalLOG(factorial(siteAbu)).doubleValue() - partBrill) / siteAbu, 3);
    }

    private double computeBrillMaxDiv(double siteAbu, double siteSpeciesNo) {

        int X = (int) (siteAbu / siteSpeciesNo);
        double r = siteAbu % siteSpeciesNo;

        double res = (naturalLOG(factorial(siteAbu)).doubleValue() - (siteSpeciesNo - r) *
                naturalLOG(factorial(X)).doubleValue() - r * naturalLOG(factorial(X + 1)).doubleValue()) / siteAbu;

        return roundValue(res, 3);
    }

    private double computeBrillMinDiv(double siteAbu, double siteSpeciesNo) {
        double partBrill = naturalLOG(factorial(siteAbu).divide(factorial((siteAbu - siteSpeciesNo + 1)))).doubleValue();
        return roundValue(((1 / siteAbu) * partBrill), 3);
    }

    private double computeBrillEven(double brillDiversity, double brillMaxDiv) {
        return roundValue(brillDiversity / brillMaxDiv, 3);

    }

    private double computeBrillRelEven(double brillDiversity, double brillMaxDiv, double brillMinDiv) {
        return roundValue((brillDiversity - brillMinDiv) / (brillMaxDiv - brillMinDiv), 3);
    }

    private double computeHeip(double shannon, double siteSpeciesNo) {
        return roundValue((Math.exp(shannon) - 1) / (siteSpeciesNo - 1), 3);
    }

    private double computeHillEven(double hillN2, double hillN1) {
        return roundValue(hillN2 / hillN1, 3);
    }

    private double computeHillN1(double shannon) {
        return roundValue(Math.exp(shannon), 3);
    }

    private double computeHillN2(double partHill) {
        return roundValue(1.0 / partHill, 3);
    }

    private double computeMargalef(double siteAbu, double siteSpeciesNo) {
        return roundValue((siteSpeciesNo - 1) / Math.log(siteAbu), 3);
    }

    private double computeMenhinick(double siteAbu, double siteSpeciesNo) {
        return roundValue(siteSpeciesNo / Math.sqrt(siteAbu), 3);
    }

    private double computeShannon(double partShannon) {
        return roundValue(Math.abs(partShannon), 3);
    }

    private double computeSimpson(double partSimpson, double siteAbu) {
        return roundValue(partSimpson / (siteAbu * (siteAbu - 1)), 3);
    }

}
