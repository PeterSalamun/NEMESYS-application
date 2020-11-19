package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.fileManagement.OpenDatabase;
import model.fileProcessing.fileManagement.OpenFile;
import model.utilities.inputCheck.DataInputCheck;
import model.utilities.inputCheck.DatabaseCheck;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.AppInterface;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class OpenSourceDataButton extends Button {

    private static final int GRIDX = 0;
    private static final int GRIDY = 2;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final String NAME = "Source Data";

    //Constructor
    public OpenSourceDataButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDWIDTH, GRIDHEIGHT, INSETS);
        switchOnButton();
    }

    //REQUIRES: JPanel
    //EFFECTS: creates and customize (see customizeButton() method) JButton
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }

    //MODIFIES: JButton
    //EFFECTS: customize JButton by setting its attributes
    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(new Dimension(110, 25));
    }

    //MODIFIES: JButton
    //EFFECTS: adds Listener to JButton
    @Override
    protected void addListener() {
        button.addActionListener(new OpenSourceDataButtonClickHandler());
    }

    private class OpenSourceDataButtonClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            openAndUploadDataSourceFile();
        }

        //EFFECTS: open and upload xlsx file provided by user, checks for any possible errors in the file and
        // databaseMap if necessary
        private void openAndUploadDataSourceFile() {

            DatabaseCheck databaseCheck;
            DataInputCheck inputCheck;
            calculateButtonOnOff(false);

            try {
                //Open xlsx file through OpenFile class
                app.setSampleSourceChecked(false);
                OpenFile file = new OpenFile();

                //displaying open xlsx file path in SourceDataPathPane
                String path = file.getPath();

                if (path != null) {
                    setPaneText(path);
                    app.setSourceFilePath(path);
                }

                //Upload default database through loadDefaultDatabase() method
                if (app.getDatabaseMap().isEmpty()) {
                    app.setDatabaseChecked(true);
                    loadDefaultDatabase();
                }

                XSSFSheet samplesSheet = file.getXSSFSheet();
                app.setSampleSheet(samplesSheet);
                app.setTaxaList(getTaxaList(samplesSheet));

                //checking database for any possible errors
                if (!app.isDatabaseChecked())
                    databaseCheck = new DatabaseCheck(app);

                //checking data provided by user for any possible errors
                inputCheck = new DataInputCheck(app);

                // activates Calculate button if all conditions are fulfilled
                if (app.isSamplesSourceChecked() && app.isDatabaseChecked())
                    calculateButtonOnOff(true);

                //throwing exceptions if necessary
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseExceptionCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (FileAlreadyExistsExceptionCall e) {
                e.getCall();
            } catch (IncompatibleTypesExceptionCall e) {
                e.getCall();
                e.displayingErrors();
            }
        }

        //REQUIRES: String
        //MODIFIES: SourceDataPathPane
        //Effects: sets uploaded xlsx data file path provided by user to SourceDataPathPane and displayed the path in
        // the main window of the app
        private void setPaneText(String path) {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof SourceDataPathPane)
                    pane.setFilePathString(path);
        }

        //EFFECTS: upload default database from within the application through OpenDatabase class if the database is
        // needed and user did not provide custom database
        private void loadDefaultDatabase() throws IOException, FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall,
                FileNotFoundExceptionCall, WrongExtensionExceptionCall, IncompatibleTypesExceptionCall {
            OpenDatabase database = new OpenDatabase(DEFAULTDATABASEPATH);
            app.setDatabaseMap(database.getDatabaseMap());
            app.setDatabaseSheet(database.getXSSFSheet());
        }

        //REQUIRES: boolean
        //MODIFIES: CalculateButton
        //EFFECTS: enabled/disabled CalculateButton within the application depending on actual circumstances
        private void calculateButtonOnOff(boolean value) {
            for (Button butt : app.getButtonsList())
                if (butt instanceof CalculateButton)
                    if (value)
                        butt.switchOnButton();
                    else
                        butt.switchOffButton();
        }

        //REQUIRES: XSSFSheet
        //MODIFIES: AppInterface
        //EFFECTS: extracts content of first column in the XSSFSheet and store the content in arrayList<String> in
        //AppInterface. The content should represent names of the organisms provided by the user.
        private ArrayList<String> getTaxaList(XSSFSheet sheet) {
            ArrayList<String> taxaList = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFCell cell = sheet.getRow(i).getCell(0);

                if ((cell == null || cell.getCellType().equals(CellType.BLANK)))
                    break;
                else
                    taxaList.add((cell.toString().trim()));
            }
            return taxaList;
        }
    }
}
