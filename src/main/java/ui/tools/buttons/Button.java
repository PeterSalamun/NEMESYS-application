package ui.tools.buttons;

import ui.AppInterface;
import ui.tools.Tool;
import ui.tools.checkBoxes.BasicCheckBox;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TabbedPanes.OptionsPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Button extends Tool {

    protected JButton button;
    protected final Dimension BUTTONDIMENSION = new Dimension(115, 25);
    protected final String DEFAULTDATABASEPATH = "/fullDatabase.xlsx";

    //Constructor
    public Button(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDWIDTH, int GRIDHEIGHT, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDWIDTH, GRIDHEIGHT, INSETS);

        createButton(parent);
        addToParent(parent, button);
        addListener();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    protected void selectingOptionPane(ArrayList<BasicPane> panesList, boolean value) {
        for (BasicPane pane : panesList) {
            if (pane instanceof OptionsPane) {
                selectingAllBoxes(pane.getActivePanel().getCheckBoxesList(), value);
            }
        }
    }

    //REQUIRES: ArrayList<BasicCheckBox>, boolean
    //MODIFIES: BasicCheckBox
    //EFFECTS:  setting all BasicCheckBoxes in ArrayList to setEnabled(true) (see BasicCheckBox.boxOnOffSwitching()
    //method)
    protected void selectingAllBoxes(ArrayList<BasicCheckBox> boxList, boolean value) {
        for(BasicCheckBox box: boxList) {
            box.boxOnOffSwitching(value);
        }
    }

    //MODIFIES: JButton
    //EFFECTS: activates this and set button.setEnabled() to true
    public void switchOnButton() {
        activate();
        button.setEnabled(true);
    }

    //MODIFIES: JButton
    //EFFECTS: deactivates this and set button.setEnabled() to false
    public void switchOffButton() {
        deactivate();
        button.setEnabled(false);
    }

    //EFFECTS: abstract methods
    protected abstract void createButton(JComponent parent);

    protected abstract void customizeButton(JComponent panel);

    //getters
    public JButton getButton() {
        return button;
    }
}
