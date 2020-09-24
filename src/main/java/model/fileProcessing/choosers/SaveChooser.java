package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveChooser extends BasicChooser {

    private static final String EXT = ".xlsx";
    private static final String TITLE = "Save results file";
    private static final String FILTERMESS = "Excel spreadsheet (*.xlsx)";


    public SaveChooser() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall {
        super(TITLE, FILTERMESS);
    }

    @Override
    protected void initChooser() throws NoFileChooseCall, FileAlreadyExistsCall {
        chooser = new JFileChooser();
        customizeChooser();
        chooseFilePath();
    }

    @Override
    protected void customizeChooser() {
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setDialogTitle(title);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(filterMess, EXT));
    }

    public void chooseFilePath() throws NoFileChooseCall, FileAlreadyExistsCall {

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath = getFullFilePath(chooser);

            if (fileAlreadyExists(filePath))
                throw new FileAlreadyExistsCall(filePath);

        } else {
            throw new NoFileChooseCall();
        }

    }

    private boolean fileAlreadyExists(String filePath) {
        File fileToSave = new File(filePath);

        return fileToSave.exists();
    }

    protected String getFullFilePath(JFileChooser file) {
        String path = file.getSelectedFile().getAbsolutePath();

        if (path.endsWith(".xlsx"))
            return path;

        return path += ".xlsx";
    }
}
