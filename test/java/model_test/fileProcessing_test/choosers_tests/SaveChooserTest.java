package model_test.fileProcessing_test.choosers_tests;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.choosers.SaveChooser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SaveChooserTest {

    SaveChooser testC;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetPathWithoutException() throws FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        testC = new SaveChooser();
        testC.chooseFilePath();
        assertTrue(testC.getFilePath() != null);
    }

    @Test
    public void testGetPathWithFileAlreadyExistsException() {
        try {
            testC = new SaveChooser();
            fail();
        } catch (NoFileChooseExceptionCall noFileChooseExceptionCall) {
            fail();
        } catch (FileNotFoundExceptionCall fileNotFoundExceptionCall) {
            fail();
        } catch (WrongExtensionExceptionCall wrongExtensionExceptionCall) {
            fail();
        } catch (FileAlreadyExistsExceptionCall fileAlreadyExistsExceptionCall) {
            assertTrue(fileAlreadyExistsExceptionCall.getCall());
        }
    }

    @Test
    public void testGetPathWithNoFileChooseException() {
        try {
            testC = new SaveChooser();
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
}
