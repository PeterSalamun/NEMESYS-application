package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.XSSFprocessing.SamplesProcessing;
import model.fileProcessing.fileManagement.OpenDatabase;
import model.fileProcessing.fileManagement.SaveFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.AppInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CalculateButton extends Button {

    private static final int GRIDX = 2;
    private static final int GRIDY = 7;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 10};
    private static final String NAME = "Calculate";
    private XSSFWorkbook resultBook;


    //Constructor
    public CalculateButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    //REQUIRES: JPanel
    //EFFECTS: creates and customize (see customizeButton()) JButton
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }


    //MODIFIES: JButton
    //EFFECTS: customize JButton by setting its attributes
    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(BUTTONDIMENSION);
        button.setEnabled(isActive());
    }

    //MODIFIES: JButton
    //EFFECTS: adds Listener to JButton
    @Override
    protected void addListener() {
        button.addActionListener(new CalculateButtonClickHandler());
    }

    private class CalculateButtonClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            performCalculation();
        }

        //EFFECTS: Uploading default database if needed, starts the process of computing results through
        // SampleProcessing() class, and save results through SaveFile() class.
        // If necessary calls appropriate exceptions
        private void performCalculation() {
            try {
                //Uploading default database through loadDefaultDatabase() method if getDatabaseMap() return empty
                // HashMap<String, NematodesDatabase> and isDiversityOn() returns false
                if (app.getDatabaseMap().isEmpty() && !app.isDiversityOn())
                    loadDefaultDatabase();

                //callse SamplePracessing class to obtain results for users data
                SamplesProcessing sapro = new SamplesProcessing(app);
                resultBook = sapro.getResultBook();
                //save results from XSSFWorkbook to xlsx file through SaveFile class
                new SaveFile(resultBook);


            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsExceptionCall e) {
                e.getCall();
                try {
                    if (e.getResponse())
                        new SaveFile(resultBook, e.getPath());
                } catch (IOException ioException) {
                    new IOExceptionCall().getCall();
                }
            } catch (IncompatibleTypesExceptionCall e) {
                e.getCall();
                e.displayingErrors();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            }


        }

        //MODIFIES:AppInterface
        //EFFECTS: read xlsx file of default database through OpenDatabase class, conversed it to HashMap and XSSFSheet
        // and set it in AppInterface
        private void loadDefaultDatabase() throws IOException, FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, IncompatibleTypesExceptionCall {
            OpenDatabase database = new OpenDatabase(DEFAULTDATABASEPATH);
            app.setDatabaseMap(database.getDatabaseMap());
            app.setDatabaseSheet(database.getXSSFSheet());
        }
    }
}