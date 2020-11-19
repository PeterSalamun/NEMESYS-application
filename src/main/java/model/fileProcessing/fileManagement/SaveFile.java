package model.fileProcessing.fileManagement;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.callsAndExceptions.otherCalls.FileSavedCall;
import model.fileProcessing.choosers.SaveChooser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile {

    private String path;
    private XSSFWorkbook book;

    //Constructor taking as parameter XSSFWorkbook
    public SaveFile(XSSFWorkbook book) throws IOException, FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        this.book = book;
        this.path = chooseFileToSave();
        savingFile();
    }

    //Constructor taking as parameters XSSFWorkbook and String
    public SaveFile(XSSFWorkbook book, String path) throws IOException {
        this.book = book;
        this.path = path;
        savingFile();
    }

    // EFFECTS: return String of file path where the file will be saved
    private String chooseFileToSave()  throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        SaveChooser file = new SaveChooser();
        return file.getFilePath();
    }

    //EFFECTS: saves XSSFWorkbook book to place described by String path
    private void savingFile() throws IOException {
        FileOutputStream saveFile = new FileOutputStream(path);
        book.write(saveFile);
        new FileSavedCall().getCall();

        saveFile.close();
    }
}
