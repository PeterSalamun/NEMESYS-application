package model_test.computing_test.data_test;

import model.computing.data.NematodesDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NematodesDatabaseTest {

    private NematodesDatabase testData1;
    private NematodesDatabase testData2;
    private NematodesDatabase testData3;
    private String n1, n2, n3;
    private String g1, g2, g3;
    private Double w1, w2, w3;

    @Before
    public void setUp() {
        n1 = "Jelen";
        n2 = "Komar";
        n3 = "Hroch";

        g1 = "B1";
        g2 = "O5";
        g3 = "F2";

        w1 = 0.564;
        w2 = 100.0;
        w3 = 5.846;

        testData1 = new NematodesDatabase(n1, g1, w1);
        testData2 = new NematodesDatabase(n2, g2, w2);
        testData3 = new NematodesDatabase(n3, g3, w3);
    }

    @Test
    public void testGetFoodShort() {
        assertEquals(testData1.getFoodShort(), "B");
        assertEquals(testData2.getFoodShort(), "O");
        assertEquals(testData3.getFoodShort(), "F");
    }

    @Test
    public void testGetWeight() {
        assertEquals(testData1.getWeight(), 0.564, 0.0001);
        assertEquals(testData2.getWeight(), 100.0, 0.0001);
        assertEquals(testData3.getWeight(), 5.846, 0.0001);
    }

    @Test
    public void testGetCP() {
        assertEquals(testData1.getCp(), 1, 0);
        assertEquals(testData2.getCp(), 5, 0);
        assertEquals(testData3.getCp(), 2, 0);
    }

    @Test
    public void testGetGuild() {
        assertEquals(testData1.getGuild(), "B1");
        assertEquals(testData2.getGuild(), "O5");
        assertEquals(testData3.getGuild(), "F2");
    }

}
