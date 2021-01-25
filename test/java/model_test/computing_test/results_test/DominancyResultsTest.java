package model_test.computing_test.results_test;

import model.computing.results.DominancyResults;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class DominancyResultsTest {

    private DominancyResults testRes;
    private String name, t1, t2, t3, t4, t5;
    private double d1, d2, d3, d4, d5;
    private TreeMap<String, Double> testMap;


    @Before
    public void setUp() {
        name = "Les";
        t1 = "bocian";
        t2 = "nematod";
        t3 = "jez";
        t4 = "zajac";
        t5 = "ryba";

        d1 = 15;
        d2 = 0;
        d3 = 26;
        d4 = 2;
        d5 = 100 - (d1 + d2 + d3 + d4);

        testMap = new TreeMap<>();
        testMap.put(t1,d1);
        testMap.put(t2,d2);
        testMap.put(t3,d3);
        testMap.put(t4,d4);
        testMap.put(t5,d5);

        testRes = new DominancyResults(name, testMap);
    }

    @Test
    public void testGetTaxaDominancy() {
        assertEquals(testRes.getTaxaDominancy().size(), 5);
    }

    @Test
    public void testGetName() {
        assertEquals(testRes.getSiteName(), "Les");
    }
}
