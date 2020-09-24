package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.fileManagment.OpenDatabase;
import model.fileProcessing.fileManagment.OpenFile;
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

    public OpenSourceDataButton(AppInterface appInterface, JComponent parent) {
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
    }

    @Override
    protected void addListener() {
        button.addActionListener(new OpenSourceDataButtonClickHandler());
    }

    private class OpenSourceDataButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            DatabaseCheck databaseCheck;
            DataInputCheck inputCheck;
            long start = System.currentTimeMillis();
            try {
                app.setSampleSourceChecked(false);
                OpenFile file = new OpenFile();

                String path = file.getPath();
                if (path != null) {
                    setPaneText(path);
                    app.setSourceFilePath(path);
                }

                if (app.getDatabaseMap().isEmpty())
                    loadDefaultDatabse();

                XSSFSheet samplesSheet = file.getXSSFSheet();
                app.setSampleSheet(samplesSheet);
                app.setTaxaList(getTaxaList(samplesSheet));

                if (!app.isDatabaseChecked())
                    databaseCheck = new DatabaseCheck(app);

                inputCheck = new DataInputCheck(app);

                if (app.isSamplesSourceChecked() && app.isDatabaseChecked())
                    calculateButtonOn();

                long end = System.currentTimeMillis();
                System.out.println(end - start);
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (FileAlreadyExistsCall e) {
                e.getCall();
            } catch (IncompatibleTypesExceptionCall e) {
                e.getCall();
                e.displayingErrors();
            }
        }

        private void setPaneText(String path) {
            for (BasicPane pane : app.getPanesList())
                if (pane instanceof SourceDataPathPane)
                    pane.setFilePathString(path);
        }

        private void loadDefaultDatabse() throws IOException, FileAlreadyExistsCall, NoFileChooseCall,
                FileNotFoundExceptionCall, WrongExtensionExceptionCall, IncompatibleTypesExceptionCall {
            OpenDatabase database = new OpenDatabase(DEFAULTDATABASEPATH);
            app.setDatabaseMap(database.getDatabaseMap());
            app.setDatabaseSheet(database.getXSSFSheet());
        }

        private void calculateButtonOn() {
            for (Button butt : app.getButtonsList())
                if (butt instanceof CalculateButton)
                    butt.switchOnButton();
        }

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
