package model.fileProcessing.fileManagment;


import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.choosers.OpenChooser;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BasicOpenFile {

    String path;
    XSSFSheet sheet;
    XSSFWorkbook xBook;

    public BasicOpenFile() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall {
        path = chooseFileToOpen();
    }

    public BasicOpenFile(String defaultPath) {
        path = defaultPath;
    }

    protected XSSFSheet openDefaultXSSFWorkbook() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        xBook = new XSSFWorkbook(inputStream);
        inputStream.close();

        return xBook.getSheetAt(0);
    }

    protected XSSFSheet openXSSFWorkbook() throws IOException {
        InputStream inputStream = new FileInputStream(new File(path));
        xBook = new XSSFWorkbook(inputStream);
        inputStream.close();

        return xBook.getSheetAt(0);
    }

    public String getPath() {
        return path;
    }

    protected String chooseFileToOpen() throws FileAlreadyExistsCall, NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        OpenChooser file = new OpenChooser();
        return file.getFilePath();
    }

    public XSSFSheet getXSSFSheet() {
        return sheet;
    }

    public XSSFWorkbook getxBook() {
        return xBook;
    }
}
