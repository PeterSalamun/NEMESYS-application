package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileNotFoundExceptionCallTest {

    private FileNotFoundExceptionCall testCall;

    @Before
    public void setUp() {
        testCall = new FileNotFoundExceptionCall();
    }

    @Test
    public void testGetCall() {
        assertEquals(testCall.getCall(), true);
    }
}
