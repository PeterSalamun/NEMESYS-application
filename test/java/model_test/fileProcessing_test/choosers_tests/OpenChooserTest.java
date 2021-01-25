package model_test.fileProcessing_test.choosers_tests;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.choosers.OpenChooser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OpenChooserTest {

    OpenChooser testC;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetPathWithoutException() throws FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        testC = new OpenChooser();
        assertTrue(testC.getFilePath() != null);
    }

    @Test
    public void testGetPathWithNoFileChooseCallException() {
        try {
            testC = new OpenChooser();
            fail();
        } catch (NoFileChooseExceptionCall noFileChooseExceptionCall) {
            assertTrue(noFileChooseExceptionCall.getCall());
        } catch (FileNotFoundExceptionCall fileNotFoundExceptionCall) {
            fail();
        } catch (WrongExtensionExceptionCall wrongExtensionExceptionCall) {
            fail();
        } catch (FileAlreadyExistsExceptionCall fileAlreadyExistsExceptionCall) {
            fail();
        }
    }

    @Test
    public void testGetPathWithFileNotFoundExceptionCall() {
        try {
            testC = new OpenChooser();
            fail();
        } catch (NoFileChooseExceptionCall noFileChooseExceptionCall) {
            fail();
        } catch (FileNotFoundExceptionCall fileNotFoundExceptionCall) {
            assertTrue(fileNotFoundExceptionCall.getCall());
        } catch (WrongExtensionExceptionCall wrongExtensionExceptionCall) {
            fail();
        } catch (FileAlreadyExistsExceptionCall fileAlreadyExistsExceptionCall) {
            fail();
        }
    }


    @Test
    public void testGetPathWithWrongExtensionException() {
        try {
            testC = new OpenChooser();
            fail();
        } catch (NoFileChooseExceptionCall noFileChooseExceptionCall) {
            fail();
        } catch (FileNotFoundExceptionCall fileNotFoundExceptionCall) {
            fail();
        } catch (WrongExtensionExceptionCall wrongExtensionExceptionCall) {
            assertTrue(wrongExtensionExceptionCall.getCall());
        } catch (FileAlreadyExistsExceptionCall fileAlreadyExistsExceptionCall) {
            fail();
        }
    }
}
