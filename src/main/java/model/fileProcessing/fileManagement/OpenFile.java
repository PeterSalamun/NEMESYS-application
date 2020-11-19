package model.fileProcessing.fileManagement;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;

import java.io.IOException;

public class OpenFile extends BasicOpenFile {

    //Constructor
    public OpenFile() throws OLE2NotOfficeXmlFileException, IOException, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        super();
        initialize();
    }

    //EFFECTS: initialize XSSFSheet sheet by calling openXSSFWorkbook()
    private void initialize() throws OLE2NotOfficeXmlFileException, IOException {
        sheet = openXSSFWorkbook();
    }
}
