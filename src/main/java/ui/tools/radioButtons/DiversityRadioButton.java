package ui.tools.radioButtons;

import ui.AppInterface;
import ui.tools.buttons.Button;
import ui.tools.buttons.OpenDatabaseButton;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiversityRadioButton extends BasicRadioButton {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 10, 50};
    private static final String NAME = "Any organisms";

    //Constructor
    public DiversityRadioButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    //REQUIRES: JComponent parent
    //MODIFIES: this
    //EFFECTS: creates new JRadioButton which is then customized
    @Override
    protected void createRadioButton(JComponent parent) {
        radioButton = new JRadioButton(NAME);
        customizeRadioButton();
    }

    //MODIFIES: this, JRadioButton
    //EFFECTS: activates and set JRadioButton to selected
    @Override
    protected void customizeRadioButton() {
        radioButton.setSelected(true);
        activate();
    }

    //getters
    public static String getButtonName() {
        return NAME;
    }

    //EFFECTS: add listener to this
    @Override
    protected void addListener() {
        radioButton.addActionListener(new DiversityOnlyRadioButtonClickHandler());
    }

    //EFFECTS: inner class for listener
    private class DiversityOnlyRadioButtonClickHandler implements ActionListener {


        //MODIFIES: this
        //EFFECTS: if this is not active, activates this, setting JRadioButton to actual active status of this, switching off
        // other tools - see switchOffButtons
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isActive())
                activate();

            radioButton.setSelected(isActive());
            switchOffButtons(false);
        }

        //REQUIRES: boolean value
        //MODIFIES: NematodesOnlyRadioButton, OpenDatabaseButton, OptionsPane, DatabasePathPane
        //EFFECTS: setting NematodesOnlyRadioButton, OpenDatabaseButton, OptionsPane and DatabasePathPane active status
        // and enable status to false
        private void switchOffButtons(boolean value) {
            switchOffNematodeButton();
            switchOffDatabaseButton();
            switchOffIndicesOptions(value);
        }

        //MODIFIES: NematodesOnlyRadioButton, DiversityOn parameter
        //EFFECTS:  switching off the NematodesOnlyRadioButton, setting DiversityOn parameter to true
        private void switchOffNematodeButton() {
            for (BasicRadioButton button : app.getAllVSNematodesPanel().getRadioButtons()) {
                if (button instanceof NematodesOnlyRadioButton) {
                    button.deactivate();
                    button.selectingRadioButton(button.isActive());
                    app.setDiversityOn(!button.isActive());
                }
            }
        }

        //MODIFIES: OpenDatabaseButton
        //EFFECTS: switching off the OpenDatabaseButton (see switchOffButton method for buttons)
        private void switchOffDatabaseButton() {
            for (Button button : app.getButtonsList()) {
                if (button instanceof OpenDatabaseButton)
                    ((OpenDatabaseButton) button).switchOffButton();

            }
        }

        //MODIFIES: OptionsPane, DatabasePathPane
        //EFFECTS: switching off OptionsPane, DatabasePathPane (see switchOnOff method for panes)
        private void switchOffIndicesOptions(boolean value) {
            for (BasicPane pane : app.getPanesList()) {
                if (!(pane instanceof SourceDataPathPane)) {
                    pane.deactivate();
                    pane.switchOnOff(value);
                }
            }
        }
    }
}
