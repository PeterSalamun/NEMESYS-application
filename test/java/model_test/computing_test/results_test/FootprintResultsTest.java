package model_test.computing_test.results_test;

import model.computing.results.CPResults;
import model.computing.results.FootprintResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootprintResultsTest {

    private FootprintResults testRes;
    private String name;
    private double structure;
    private double enrichment;
    private double functional;
    private double herbivore;
    private double bacterial;
    private double fungal;
    private double omnivore;
    private double predator;
    private double composite;
    private double weight;

    @Before
    public void setUp() {
        name = "Les";
        structure = 1;
        enrichment = 2;
        functional = 3;
        herbivore = 4;
        bacterial = 5;
        fungal = 6;
        omnivore = 7;
        predator = 8;
        composite = 9;
        weight = 10;

        testRes = new FootprintResults(name, structure, enrichment, functional, herbivore, bacterial, fungal, omnivore,
                predator, composite, weight);
    }

    @Test
    public void testGetters() {
        assertEquals(testRes.getSiteName(), "Les");
        assertEquals(testRes.getStructure(), 1, 0);
        assertEquals(testRes.getEnrichment(), 2, 0);
        assertEquals(testRes.getFunctional(), 3, 0);
        assertEquals(testRes.getHerbivore(), 4, 0);
        assertEquals(testRes.getBacterial(), 5, 0);
        assertEquals(testRes.getFungal(), 6, 0);
        assertEquals(testRes.getOmnivore(), 7, 0);
        assertEquals(testRes.getPredator(), 8, 0);
        assertEquals(testRes.getComposite(), 9, 0);
        assertEquals(testRes.getWeight(), 10, 0);
    }
}
