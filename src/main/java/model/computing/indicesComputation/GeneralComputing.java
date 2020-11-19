package model.computing.indicesComputation;

import ch.obermuhlner.math.big.BigDecimalMath;
import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.results.GeneralResults;
import org.apache.commons.math3.util.Precision;
import ui.AppInterface;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class GeneralComputing {

    protected ArrayList<Site> siteArrayList;
    protected ArrayList<Sample> siteSamples;
    protected HashMap<String, NematodesDatabase> database;
    protected AppInterface app;
    protected ArrayList<GeneralResults> allSitesResults;

    //Constructor
    public GeneralComputing(AppInterface app) {
        this.app = app;
        initializeFields();
    }

    //EFFECTS: initialize fields in the class
    private void initializeFields() {
        this.database = app.getDatabaseMap();
        this.allSitesResults = new ArrayList<>();
    }

    //EFFECTS: abstract method
    protected abstract void computeAllIndices();

    //REQUIRES: double, int
    //MODIFIES: double number
    //EFFECTS: if number is not NaN, than the number is rounded to int decimalPlaces places
    protected double roundValue(double number, int decimalPlaces) {
        if (Double.isNaN(number))
            return 0.0;

        return Precision.round(number, decimalPlaces, 4);
    }

    //REUIRES: double
    //EFFECTS: computes factorial of double num and return BigDecimal value
    protected BigDecimal factorial(double num) {
        BigDecimal fact = BigDecimal.ONE;

        for (int i = (int) num; i > 0; i--) {
            fact = fact.multiply(new BigDecimal(String.valueOf(i)));
        }
        return fact;
    }

    //REQUIRES: BigDecimal
    //EFFECTS: return logarithm of BigDecimal val
    protected BigDecimal naturalLOG(BigDecimal val) {
        MathContext context = new MathContext(15);
        return BigDecimalMath.log(val, context);
    }

    //REQUIRES: ArrayList<Sample>
    //EFFECTS: return double representing the total abundance for site composed from samples abundance contained in
    // the arrayList
    protected double getSiteAbundance(ArrayList<Sample> samplesList) {
        double siteAbu = 0;
        for (Sample s : samplesList){
            siteAbu += s.getAbundance();}

        return siteAbu;
    }

    //REQUIRES: double, double
    //EFFECTS: return double representing percentage of double value1 in double value2 and rounded to 2 decimal places
    protected double percentage(double value1, double value2) {
        return roundValue((value1 / value2) * 100, 2);
    }

    //REQUIRES: ArrayList<Site>
    //MODIFIES: allSitesResults arrayList
    //EFFECTS: calls computeAllIndices() method to compute indices for particular class and return
    // ArrayList<GeneralResults> allSitesResults
    public ArrayList<GeneralResults> computeAllSitesResults(ArrayList<Site> sites) {
        siteArrayList = sites;
        allSitesResults.clear();
        computeAllIndices();
        return allSitesResults;
    }
}

