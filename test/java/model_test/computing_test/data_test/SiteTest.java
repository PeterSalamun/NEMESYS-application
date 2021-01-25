package model_test.computing_test.data_test;

import model.computing.data.Sample;
import model.computing.data.Site;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SiteTest {

    private Sample c1, c2, c3;
    private ArrayList<Sample> testList;
    private String siteName;
    private Site testSite;

    @Before
    public void setUp() {
        c1 = new Sample("Rhabditis", 10, "Les", 1);
        c2 = new Sample("Tulipan", 5, "Zahrada", 2);
        c3 = new Sample("Bubo bubo", 1, "Luka", 3);
        testList = new ArrayList<>();
        testList.add(c1);
        testList.add(c2);
        testList.add(c3);

        siteName = "Mesto";

        testSite = new Site(siteName, testList);
    }

    @Test
    public void testGetTaxaList() {
        assertEquals(testSite.getTaxaList().size(), 3);
        assertEquals(testSite.getTaxaList().get(0), c1);
    }

    @Test
    public void testGetSiteName() {
        assertEquals(testSite.getSiteName(), "Mesto");
    }

}
