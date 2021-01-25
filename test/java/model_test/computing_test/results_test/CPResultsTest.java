package model_test.computing_test.results_test;

import model.computing.results.CPResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CPResultsTest {

    private CPResults testRes;
    private String name;
    private double abundance, cp1, cp2, cp3, cp4, cp5, other;

    @Before
    public void setUp() {
        name = "Les";
        abundance = 542;
        cp1 = 10;
        cp2 = 10;
        cp3 = 40;
        cp4 = 10;
        cp5 = 20;
        other = 10;

        testRes = new CPResults(name, abundance, cp1, cp2, cp3, cp4, cp5, other);
    }

    @Test
    public void testGetters() {
        assertEquals(testRes.getSiteName(), "Les");
        assertEquals(testRes.getCp1(), 10, 0);
        assertEquals(testRes.getCp2(), 10, 0);
        assertEquals(testRes.getCp3(), 40, 0);
        assertEquals(testRes.getCp4(), 10, 0);
        assertEquals(testRes.getCp5(), 20, 0);
        assertEquals(testRes.getCpOther(), 10, 0);
    }
}
