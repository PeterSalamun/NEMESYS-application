package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WrongExtensionExceptionCallTest {

   private WrongExtensionExceptionCall testCall;

    @Before
    public void setUp() {
        testCall = new WrongExtensionExceptionCall();
    }

    @Test
    public void testGetCall() {
        assertTrue(testCall.getCall());
    }

}
