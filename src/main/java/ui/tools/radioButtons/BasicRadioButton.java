package ui.tools.radioButtons;

import ui.AppInterface;
import ui.tools.Tool;

import javax.swing.*;

public abstract class BasicRadioButton extends Tool {

    protected JRadioButton radioButton;

    //Constructor
    public BasicRadioButton(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        createRadioButton(parent);
        addToParent(parent, radioButton);
        addListener();
    }

    //abstract methods
    protected abstract void createRadioButton(JComponent parent);

    protected abstract void customizeRadioButton();

    protected abstract void addListener();

    //REQUIRES: boolean value
    //MODIFIES: JRadioButton radioButton
    //EFFECTS: switching the JRadioButton from selected( value == true) to unselected (value == false)
    protected void selectingRadioButton(boolean value) {
        radioButton.setSelected(value);
    }

    // getters
    public JRadioButton getRadioButton() {
        return radioButton;
    }
}
