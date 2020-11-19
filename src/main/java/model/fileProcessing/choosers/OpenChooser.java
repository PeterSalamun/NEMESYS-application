package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenChooser extends BasicChooser {

    private static final String EXT1 = "xlsx";
    private static final String TITLE = "Open file";
    private static final String FILTERMESS = "Excel spreadsheet (*.xlsx)";

    //Constuructor
    public OpenChooser() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        super(TITLE, FILTERMESS);
    }

    //MODIFIES: this
    //EFFECTS: initialize JFileChooser() chooser and calls methods customizeChooser() and chooseFilePath()
    @Override
    protected void initChooser() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        chooser = new JFileChooser();
        customizeChooser();
        chooseFilePath();
    }

    //MODIFIES: JFileChooser
    //EFFECTS: customizing JFileChosser chooser
    @Override
    protected void customizeChooser() {
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle(title);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(FILTERMESS, EXT1));
    }

    //EFFECTS: get file path in form of String of choose file through JFileChooser and store it in filePath field.
    // If the file does not exists it throws FileNotFoundExceptionCall,
    // if the file is of wrong type it throws WrongExtensionExceptionCall,
    // if no file will be choose through JFileChooser() it throws NoFileChooseExceptionCall
    protected void chooseFilePath() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();

            if (!chooser.getSelectedFile().exists())
                throw new FileNotFoundExceptionCall();
            if (!filePath.contains(".xlsx"))
                throw new WrongExtensionExceptionCall();

        } else {
            throw new NoFileChooseExceptionCall();
        }
    }
}
