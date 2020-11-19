package model.fileProcessing.fileManagement;


import model.callsAndExceptions.exceptions.FileAlreadyExistsExceptionCall;
import model.callsAndExceptions.exceptions.FileNotFoundExceptionCall;
import model.callsAndExceptions.exceptions.NoFileChooseExceptionCall;
import model.callsAndExceptions.exceptions.WrongExtensionExceptionCall;
import model.computing.data.NematodesDatabase;
import model.fileProcessing.XSSFprocessing.LoadDatabase;

import java.io.IOException;
import java.util.HashMap;

public class OpenDatabase extends BasicOpenFile {

    private HashMap<String, NematodesDatabase> database;
    private LoadDatabase loadDatabase;

    //Constructor taking no parameter
    public OpenDatabase() throws IOException, FileNotFoundExceptionCall, WrongExtensionExceptionCall, FileAlreadyExistsExceptionCall, NoFileChooseExceptionCall {
        super();
        initialize();
        openCustomDatabase();
    }

    //Constructor taking String as parameter
    public OpenDatabase(String defaultPath) throws IOException {
        super(defaultPath);
        initialize();
        openDefaultDatabase();
    }

    //EFFECTS: initialize HashMap<String, NematodesDatabase> database
    private void initialize() {
        database = new HashMap<>();
    }

    //MODIFIES: HashMap<String, NematodesDatabase> database
    //EFFECTS: open default database file and convert its content into the HashMap<>() for further use
    private void openDefaultDatabase() throws IOException {
        sheet = openDefaultXSSFWorkbook();
        loadDatabase = new LoadDatabase(sheet);
        database = loadDatabase.getDatabaseMap();
    }

    //MODIFIES: HashMap<String, NematodesDatabase> database
    //EFFECTS: open custom database file and convert its content into the HashMap<>() for further use
    private void openCustomDatabase() throws IOException {
        sheet = openXSSFWorkbook();
        loadDatabase = new LoadDatabase(sheet);
        database = loadDatabase.getDatabaseMap();
    }

    //getters
    public HashMap<String, NematodesDatabase> getDatabaseMap() {
        return database;
    }

}
