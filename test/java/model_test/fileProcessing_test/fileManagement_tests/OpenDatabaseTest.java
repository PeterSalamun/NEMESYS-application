package model_test.fileProcessing_test.fileManagement_tests;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.fileManagement.OpenDatabase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OpenDatabaseTest {

    private OpenDatabase testD;
    private String defaultPath;

    @Before
    public void setUp() {
        defaultPath = "/fullDatabase.xlsx";
    }

    @Test
    public void testGetCustomDatabaseMap() {
        try {
            testD = new OpenDatabase();
            assertFalse(testD.getDatabaseMap().isEmpty());
            assertNotNull(testD.getPath());
            assertNotNull(testD.getXSSFSheet());
            assertNotNull(testD.getXBook());
        } catch (FileNotFoundExceptionCall | FileAlreadyExistsExceptionCall | WrongExtensionExceptionCall | IOException |
                NoFileChooseExceptionCall fileNotFoundExceptionCall) {
            fail();
        }
    }

    @Test
    public void testGetDefaultDatabaseMap() {
        try {
            testD = new OpenDatabase(defaultPath);
            assertFalse(testD.getDatabaseMap().isEmpty());
            assertNotNull(testD.getPath());
            assertNotNull(testD.getXSSFSheet());
            assertNotNull(testD.getXBook());
        } catch (IOException e) {
            fail();
        }
    }

}
