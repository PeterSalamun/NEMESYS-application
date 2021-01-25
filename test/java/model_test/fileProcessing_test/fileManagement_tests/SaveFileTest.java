package model_test.fileProcessing_test.fileManagement_tests;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.fileManagement.OpenFile;
import model.fileProcessing.fileManagement.SaveFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class SaveFileTest {

    private SaveFile testF;
    private String path;
    private XSSFWorkbook book;

    @Before
    public void setUp() throws FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, IOException {
        OpenFile file = new OpenFile();
        book = file.getXBook();
        path = "C:\\Users\\Peter\\Desktop\\test2.xlsx";
    }

    @Test
    public void testSaveFailWithoutPath() {
        try {
            testF = new SaveFile(book);
        } catch (IOException | WrongExtensionExceptionCall | FileNotFoundExceptionCall | NoFileChooseExceptionCall | FileAlreadyExistsExceptionCall e) {
            fail();
        }
    }

    @Test
    public void testSaveFailWithPath() {
        try {
            testF = new SaveFile(book, path);
        } catch (IOException e) {
            fail();
        }
    }
}
