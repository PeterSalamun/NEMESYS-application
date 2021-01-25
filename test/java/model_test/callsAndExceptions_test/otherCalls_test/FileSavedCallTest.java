package model_test.callsAndExceptions_test.otherCalls_test;

import model.callsAndExceptions.otherCalls.FileSavedCall;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;


public class FileSavedCallTest {

    private String mes;
    private String title;
    private FileSavedCall testCall;

    @Before
    public void setUp() {
        mes = "Your file have been saved.";
        title = "File saved";
        testCall = new FileSavedCall();
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
        assertEquals(testCall.getCall(), JOptionPane.OK_OPTION);
        //test for 'exit' option
        assertEquals(testCall.getCall(), JOptionPane.CLOSED_OPTION);
    }
}
