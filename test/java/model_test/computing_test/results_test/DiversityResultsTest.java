package model_test.computing_test.results_test;

import model.computing.results.CPResults;
import model.computing.results.DiversityResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiversityResultsTest {

    private DiversityResults testRes;
    private String name;
    private double abundance;
    private double speciesNo;
    private double brillDiversity;
    private double brillMaxDiv;
    private double brillMinDiv;
    private double brillEven;
    private double brillRelEven;
    private double heip;
    private double hillN1;
    private double hillN2;
    private double hillEven;
    private double margalef;
    private double menhinick;
    private double shannon;
    private double simpson;

    @Before
    public void setUp() {
        name = "Les";
        abundance = 542;
        speciesNo = 15;
        brillDiversity = 20;
        brillMaxDiv = 53;
        brillMinDiv = 254;
        brillEven = -10;
        brillRelEven = 0;
        heip = 4;
        hillN1 = 5;
        hillN2 = 78;
        hillEven = 25;
        margalef = 12;
        menhinick = 8;
        shannon = 0.88;
        simpson = -0.333;

        testRes = new DiversityResults(name, abundance, speciesNo, brillDiversity, brillMaxDiv, brillMinDiv, brillEven,
                brillRelEven, heip, hillN1, hillN2, hillEven, margalef, menhinick, shannon, simpson);
    }

    @Test
    public void testGetters() {
        assertEquals(testRes.getSiteName(), "Les");
        assertEquals(testRes.getAbundance(), 542, 0);
        assertEquals(testRes.getSpeciesNo(), 15, 0);
        assertEquals(testRes.getBrillDiversity(), 20, 0);
        assertEquals(testRes.getBrillMaxDiv(), 53, 0);
        assertEquals(testRes.getBrillMinDiv(), 254, 0);
        assertEquals(testRes.getBrillEven(), -10, 0);
        assertEquals(testRes.getBrillRelEven(), 0, 0);
        assertEquals(testRes.getHeip(), 4, 0);
        assertEquals(testRes.getHillEven(), 5, 0);
        assertEquals(testRes.getHillN1(), 78, 0);
        assertEquals(testRes.getHillN2(), 25, 0);
        assertEquals(testRes.getMargalef(), 12, 0);
        assertEquals(testRes.getMenhinick(), 8, 0);
        assertEquals(testRes.getShannon(), 0.88, 0.001);
        assertEquals(testRes.getSimpson(), -0.333, 0.0001);
    }
}
