package model_test.fileProcessing_test.fileManagement_tests;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.fileManagement.OpenFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OpenFileTest {

    OpenFile testF;

    @Before
    public void setUp(){}

    @Test
    public void testGetters() {
        try {
            testF = new OpenFile();
            assertNotNull(testF.getPath());
            assertNotNull(testF.getXBook());
            assertNotNull(testF.getXSSFSheet());
        } catch (IOException | FileAlreadyExistsExceptionCall | WrongExtensionExceptionCall | FileNotFoundExceptionCall | NoFileChooseExceptionCall e) {
            fail();
        }

    }
}
