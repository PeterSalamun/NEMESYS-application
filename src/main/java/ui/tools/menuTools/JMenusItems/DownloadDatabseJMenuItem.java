package ui.tools.menuTools.JMenusItems;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.fileManagment.OpenDatabase;
import model.fileProcessing.fileManagment.SaveFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class DownloadDatabseJMenuItem extends BasicJMenuItem {


    public DownloadDatabseJMenuItem(JMenu parent, String name) {
        super(parent, name);
    }

    @Override
    protected void addListener() {
        menuItem.addActionListener(new DownloadDatabseJMenuItemClickHandler());
    }

    private class DownloadDatabseJMenuItemClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            OpenDatabase openDatabase;
            XSSFWorkbook database = null;
            try {
                openDatabase = new OpenDatabase(DEFAULTDATABASEPATH);
                database = openDatabase.getxBook();
                new SaveFile(database);

            } catch (NoFileChooseCall e) {
                e.getCall();
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (FileAlreadyExistsCall e) {
                e.getCall();
                try {
                    if (e.getResponse())
                        new SaveFile(database, e.getPath());
                } catch (IOException ioException) {
                    new IOExceptionCall().getCall();
                }
            }


        }
    }
}
