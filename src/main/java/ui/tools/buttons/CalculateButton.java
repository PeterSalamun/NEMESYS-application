package ui.tools.buttons;

import model.callsAndExceptions.exceptions.*;
import model.fileProcessing.XSSFprocessing.SamplesProcessing;
import model.fileProcessing.fileManagment.OpenDatabase;
import model.fileProcessing.fileManagment.SaveFile;
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


    public CalculateButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }

    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(BUTTONDIMENSION);
        button.setEnabled(isActive());
    }


    @Override
    protected void addListener() {
        button.addActionListener(new CalculateButtonClickHandler());
    }

    private class CalculateButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            try {

                if (app.getDatabaseMap().isEmpty() && !app.isDiversityOn())
                    loadDefaultDatabase();

                SamplesProcessing sapro = new SamplesProcessing(app);
                resultBook = sapro.getResultBook();

                new SaveFile(resultBook);


            } catch (IOException e) {
                new IOExceptionCall().getCall();
            } catch (FileNotFoundExceptionCall e) {
                e.getCall();
            } catch (WrongExtensionExceptionCall e) {
                e.getCall();
            } catch (NoFileChooseCall e) {
                e.getCall();
            } catch (FileAlreadyExistsCall e) {
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
            }


        }

        private void loadDefaultDatabase() throws IOException, FileAlreadyExistsCall, NoFileChooseCall, FileNotFoundExceptionCall, WrongExtensionExceptionCall, IncompatibleTypesExceptionCall {
            OpenDatabase database = new OpenDatabase(DEFAULTDATABASEPATH);
            app.setDatabaseMap(database.getDatabaseMap());
            app.setDatabaseSheet(database.getXSSFSheet());
        }
    }
}