package model_test.computing_test.data_test;

import model.computing.data.Creatures;
import model.computing.data.Sample;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleTest {

    private Sample testSample;
    private String taxa;
    private double abundance;
    private String site;
    private int row;

    @Before
    public void setUp() {
        taxa = "Rhabditis";
        abundance = 100;
        site = "Les";
        row = 5;
        testSample = new Sample(taxa, abundance, site, row);
    }

    @Test
    public void testGetTaxaName() {
        assertEquals(testSample.getTaxaName(), taxa);
    }

    @Test
    public void testGetAbundance() {
        assertEquals(testSample.getAbundance(), abundance, 0.001);
    }

    @Test
    public void testGetSiteName() {
        assertEquals(testSample.getSiteName(), site);
    }

    @Test
    public void testGetRow() {
        assertEquals(testSample.getRow(), row);
    }

    @Test
    public void testSetRow() {
        int row;

        row = 1;
        testSample.setRow(row);
        assertEquals(testSample.getRow(), row);

        row = 0;
        testSample.setRow(row);
        assertEquals(testSample.getRow(), row);

        row = -1;
        testSample.setRow(row);
        assertEquals(testSample.getRow(), row);
    }

    @Test
    public void testSetSiteName() {
        String site;

        site = "site";
        testSample.setSiteName(site);
        assertEquals(testSample.getSiteName(), site);

        site = "54546";
        testSample.setSiteName(site);
        assertEquals(testSample.getSiteName(), site);
    }
}
