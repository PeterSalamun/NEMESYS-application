package model_test.callsAndExceptions_test.exceptions_test;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileAlreadyExistsExceptionCallTest {

    private FileAlreadyExistsExceptionCall testCall;
    private String path;

    @Before
    public void setUp() {
        path = "C:\\Users\\Peter\\Desktop\\APPresults.xlsx";
        testCall = new FileAlreadyExistsExceptionCall(path);
    }

    @Test
    public void testConstructor() {
        assertEquals(testCall.getPath(), "C:\\Users\\Peter\\Desktop\\APPresults.xlsx");
    }

    @Test
    public void testGetPath() {
        assertEquals(testCall.getPath(), "C:\\Users\\Peter\\Desktop\\APPresults.xlsx");
    }

    @Test
    public void testGetCall() {
        //if the method works
        assertEquals(testCall.getCall(), true);

        //for 'YES' option
        testCall.getCall();
        assertEquals(testCall.getResponse(), true);

        //for 'NO' option
        testCall.getCall();
        assertEquals(testCall.getResponse(), false);
    }
}
