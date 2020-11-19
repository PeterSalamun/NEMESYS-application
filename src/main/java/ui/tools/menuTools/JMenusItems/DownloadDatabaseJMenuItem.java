package ui.tools.menuTools.JMenusItems;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.fileManagement.OpenDatabase;
import model.fileProcessing.fileManagement.SaveFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DownloadDatabaseJMenuItem extends BasicJMenuItem {

    private final String DEFAULTDATABASEPATH = "/fullDatabase.xlsx";

    //Constructor
    public DownloadDatabaseJMenuItem(JMenu parent, String name) {
        super(parent, name);
    }

    //getters
    public String getDefaultDatabasePath() {
        return DEFAULTDATABASEPATH;
    }

    //MODIFIES: JMenuItem
    //EFFECTS: adds Listener to JMenuItem
    @Override
    protected void addListener() {
        menuItem.addActionListener(new DownloadDatabaseJMenuItemClickHandler());
    }

    private class DownloadDatabaseJMenuItemClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECTS: calls saveDefaultDatabase() method
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            saveDefaultDatabase();
        }

        //EFFECTS: saves actual default database from XSSFWorkbook to xlsx file to desired place in the PC. Could
        // displayed exceptions call depending on error which occurs
        private void saveDefaultDatabase() {
            OpenDatabase openDatabase;
            XSSFWorkbook database = null;
            try {
                openDatabase = new OpenDatabase(DEFAULTDATABASEPATH);
                database = openDatabase.getXBook();
                new SaveFile(database);

            } catch (NoFileChooseExceptionCall e) {
                e.getCall();
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsExceptionCall e) {
                e.getCall();
                try {
                    if (e.getResponse())
                        new SaveFile(database, e.getPath());
                } catch (IOException ioException) {
                    new IOExceptionCall().getCall();
                }
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            }
        }
    }
}
