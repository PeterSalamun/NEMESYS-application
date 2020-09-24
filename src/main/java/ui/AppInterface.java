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

    private boolean withoutErrors = false;
    private boolean sampleSourceChecked = false;

    private String sourceFilePath;
    private boolean databaseCheck = false;


    public AppInterface() {
        super("Nemesys");
        initializeFields();
        initializeGraphics();
    }

    private void initializeFields() {
        tools = new ArrayList<>();
        buttons = new ArrayList<>();
        panes = new ArrayList<>();
        databaseHashMap = new HashMap<>();
    }

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

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setSize(new Dimension(WIDTH, HEIGHT));
        add(toolArea);

        //Panel
        AllVSNematodesPanel allVSNematodesPanel = new AllVSNematodesPanel(this, toolArea);
        tools.add(allVSNematodesPanel);

        //Text and TabbedPanes
        createPanes(toolArea);

        //Buttons
        createButtons(toolArea);

    }

    private void createPanes(JPanel toolArea) {
        DatabasePathPane databasePathPane = new DatabasePathPane(this, toolArea);
        panes.add(databasePathPane);

        SourceDataPathPane sourcePathPane = new SourceDataPathPane(this, toolArea);
        panes.add(sourcePathPane);

        OptionsPane optionsTabbedPane = new OptionsPane(this, toolArea);
        panes.add(optionsTabbedPane);
    }

    private void createButtons(JPanel toolArea) {
        OpenSourceDataButton dataButton = new OpenSourceDataButton(this, toolArea);
        buttons.add(dataButton);

        OpenDatabaseButton databaseButton = new OpenDatabaseButton(this, toolArea);
        buttons.add(databaseButton);

        DeselectAllButton deselectAllButton = new DeselectAllButton(this, toolArea);
        buttons.add(deselectAllButton);

        SelectAllButton selectAllButton = new SelectAllButton(this, toolArea);
        buttons.add(selectAllButton);

        CalculateButton calculateButton = new CalculateButton(this, toolArea);
        buttons.add(calculateButton);

        ExitButton exitButton = new ExitButton(this, toolArea);
        buttons.add(exitButton);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        FileJMenu file = new FileJMenu(menuBar, "File");
        HelpJMenu help = new HelpJMenu(menuBar, "Help");
    }

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

    public void setDatabaseMap(HashMap<String, NematodesDatabase> map) {
        databaseHashMap = map;
    }

    public boolean isDiversityOn() {
        return isDiversityOn;
    }

    public void setDiversityOn(boolean diversityOn) {
        isDiversityOn = diversityOn;
    }

    public void setSourceFilePath(String filePath) {
        sourceFilePath = filePath;
    }

    public XSSFSheet getSampleSheet() {
        return sampleSheet;
    }

    public void setSampleSheet(XSSFSheet sampleSheet) {
        this.sampleSheet = sampleSheet;
    }

    public XSSFSheet getDatabaseSheet() {
        return databaseSheet;
    }

    public void setDatabaseSheet(XSSFSheet databaseSheet) {
        this.databaseSheet = databaseSheet;
    }

    public boolean isDatabaseChecked() {
        return databaseCheck;
    }

    public void setDatabaseChecked(boolean databaseCheck) {
        this.databaseCheck = databaseCheck;
    }

    public boolean isSamplesSourceChecked() {
        return sampleSourceChecked;
    }

    public void setSampleSourceChecked(boolean sampleSourceChecked) {
        this.sampleSourceChecked = sampleSourceChecked;
    }

    public void setTaxaList(ArrayList<String> taxaList) {
        this.taxaList = taxaList;
    }

    public ArrayList<String> getTaxaList() {
        return taxaList;
    }
}
