package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveChooser extends BasicChooser {

    private static final String EXT = ".xlsx";
    private static final String TITLE = "Save file";
    private static final String FILTERMESS = "Excel spreadsheet (*.xlsx)";

    //Constructor
    public SaveChooser() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        super(TITLE, FILTERMESS);
    }

    //MODIFIES: this
    //EFFECTS: initialize JFileChooser() chooser and calls methods customizeChooser() and chooseFilePath()
    @Override
    protected void initChooser() throws NoFileChooseExceptionCall, FileAlreadyExistsExceptionCall {
        chooser = new JFileChooser();
        customizeChooser();
        chooseFilePath();
    }

    //MODIFIES: JFileChooser
    //EFFECTS: customizing JFileChosser chooser
    @Override
    protected void customizeChooser() {
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setDialogTitle(title);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(filterMess, EXT));
    }

    //EFFECTS: get file path in form of String of choose file through JFileChooser and store it in filePath field.
    // If the file does already exists it throws FileAlreadyExistsExceptionCall,
    // if no file will be choose through JFileChooser() it throws NoFileChooseExceptionCall
    public void chooseFilePath() throws NoFileChooseExceptionCall, FileAlreadyExistsExceptionCall {

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath = getFullFilePath(chooser);

            if (fileAlreadyExists(filePath))
                throw new FileAlreadyExistsExceptionCall(filePath);

        } else {
            throw new NoFileChooseExceptionCall();
        }

    }

    //REQUIRES: String
    //EFFECTS: return boolean of if file with String filePath already exists or not
    private boolean fileAlreadyExists(String filePath) {
        File fileToSave = new File(filePath);

        return fileToSave.exists();
    }

    //REQUIRES: JFileChooser
    //EFFECTS: return full path of choose file including filename extension *.xlsx
    protected String getFullFilePath(JFileChooser file) {
        String path = file.getSelectedFile().getAbsolutePath();

        if (path.endsWith(".xlsx"))
            return path;

        return path += ".xlsx";
    }
}
