package ui.tools.panes.TabbedPanes;

import ui.AppInterface;
import ui.tools.panels.BasicPanel;
import ui.tools.panels.DiversityPanel;
import ui.tools.panels.FootprintsPanel;
import ui.tools.panels.FunctionalPanel;
import ui.tools.panes.BasicPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionsPane extends BasicPane {


    private static final int GRIDX = 0;
    private static final int GRIDY = 3;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 3;
    private static final int[] INSETS = {5, 5, 5, 10};
    private ArrayList<BasicPanel> optionPanelList;
    private BasicPanel activePanel;

    //Constructor
    public OptionsPane(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        activate();
    }

    //MODIFIES: this
    //EFFECTS: initialize optionPanelList arrayList
    private void initializeFields() {
        optionPanelList = new ArrayList<>();
    }

    //REQUIERS: JComponent parent - JPanel
    //MODIFIES: this, parent
    //EFFECTS: creates and customize JTabbedPane tabbedPane, initialize optionPaneList (initializeFields() method)
    @Override
    protected void createPane(JComponent parent) {
        tabbedPane = new JTabbedPane();
        initializeFields();
        customizePane(tabbedPane);
    }

    //REQUIRES: JTabbedPane tabbedPane
    //MODIFIES: this, JTabbedPane tabbedPane
    //EFFECTS: setting size of JTabbedPane tabbedPane, adding 3 BasicPanels (Diversity, Functional, Footprint JPanels)
    // and customize thme (customizePanePanels())
    @Override
    protected void customizePane(JComponent pane) {
        pane.setPreferredSize(new Dimension(505, 185));
        addPanels(pane);

        for (int i = 0; i < optionPanelList.size(); i++) {
            BasicPanel panel = optionPanelList.get(i);
            customizePanePanels((JTabbedPane) pane, panel, i);
        }
    }

    //REQUIRES: AppInterface, JTabbedPane
    //MODIFIES: this, JTabbedPane tabbedPane, activePanel, optionPaneList
    //EFFECTS: creating DiversityPanel, FunctionalPanel, FootprintPanel and adding them to optionPanelList array,
    // setting DiversityPanel as activePanel
    @Override
    protected void addPanels(JComponent parent) {
        DiversityPanel diversityPanel = new DiversityPanel(app, parent);
        activePanel = diversityPanel;
        optionPanelList.add(diversityPanel);

        FunctionalPanel functionalPanel = new FunctionalPanel(app, parent);
        optionPanelList.add(functionalPanel);

        FootprintsPanel footprintsPanel = new FootprintsPanel(app, parent);
        optionPanelList.add(footprintsPanel);

    }

    //REQUIRES: JTabbedPane, BasicPanel, int
    //MODIFIES: JTabbedPane parent, BasicPanel child
    //EFFECTS: setting JTabbedPane parent color and title according to BasicPanel child, enabling only first BasicPanel
    // child (DiversityPanel) in JTabbedPane parent
    private void customizePanePanels(JTabbedPane parent, BasicPanel child, int index) {
        parent.setBackgroundAt(index, child.getActualColor());
        parent.setTitleAt(index, child.getTitle());
        if(index != 0)
            parent.setEnabledAt(index, false);
    }

    //REQUIRES: optionPanelList, BasicPanel
    //MODIFIES: BasicPanel activePanel
    //EFFECTS: activate panel choose by user and deactivate current active panel in the optionPane
    private void switchingActivePanels() {
        for (int i = 0; i < optionPanelList.size(); i++) {
            if (tabbedPane.getSelectedIndex() == i) {
                optionPanelList.get(i).activate();
                activePanel.deactivate();
                activePanel = optionPanelList.get(i);
            }
        }
    }

    //REQUIRES: boolean bValue
    //MODIFIES: BasicPanel
    //EFFECTS: depending on boolean bValue setting BasicPanel FunctionalPanel and BasicPanel FootprintPanel to enabled
    // or disabled, and switching actual active panel to DiversityPanel
    public void switchOnOff(boolean bValue) {
        for (int i = 0; i < optionPanelList.size(); i++)
            if(optionPanelList.get(i) instanceof DiversityPanel)
                tabbedPane.setSelectedIndex(i);
            else {
                tabbedPane.setEnabledAt(i, bValue);
                if(bValue)
                    optionPanelList.get(i).switchOn();
                else
                    optionPanelList.get(i).switchOff();
            }
        switchingActivePanels();
    }

    //getters
    @Override
    public ArrayList<BasicPanel> getOptionPanels() {
        return optionPanelList;
    }

    public BasicPanel getDiversityPanel() {return optionPanelList.get(0);}

    public BasicPanel getFunctionalPanel() {return optionPanelList.get(1);}

    public BasicPanel getFootprintsPanel() {return optionPanelList.get(2);}

    public BasicPanel getActivePanel() {
        switchingActivePanels();
        return activePanel;
    }



}
