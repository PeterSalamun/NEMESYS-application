package ui.tools.panels;

import ui.AppInterface;
import ui.tools.Tool;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class BasicPanel extends Tool {

    JPanel panel;
    Color basicColor;
    String title;
    ArrayList<BasicCheckBox> checkboxesList;

    //Constructor
    public BasicPanel(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH,
                      int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        initializeFields();
    }

    //MODIFIES: JPanel panel
    //EFFECTS:  deactivate the panel (set active to false) and set enable of the panel to active status
    public void switchOff() {
        deactivate();
        panel.setEnabled(isActive());
    }

    //MODIFIES: JPanel panel
    //EFFECTS:  activate the panel (set active to true) and set enable of the panel to active status
    public void switchOn() {
        activate();
        panel.setEnabled(isActive());
    }

    //abstract and override methods without bodies
    protected void customizePanel(JComponent panel) {
    }

    //REQUIRES: JPanel, String[], Color
    //MODIFIES: JPanel, this
    //EFFECTS: creating JPanel
    protected void createPanel(JComponent parent, String[] indicesNames, Color color) {
        panel = new JPanel();
        customizePanel(panel);
        addTools(panel, indicesNames, color);
        addToParent(parent, panel);
    }

    //REQUIRES: JPanel, String[], Color
    //MODIFIES: JPanel
    //EFFECTS: adding BasicCheckBoxes to the parent JPanel with color of the parent JPanel and appropriate name of the
    // index which correspond with the checkbox
    protected void addTools(JComponent parent, String[] indicesNames, Color color) {
        for (String name : indicesNames) {
            BasicCheckBox box = new BasicCheckBox(app, parent, color, name);
            checkboxesList.add(box);
        }
    }

    //EFFECTS: initialize checkboxesList arrayList<BasicCheckBoxes>
    private void initializeFields() {
        checkboxesList = new ArrayList<>();
    }

    //getters
    public Color getActualColor() {
        return basicColor;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<BasicCheckBox> getCheckBoxesList() {
        return checkboxesList;
    }

    //REQUIRES: checkboxesList
    //MODIFIES: ArrayList<Boolean> list
    //EFFECTS: returning list of boolean values depending on if the BasicChcekBox is selected or not in checkboxesList
    public ArrayList<Boolean> getSelectedCheckBoxes() {
        ArrayList<Boolean> list = new ArrayList<>();

        for (BasicCheckBox box : checkboxesList) {
            if (box.isActive())
                list.add(true);
            else
                list.add(false);
        }

        return list;
    }
}
