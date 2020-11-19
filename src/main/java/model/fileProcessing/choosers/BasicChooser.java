package model.fileProcessing.choosers;

import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;

import javax.swing.*;

public abstract class BasicChooser extends JFrame {

    protected JFileChooser chooser;
    protected String title;
    protected String filterMess;
    protected String filePath;

    //Constructor
    public BasicChooser(String title, String filterMess) throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall {
        this.title = title;
        this.filterMess = filterMess;
        initChooser();
    }

    //EFFECTS: abstract definition of a methods
    protected abstract void initChooser() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall;

    protected abstract void customizeChooser();

    //getters
    public String getFilePath() {return filePath;}

}
