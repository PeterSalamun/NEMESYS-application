package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.callsAndExceptions.otherCalls.LoadDefaultDatabase;
import model.fileProcessing.fileManagement.OpenDatabase;
import model.utilities.inputCheck.DataInputCheck;
import model.utilities.inputCheck.DatabaseCheck;
import ui.AppInterface;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.DatabasePathPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenDatabaseButton extends Button {

    private static final int GRIDX = 0;
    private static final int GRIDY = 1;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final String NAME = "Database";

    //Constructor
    public OpenDatabaseButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDWIDTH, GRIDHEIGHT, INSETS);
        switchOffButton();
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
        button.addActionListener(new OpenDatabaseButtonClickHandler());
    }

    private class OpenDatabaseButtonClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            uploadDatabase();
        }


        //EFFECTS: upload, open xlsx file (database file) and check the data for any possible errors
        private void uploadDatabase() {

            DataInputCheck dataInputCheck;
            OpenDatabase database;

            try {
                app.setDatabaseChecked(false);

                //if text in databasePathPane is not Default database, the application offers user options to upload
                // either default database or custom database
                if (!getPaneText().equals("Default database")) {
                    LoadDefaultDatabase defaultDatabase = new LoadDefaultDatabase();

                    //upload of default database
                    if (defaultDatabase.getCall() == JOptionPane.YES_OPTION) {
                        database = new OpenDatabase(DEFAULTDATABASEPATH);
                        setPaneText("Default database");

                        //upload of custom database
                    } else {
                        database = new OpenDatabase();
                        setPaneText(database.getPath());
                    }

                    //upload of custom database
                } else {
                    database = new OpenDatabase();
                    setPaneText(database.getPath());
                }

                app.setDatabaseSheet(database.getXSSFSheet());

                //checking database data for errors
                DatabaseCheck databaseControl = new DatabaseCheck(app);
                app.setDatabaseMap(database.getDatabaseMap());

                //if data provided by user were not checked by now and are uploaded the application check the data for
                // errors
                if (!app.isSamplesSourceChecked() && app.getSampleSheet() != null)
                    dataInputCheck = new DataInputCheck(app);

                //throwing different exceptions depending on the actual circumstances
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseExceptionCall e) {
                e.getCall();
            } catch (IncompatibleTypesExceptionCall e) {
                e.getCall();
                e.displayingErrors();
            }
        }

        //REQUIRES: String
        //MODIFIES: DatabasePathPane
        //EFFECTS: displaying String path in DatabasePathPane
        private void setPaneText(String path) {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof DatabasePathPane)
                    pane.setFilePathString(path);
        }

        //EFFECTS: returning String containing actual text displayed by DatabasePathPane
        private String getPaneText() {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof DatabasePathPane)
                    return pane.getFilePathString().trim();

            return null;
        }
    }
}
