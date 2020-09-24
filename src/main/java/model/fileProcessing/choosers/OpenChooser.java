package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenChooser extends BasicChooser {

    private static final String EXT1 = "xlsx";
    private static final String TITLE = "Open file";
    private static final String FILTERMESS = "Excel spreadsheet (*.xlsx)";

    public OpenChooser() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall {
        super(TITLE, FILTERMESS);
    }

    @Override
    protected void initChooser() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {
        chooser = new JFileChooser();
        customizeChooser();
        chooseFilePath();
    }

    @Override
    protected void customizeChooser() {
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setDialogTitle(title);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(FILTERMESS, EXT1));
    }

    protected void chooseFilePath() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall {

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();

            if (!chooser.getSelectedFile().exists())
                throw new FileNotFoundExceptionCall();
            if (!filePath.contains(".xlsx"))
                throw new WrongExtensionExceptionCall();

        } else {
            throw new NoFileChooseCall();
        }
    }
}
