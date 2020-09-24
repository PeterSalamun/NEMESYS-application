package ui.tools.radioButtons;

import ui.AppInterface;
import ui.tools.Tool;

import javax.swing.*;

public abstract class BasicRadioButton extends Tool {

    JRadioButton radioButton;

    public BasicRadioButton(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        createRadioButton(parent);
        addToParent(parent, radioButton);
        addListener();
    }

    protected abstract void createRadioButton(JComponent parent);

    protected abstract void customizeRadioButton();

    protected abstract void addListener();

    protected void selectingRadioButton(boolean value) {
        radioButton.setSelected(value);
    }
}
