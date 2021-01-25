package model_test.callsAndExceptions_test.otherCalls_test;

import model.callsAndExceptions.otherCalls.SaveDataCall;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class SaveDataCallTest {

    private SaveDataCall testCall;
    private String mes;
    private String title;

    @Before
    public void setUp() {
        mes = "Do you want to save the data?";
        title = "Save file";
        testCall = new SaveDataCall();
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
        //test for 'YES' option return 0
        assertEquals(testCall.getCall(), JOptionPane.YES_OPTION);
        //test for 'NO' option return 1
        assertEquals(testCall.getCall(), JOptionPane.NO_OPTION);
        //test for 'CANCEL' option return 2
        assertEquals(testCall.getCall(), JOptionPane.CANCEL_OPTION);
    }


}
