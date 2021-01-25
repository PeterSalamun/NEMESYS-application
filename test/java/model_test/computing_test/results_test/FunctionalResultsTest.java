package model_test.computing_test.results_test;

import model.computing.results.FootprintResults;
import model.computing.results.FunctionalResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionalResultsTest {

    private FunctionalResults testRes;
    private String name;
    private double maturity;
    private double maturity25;
    private double plant;
    private double sumMI;
    private double sumMI25;
    private double si;
    private double ei;
    private double bi;
    private double ci;

    @Before
    public void setUp() {
        name = "Les";
        maturity = 1;
        maturity25 = 2;
        plant = 3;
        sumMI = 4;
        sumMI25 = 5;
        si = 6;
        ei = 7;
        bi = 8;
        ci = 9;

        testRes = new FunctionalResults(name, maturity, maturity25, plant, sumMI, sumMI25, si, ei, bi, ci);
    }

    @Test
    public void testGetters() {
        assertEquals(testRes.getSiteName(), "Les");
        assertEquals(testRes.getMaturity(), 1, 0);
        assertEquals(testRes.getMaturity25(), 2, 0);
        assertEquals(testRes.getPlant(), 3, 0);
        assertEquals(testRes.getSumMI(), 4, 0);
        assertEquals(testRes.getSumMI25(), 5, 0);
        assertEquals(testRes.getSi(), 6, 0);
        assertEquals(testRes.getEi(), 7, 0);
        assertEquals(testRes.getBi(), 8, 0);
        assertEquals(testRes.getCi(), 9, 0);

    }
}
