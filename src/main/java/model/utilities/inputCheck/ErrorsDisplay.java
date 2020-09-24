package model.utilities.inputCheck;

import model.callsAndExceptions.exceptions.*;
import model.callsAndExceptions.otherCalls.SaveDataCall;
import model.fileProcessing.fileManagment.SaveFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

public class ErrorsDisplay extends JFrame {

    XSSFWorkbook errorBook;
    JFrame errorArea;
    JTable errorTable;
    JScrollPane scrollPane;
    ArrayList<Errors> errors;
    final String[] columnsNames = {"Row No.", "Taxon", "Column", "Error value", "Error type"};

    public ErrorsDisplay(ArrayList<Errors> errors) {
        this.errors = errors;
        initialize();
    }

    private void initialize() {
        errorBook = new XSSFWorkbook();
        errorBook.createSheet("Input Errors");
        BasicErrorTable errTab = new BasicErrorTable(errors, columnsNames);
        errorTable = errTab.getErrorTable();
        createFrame();
        addFrameListener();
        addTable();
    }

    private void createFrame() {
        errorArea = new JFrame();
        customizeFrame(errorArea);
    }

    private void customizeFrame(JFrame errorArea) {
        errorArea.setPreferredSize(new Dimension(600, 400));
        errorArea.setResizable(true);
        errorArea.setVisible(true);
        errorArea.setTitle("Input Errors");
    }

    private void addTable() {
        scrollPane = new JScrollPane(errorTable);
        scrollPane.createHorizontalScrollBar();
        scrollPane.createVerticalScrollBar();
        errorArea.add(scrollPane);
        errorArea.setLocationRelativeTo(null);

        errorArea.pack();
    }

    private void exitOptions() throws NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall, IOException {
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

    private void addFrameListener() {
        errorArea.addWindowListener(new errorAreaClickHandler());
    }

    private class errorAreaClickHandler implements WindowListener {

        @Override
        public void windowClosing(WindowEvent event) {
            try {
                exitOptions();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsCall e) {
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
