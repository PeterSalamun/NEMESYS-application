package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.callsAndExceptions.otherCalls.LoadDefaultDatabase;
import model.fileProcessing.fileManagment.OpenDatabase;
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

    public OpenDatabaseButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDWIDTH, GRIDHEIGHT, INSETS);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }


    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(new Dimension(110, 25));
        switchOff();
    }

    @Override
    protected void addListener() {
        button.addActionListener(new OpenDatabaseButtonClickHandler());
    }

    public void switchOff() {
        deactivate();
        button.setEnabled(isActive());
    }

    public void switchOn() {
        activate();
        button.setEnabled(isActive());
    }


    private class OpenDatabaseButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            DataInputCheck dataInputCheck;
            OpenDatabase database;

            try {
                app.setDatabaseChecked(false);
                if (!getPaneText().equals("Default database")) {
                    LoadDefaultDatabase defaultDatabase = new LoadDefaultDatabase();

                    if (defaultDatabase.getCall() == JOptionPane.YES_OPTION) {
                        database = new OpenDatabase(DEFAULTDATABASEPATH);
                        setPaneText("Default database");

                    } else {
                        database = new OpenDatabase();
                        setPaneText(database.getPath());
                    }

                } else {
                    database = new OpenDatabase();
                    setPaneText(database.getPath());
                }

                app.setDatabaseSheet(database.getXSSFSheet());
                DatabaseCheck databaseControl = new DatabaseCheck(app);
                app.setDatabaseMap(database.getDatabaseMap());

                if (!app.isSamplesSourceChecked() && app.getSampleSheet() != null)
                    dataInputCheck = new DataInputCheck(app);

            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsCall e) {
                e.getCall();
            } catch (NoFileChooseCall e) {
                e.getCall();
            } catch (IncompatibleTypesExceptionCall e) {
                e.getCall();
                e.displayingErrors();
            }
        }

        private void setPaneText(String path) {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof DatabasePathPane)
                    pane.setFilePathString(path);
        }

        private String getPaneText() {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof DatabasePathPane)
                    return pane.getFilePathString().trim();

            return null;
        }
    }
}
