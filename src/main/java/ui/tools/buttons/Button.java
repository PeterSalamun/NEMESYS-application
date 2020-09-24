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
    protected static final String DEFAULTDATABASEPATH = "/fullDatabase.xlsx";

    public Button(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDWIDTH, int GRIDHEIGHT, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDWIDTH, GRIDHEIGHT, INSETS);

        createButton(parent);
        addToParent(parent, button);
        addListener();
    }

    protected void selectingOptionPane(ArrayList<BasicPane> panesList, boolean value) {
        for (BasicPane pane : panesList) {
            if (pane instanceof OptionsPane) {
                selectingAllBoxes(pane.getActivePanel().getCheckBoxesList(), value);
            }
        }
    }

    protected void selectingAllBoxes(ArrayList<BasicCheckBox> boxList, boolean value) {
        for(BasicCheckBox box: boxList) {
            box.boxOnOffSwitching(value);
        }
    }

    protected abstract void createButton(JComponent parent);

    protected abstract void customizeButton(JComponent panel);

    public void switchOnButton() {
        activate();
        button.setEnabled(true);
    }

    public void switchOffButton() {
        deactivate();
        button.setEnabled(false);
    }
}
