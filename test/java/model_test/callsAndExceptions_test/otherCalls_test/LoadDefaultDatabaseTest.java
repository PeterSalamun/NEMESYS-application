package model_test.callsAndExceptions_test.otherCalls_test;

import model.callsAndExceptions.otherCalls.LoadDefaultDatabase;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class LoadDefaultDatabaseTest {

    private String mes;
    private String title;
    private LoadDefaultDatabase testCall;

    @Before
    public void setUp() {
        mes = "Do you want to load default database?";
        title = "Load Default database";
        testCall = new LoadDefaultDatabase();
    }

    @Test
    public void testGetMessage() {
        assertEquals(testCall.getMessage(), mes);
    }

    @Test
    public void testGetTitle() {
        assertEquals(testCall.getTitle(), title);
    }

    @Test
    public void testGetCall() {
        //test for 'YES' option
        assertEquals(testCall.getCall(), JOptionPane.YES_OPTION);
        //test for 'NO' option
        assertEquals(testCall.getCall(), JOptionPane.NO_OPTION);
        //test for 'exit' option
        assertEquals(testCall.getCall(), JOptionPane.CLOSED_OPTION);
    }
}
