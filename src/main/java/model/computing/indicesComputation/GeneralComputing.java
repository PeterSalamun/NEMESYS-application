package model.computing.indicesComputation;

import ch.obermuhlner.math.big.BigDecimalMath;
import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.GeneralResults;
import org.apache.commons.math3.util.Precision;
import ui.AppInterface;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class GeneralComputing {

    protected ArrayList<Site> siteArrayList;
    protected ArrayList<Sample> siteSamples;
    protected HashMap<String, NematodesDatabase> database;
    protected AppInterface app;
    protected ArrayList<GeneralResults> allSitesResults;


    public GeneralComputing(AppInterface app) {
        this.app = app;
        this.database = app.getDatabaseMap();
        this.allSitesResults = new ArrayList<>();
    }

    protected abstract void computeAllIndices();

    protected double roundValue(double number, int decimalPlaces) {

        Double f = number;
        if (f.isNaN())
            return 0.0;

        return Precision.round(number, decimalPlaces, 4);
    }

    protected BigDecimal factorial(double num) {
        BigDecimal fact = BigDecimal.ONE;

        for (int i = (int) num; i > 0; i--) {
            fact = fact.multiply(new BigDecimal(String.valueOf(i)));
        }
        return fact;
    }

    protected BigDecimal naturalLOG(BigDecimal val) {
        MathContext context = new MathContext(15);
        return BigDecimalMath.log(val, context);
    }

    protected BigDecimal bigDecimalPow(BigDecimal base, Double exp) {
        MathContext context = new MathContext(15);
        return BigDecimalMath.pow(base, BigDecimal.valueOf(exp), context);
    }

    public ArrayList<GeneralResults> computeAllSitesResults(ArrayList<Site> sites) {
        siteArrayList = sites;
        computeAllIndices();
        return allSitesResults;
    }

    protected double getSiteAbundance(ArrayList<Sample> samplesList) {
        double siteAbu = 0;
        for (Sample s : samplesList)
            siteAbu += s.getAbundance();

        return siteAbu;
    }

    protected double percentage(double value1, double value2) {
        return roundValue((value1 / value2) * 100, 2);
    }
}

