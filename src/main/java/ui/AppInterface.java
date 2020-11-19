package ui;

import model.computing.data.NematodesDatabase;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import ui.tools.Tool;
import ui.tools.buttons.Button;
import ui.tools.buttons.*;
import ui.tools.menuTools.JMenus.FileJMenu;
import ui.tools.menuTools.JMenus.HelpJMenu;
import ui.tools.panels.AllVSNematodesPanel;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TabbedPanes.OptionsPane;
import ui.tools.panes.TextPane.DatabasePathPane;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

// Main class for creating the graphic interface of the application and storing all the neccessary informations for
// obtained from user (choose indices, custome database, data file)
public class AppInterface extends JFrame {

    private static final int WIDTH = 555;
    private static final int HEIGHT = 525;
    private boolean isDiversityOn = true;
    private ArrayList<Tool> tools;
    private ArrayList<Button> buttons;
    private ArrayList<BasicPane> panes;
    private ArrayList<String> taxaList;
    private HashMap<String, NematodesDatabase> databaseHashMap;
    private XSSFSheet sampleSheet;
    private XSSFSheet databaseSheet;

    private boolean sampleSourceChecked = false;

    private String sourceFilePath;
    private boolean databaseCheck = false;

    // constructor
    public AppInterface() {
        super("Nemesys");
        initializeFields();
        initializeGraphics();
    }



    // initialize fields for storing the list of different JComponents during the run of the application
    private void initializeFields() {
        tools = new ArrayList<>();
        buttons = new ArrayList<>();
        panes = new ArrayList<>();
        databaseHashMap = new HashMap<>();
    }

    // initialize graphic interface of the main window of the application
    private void initializeGraphics() {
        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLookAndFeel();

        createMenuBar();
        createTools();

        pack();
    }

    // setting the look and feel of the main application window to that of the PC where the application is running
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    // create tools  which are displayed on the main application window by calling specific methods for each type of tool,
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setSize(new Dimension(WIDTH, HEIGHT));
        add(toolArea);

        //Panel AllVSNematodesPanel where the mode of calculation is displayed (in a form of JRadioButtons)
        AllVSNematodesPanel allVSNematodesPanel = new AllVSNematodesPanel(this, toolArea);
        tools.add(allVSNematodesPanel);

        //method for creating TextPanes and TabbedPanes
        createPanes(toolArea);

        //method for creating Buttons
        createButtons(toolArea);

    }

    // creating three different panes (2 TextPanes, 1 TabbedPane) which are displayed at the application main window
    private void createPanes(JPanel toolArea) {
        //Database TextPane, where the application displayed actual filepath of database file which is in use. User do
        // not have to deliver the database in case he is not using application for computing specific nematode indices
        // or decided to use the default database
        DatabasePathPane databasePathPane = new DatabasePathPane(this, toolArea);
        panes.add(databasePathPane);

        //Source TextPane displayed filepath of file where the abundances of organisms are store - the file has to be
        // delivered by the user
        SourceDataPathPane sourcePathPane = new SourceDataPathPane(this, toolArea);
        panes.add(sourcePathPane);

        //Pane where all the indices available (divided in three separate panels - diversity, footprints, functional
        // indices) in the application are displayed and user can choose which indices should
        // be calculated by checking the BasicCheckBoxes
        OptionsPane optionsTabbedPane = new OptionsPane(this, toolArea);
        panes.add(optionsTabbedPane);
    }

    // creates Buttons which are displayed at the application main window
    private void createButtons(JPanel toolArea) {
        //Button to upload data file provided by the user
        OpenSourceDataButton dataButton = new OpenSourceDataButton(this, toolArea);
        buttons.add(dataButton);

        //Button to upload custom nematode database provided by the user
        OpenDatabaseButton databaseButton = new OpenDatabaseButton(this, toolArea);
        buttons.add(databaseButton);

        // Button to deselect all BasicCheckBoxes at active panel at the options pane
        DeselectAllButton deselectAllButton = new DeselectAllButton(this, toolArea);
        buttons.add(deselectAllButton);

        // Button to select all BasicCheckBoxes at active panel at the options pane
        SelectAllButton selectAllButton = new SelectAllButton(this, toolArea);
        buttons.add(selectAllButton);

        // Button to start the processing and computing the results for each index which was choose
        CalculateButton calculateButton = new CalculateButton(this, toolArea);
        buttons.add(calculateButton);

        // Button to end the application
        ExitButton exitButton = new ExitButton(this, toolArea);
        buttons.add(exitButton);
    }

    // creates MenuBar with two items - "File" and "Help" menu
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        FileJMenu file = new FileJMenu(menuBar, "File");
        HelpJMenu help = new HelpJMenu(menuBar, "Help");
    }



    //getters
    public ArrayList<Button> getButtonsList() {
        return buttons;
    }

    public AllVSNematodesPanel getAllVSNematodesPanel() {
        for (Tool tool : tools)
            if (tool instanceof AllVSNematodesPanel)
                return (AllVSNematodesPanel) tool;

        return null;
    }

    public ArrayList<BasicPane> getPanesList() {
        return panes;
    }

    public OptionsPane getOptionsPane() {
        return (OptionsPane) panes.get(2);
    }

    public HashMap<String, NematodesDatabase> getDatabaseMap() {
        return databaseHashMap;
    }

    public XSSFSheet getSampleSheet() {
        return sampleSheet;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public XSSFSheet getDatabaseSheet() {
        return databaseSheet;
    }

    public ArrayList<String> getTaxaList() {
        return taxaList;
    }

    public boolean isDiversityOn() {
        return isDiversityOn;
    }

    public boolean isDatabaseChecked() {
        return databaseCheck;
    }

    public boolean isSamplesSourceChecked() {
        return sampleSourceChecked;
    }



    //setters
    public void setDatabaseMap(HashMap<String, NematodesDatabase> map) {
        databaseHashMap = map;
    }

    public void setDiversityOn(boolean diversityOn) {
        isDiversityOn = diversityOn;
    }

    public void setSourceFilePath(String filePath) {
        sourceFilePath = filePath;
    }

    public void setSampleSheet(XSSFSheet sampleSheet) {
        this.sampleSheet = sampleSheet;
    }

    public void setDatabaseSheet(XSSFSheet databaseSheet) {
        this.databaseSheet = databaseSheet;
    }

    public void setDatabaseChecked(boolean databaseCheck) {
        this.databaseCheck = databaseCheck;
    }

    public void setSampleSourceChecked(boolean sampleSourceChecked) {
        this.sampleSourceChecked = sampleSourceChecked;
    }

    public void setTaxaList(ArrayList<String> taxaList) {
        this.taxaList = taxaList;
    }


}
