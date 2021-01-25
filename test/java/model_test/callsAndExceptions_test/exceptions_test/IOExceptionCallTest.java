package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.IOExceptionCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IOExceptionCallTest {

    private IOExceptionCall testCall;

    @Before
    public void setUp() {
        testCall = new IOExceptionCall();
    }

    @Test
    public void testGetCall() {
        assertTrue(testCall.getCall());
    }
}
