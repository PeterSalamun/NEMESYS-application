package model.fileProcessing.fileManagment;

import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;

import java.io.IOException;

public class OpenFile extends BasicOpenFile {

    public OpenFile() throws OLE2NotOfficeXmlFileException, IOException, FileAlreadyExistsCall, NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall{
        super();
        initialize();
    }

    private void initialize() throws OLE2NotOfficeXmlFileException, IOException {
        sheet = openXSSFWorkbook();
    }
}
