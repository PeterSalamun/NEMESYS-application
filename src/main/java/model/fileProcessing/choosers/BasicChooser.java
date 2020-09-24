package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;

public abstract class BasicChooser extends JFrame {

    JFileChooser chooser;
    String title;
    String filterMess;
    String filePath;

    public BasicChooser(String title, String filterMess) throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall {
        this.title = title;
        this.filterMess = filterMess;
        initChooser();
    }

    protected abstract void initChooser() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall;

    protected abstract void customizeChooser();

    public String getFilePath() {return filePath;}

    protected String getFullFilePath(JFileChooser file) {
        return null;
    }
}
