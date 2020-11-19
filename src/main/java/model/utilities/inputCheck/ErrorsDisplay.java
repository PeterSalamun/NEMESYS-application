package model.utilities.inputCheck;

import model.callsAndExceptions.exceptions.*;
import model.callsAndExceptions.otherCalls.SaveDataCall;
import model.fileProcessing.fileManagement.SaveFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

public class ErrorsDisplay extends JFrame {

    private XSSFWorkbook errorBook;
    private JFrame errorArea;
    private JTable errorTable;
    private final ArrayList<Errors> errors;
    final String[] columnsNames = {"Row No.", "Taxon", "Column", "Error value", "Error type"};

    //Constructor takes ArrayList<Errors> as parameter
    public ErrorsDisplay(ArrayList<Errors> errors) {
        this.errors = errors;
        initialize();
    }

    //EFFECTS: initialize fields
    private void initialize() {
        errorBook = new XSSFWorkbook();
        errorBook.createSheet("Input Errors");
        errorArea = new JFrame();
        errorTable = new BasicErrorTable(errors, columnsNames).getErrorTable();
        customizeFrame(errorArea);
        addFrameListener();
        addTable();
    }

    //REQUIRES: JFrame
    //MODIFIES: JFrame
    //EFFECTS: customize the JFrame
    private void customizeFrame(JFrame errorArea) {
        errorArea.setPreferredSize(new Dimension(600, 400));
        errorArea.setResizable(true);
        errorArea.setVisible(true);
        errorArea.setTitle("Input Errors");
    }

    //MODIFIES: JFrame, JScrollPane
    //EFFECTS: adding JTable to scrollPane and then adding the scrollPane to JFrame
    private void addTable() {
        JScrollPane scrollPane = new JScrollPane(errorTable);
        scrollPane.createHorizontalScrollBar();
        scrollPane.createVerticalScrollBar();
        errorArea.add(scrollPane);
        errorArea.setLocationRelativeTo(null);

        errorArea.pack();
    }

    //EFFECTS: depending on the int respond when closing the JFrame errorArea the content of the table is or is not
    // saved
    private void exitOptions() throws NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall, IOException {
        int respond = new SaveDataCall().getCall();

        if (respond == JOptionPane.YES_OPTION) {
            new ErrorSheetCreator(errorBook, errors);
            new SaveFile(errorBook);
            errorArea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        } else if (respond == JOptionPane.NO_OPTION) {
            errorArea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else if (respond == JOptionPane.CANCEL_OPTION) {
            errorArea.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }

    //MODIFIES: JFrame
    //EFFECTS: adding listener to the errorArea
    private void addFrameListener() {
        errorArea.addWindowListener(new errorAreaClickHandler());
    }

    private class errorAreaClickHandler implements WindowListener {

        //EFFECTS: dealing with exceptions thrown by JFrame when closing
        @Override
        public void windowClosing(WindowEvent event) {
            try {
                exitOptions();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseExceptionCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsExceptionCall e) {
                e.getCall();
                e.savingErrors(errorBook, errors);
                try {
                    if(e.getResponse())
                        new SaveFile(errorBook, e.getPath());
                } catch (IOException ioException) {
                    new IOExceptionCall().getCall();
                }
            }
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
