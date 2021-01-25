package model_test.computing_test.results_test;

import model.computing.results.TrophicResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrophicResultsTest {

    private TrophicResults testRes;

    private String name;
    private double abu;
    private double her;
    private double bac;
    private double fun;
    private double pre;
    private double omn;
    private double other;

    @Before
    public void setUp() {
        name = "Les";
        abu = 542;
        her = 12;
        bac = 45;
        fun = 78;
        pre = -45;
        omn = 0.54;
        other = 5.18;

        testRes = new TrophicResults(name, abu, her, bac, fun, pre, omn, other);
    }

    @Test
    public void testGetters() {
        assertEquals(testRes.getSiteName(), "Les");
        assertEquals(testRes.getSiteAbundance(), 542,0);
        assertEquals(testRes.getHerbivore(), 12,0);
        assertEquals(testRes.getBacterivore(), 45,0);
        assertEquals(testRes.getFungivore(), 78,0);
        assertEquals(testRes.getPredator(), -45,0);
        assertEquals(testRes.getOmnivore(), 0.54,0);
        assertEquals(testRes.getTrophicOther(), 5.18,0);
    }

}
