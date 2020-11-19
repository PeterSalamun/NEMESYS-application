package model.fileProcessing.fileManagement;


import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.fileProcessing.choosers.OpenChooser;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BasicOpenFile {

    protected String path;
    protected XSSFSheet sheet;
    protected XSSFWorkbook xBook;

    //Constructor taking no parameters
    public BasicOpenFile() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        path = chooseFileToOpen();
    }

    //Constructor taking in String as a parameter
    public BasicOpenFile(String defaultPath) {
        path = defaultPath;
    }

    //EFFECTS: return first XSSFSheet from XSSFWorkBook xBook when user does not provide custom database. Used only for
    // opening default database. Throws IOException if the default database can not be opened.
    protected XSSFSheet openDefaultXSSFWorkbook() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        xBook = new XSSFWorkbook(inputStream);
        inputStream.close();

        return xBook.getSheetAt(0);
    }

    //EFFECTS: return first XSSFSheet from XSSFWorkBook xBook. Used for opening custom database or data file provided
    // by user
    protected XSSFSheet openXSSFWorkbook() throws IOException {
        InputStream inputStream = new FileInputStream(new File(path));
        xBook = new XSSFWorkbook(inputStream);
        inputStream.close();

        return xBook.getSheetAt(0);
    }

    //EFFECTS: return String representing choose file path provided by calling OpenChooser()
    protected String chooseFileToOpen() throws FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        OpenChooser file = new OpenChooser();
        return file.getFilePath();
    }

    //getters
    public String getPath() {
        return path;
    }

    public XSSFSheet getXSSFSheet() {
        return sheet;
    }

    public XSSFWorkbook getXBook() {
        return xBook;
    }
}
