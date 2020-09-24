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

    public NematodesOnlyRadioButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void createRadioButton(JComponent parent) {
        radioButton = new JRadioButton(NAME);
        customizeRadioButton();
    }

    @Override
    protected void customizeRadioButton() {
        radioButton.setSelected(false);
    }


    @Override
    protected void addListener() {
        radioButton.addActionListener(new NematodesOnlyRadioButtonClickHandler());
    }

    private class NematodesOnlyRadioButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isActive())
                activate();

            radioButton.setSelected(isActive());
            switchOnButtons(true);

        }

        private void switchOnButtons(boolean value) {
            switchOffDiversityButton();
            switchOnDatabaseButton();
            switchOnIndicesOptions(value);
        }

        private void switchOffDiversityButton() {
            for (BasicRadioButton button : app.getAllVSNematodesPanel().getRadioButtons()) {
                if (button instanceof DiversityRadioButton) {
                    button.deactivate();
                    button.selectingRadioButton(button.isActive());
                    app.setDiversityOn(button.isActive());
                }

            }
        }

        private void switchOnDatabaseButton() {
            for (Button button : app.getButtonsList()) {
                if (button instanceof OpenDatabaseButton)
                    ((OpenDatabaseButton) button).switchOn();

            }
        }

        private void switchOnIndicesOptions(boolean value) {
            for (BasicPane pane : app.getPanesList())
                if (!(pane instanceof SourceDataPathPane))
                    pane.switchOnOff(value);
        }
    }
}
