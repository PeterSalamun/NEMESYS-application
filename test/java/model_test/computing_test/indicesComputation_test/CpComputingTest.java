package model_test.computing_test.indicesComputation_test;

import model.computing.data.NematodesDatabase;
import model.computing.data.Sample;
import model.computing.data.Site;
import model.computing.indicesComputation.CpComputing;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CpComputingTest {

    private AppInterface testApp;
    private CpComputing testCP;
    private HashMap<String, NematodesDatabase> testMap;
    private NematodesDatabase d1, d2, d3;

    private Sample sam1, sam2, sam3, sam4, sam5, sam6;
    private String sN1, sN2;
    private Site s1, s2;

    private ArrayList<Sample> testSamples1, testSamples2;
    private ArrayList<Site> testSites;


    @Before
    public void setUp() {
        d1 = new NematodesDatabase("Rhabditis", "B1", 0.501);
        d2 = new NematodesDatabase("Acrobeloides", "B2", 0.978);
        d3 = new NematodesDatabase("Dorylaimus", "O5", 0.129);

        testMap = new HashMap<>();
        testMap.put(d1.getTaxaName(), d1);
        testMap.put(d2.getTaxaName(), d2);
        testMap.put(d3.getTaxaName(), d3);

        testApp = new AppInterface();
        testApp.setDatabaseMap(testMap);

        testCP = new CpComputing(testApp);

        sN1 = "Les";
        sN2 = "Luka";

        sam1 = new Sample("Rhabditis", 10, sN1, 1);
        sam2 = new Sample("Acrobeloides", 5, sN1, 2);
        sam3 = new Sample("Dorylaimus", 8, sN1, 3);

        sam4 = new Sample("Rhabditis", 17, sN2, 1);
        sam5 = new Sample("Acrobeloides", 31, sN2, 2);
        sam6 = new Sample("Dorylaimus", 12, sN2, 3);

        testSamples1 = new ArrayList<>();
        testSamples2 = new ArrayList<>();
        testSites = new ArrayList<>();

        testSamples1.add(sam1);
        testSamples1.add(sam2);
        testSamples1.add(sam3);
        s1 = new Site(sN1, testSamples1);


        testSamples2.add(sam4);
        testSamples2.add(sam5);
        testSamples2.add(sam6);
        s2 = new Site(sN2, testSamples2);

    }

    @Test
    public void testComputeAllSitesResults() {

        assertEquals(testCP.computeAllSitesResults(testSites).size(), 0);

        testSites.add(s1);
        assertEquals(testCP.computeAllSitesResults(testSites).size(), 1);

        testSites.add(s2);
        assertEquals(testCP.computeAllSitesResults(testSites).size(), 2);
    }
}
