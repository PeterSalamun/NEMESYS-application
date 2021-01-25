package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NoFileChooseExceptionCallTest {

    private NoFileChooseExceptionCall testCall;

    @Before
    public void setUp() {
        testCall = new NoFileChooseExceptionCall();
    }

    @Test
    public void testGetCall() {
        assertTrue(testCall.getCall());
    }
}
