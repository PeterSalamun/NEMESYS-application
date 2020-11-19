package ui.tools.radioButtons;

import ui.AppInterface;
import ui.tools.buttons.Button;
import ui.tools.buttons.OpenDatabaseButton;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NematodesOnlyRadioButton extends BasicRadioButton {

    private static final int GRIDX = 1;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 50, 10, 5};
    private static final String NAME = "Nematodes only";

    // Constructor
    public NematodesOnlyRadioButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    //REQUIRES: JComponent parent - JPanel
    //MODIFIES: this, JRadioButton
    //EFFECTS: creates and customize JRadioButton
    @Override
    protected void createRadioButton(JComponent parent) {
        radioButton = new JRadioButton(NAME);
        customizeRadioButton();
    }

    //MODIFIES: JRadioButton, this
    //EFFECTS: setting JRadioButton select feature to false
    @Override
    protected void customizeRadioButton() {
        radioButton.setSelected(false);
    }

    //getters
    public static String getButtonName() {
        return NAME;
    }

    //MODIFIES: this
    //EFFECTS: adds listener to this
    @Override
    protected void addListener() {
        radioButton.addActionListener(new NematodesOnlyRadioButtonClickHandler());
    }


    //EFFECTS: inner class for listener
    private class NematodesOnlyRadioButtonClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: if this is not active, activates this, setting JRadioButton to actual active status of this, switching on
        // other tools - see switchOnButtons
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isActive())
                activate();

            radioButton.setSelected(isActive());
            switchOnButtons(true);

        }

        //REQUIRES: boolean value
        //MODIFIES: NematodesOnlyRadioButton, OpenDatabaseButton, OptionsPane, DatabasePathPane
        //EFFECTS: setting NematodesOnlyRadioButton, OpenDatabaseButton, OptionsPane and DatabasePathPane active status
        // and enable status to true
        private void switchOnButtons(boolean value) {
            switchOffDiversityButton();
            switchOnDatabaseButton();
            switchOnIndicesOptions(value);
        }

        //MODIFIES: DiversityButton, DiversityOn parameter
        //EFFECTS:  switching off the DiversityButton, setting DiversityOn parameter to false
        private void switchOffDiversityButton() {
            for (BasicRadioButton button : app.getAllVSNematodesPanel().getRadioButtons()) {
                if (button instanceof DiversityRadioButton) {
                    button.deactivate();
                    button.selectingRadioButton(button.isActive());
                    app.setDiversityOn(button.isActive());
                }

            }
        }

        //MODIFIES: OpenDatabaseButton
        //EFFECTS: switching on the OpenDatabaseButton (see switchOnButton method for buttons)
        private void switchOnDatabaseButton() {
            for (Button button : app.getButtonsList()) {
                if (button instanceof OpenDatabaseButton)
                    ((OpenDatabaseButton) button).switchOnButton();

            }
        }

        //MODIFIES: OptionsPane, DatabasePathPane
        //EFFECTS: switching on OptionsPane, DatabasePathPane (see switchOnOff method for panes)
        private void switchOnIndicesOptions(boolean value) {
            for (BasicPane pane : app.getPanesList())
                if (!(pane instanceof SourceDataPathPane)){
                    pane.activate();
                    pane.switchOnOff(value);}
        }
    }
}
