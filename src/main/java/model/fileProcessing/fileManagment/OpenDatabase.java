package model.fileProcessing.fileManagment;


import model.callsAndExceptions.exceptions.FileAlreadyExistsCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.computing.data.NematodesDatabase;
import model.fileProcessing.XSSFprocessing.LoadDatabase;

import java.io.IOException;
import java.util.HashMap;

public class OpenDatabase extends BasicOpenFile {

    private HashMap<String, NematodesDatabase> database;
    LoadDatabase loadDatabase;

    public OpenDatabase() throws IOException, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsCall, NoFileChooseCall {
        super();
        initialize();
        openCustomeDatabase();
    }

    public OpenDatabase(String defaultPath) throws IOException {
        super(defaultPath);
        initialize();
        openDefaultDatabase();
    }

    private void initialize() {
        database = new HashMap<>();
    }

    private void openDefaultDatabase() throws IOException {
        sheet = openDefaultXSSFWorkbook();
        loadDatabase = new LoadDatabase(sheet);
        database = loadDatabase.getDatabaseMap();
    }

    private void openCustomeDatabase() throws IOException {
        sheet = openXSSFWorkbook();
        loadDatabase = new LoadDatabase(sheet);
        database = loadDatabase.getDatabaseMap();
    }

    public HashMap<String, NematodesDatabase> getDatabaseMap() {
        return database;
    }

}
