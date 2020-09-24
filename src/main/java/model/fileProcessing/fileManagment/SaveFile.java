package model.fileProcessing.fileManagment;

import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.callsAndExceptions.otherCalls.FileSavedCall;
import model.fileProcessing.choosers.SaveChooser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile {

    String path;
    XSSFWorkbook book;

    public SaveFile(XSSFWorkbook book) throws IOException, FileAlreadyExistsCall, NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        this.book = book;
        this.path = chooseFileToSave();
        savingFile();
    }

    public SaveFile(XSSFWorkbook book, String path) throws IOException {
        this.book = book;
        this.path = path;
        savingFile();
    }

    private String chooseFileToSave()  throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall{
        SaveChooser file = new SaveChooser();
        return file.getFilePath();
    }

    private void savingFile() throws IOException {

        FileOutputStream saveFile = new FileOutputStream(path);
        book.write(saveFile);
        new FileSavedCall().getCall();

        saveFile.close();
    }
}
