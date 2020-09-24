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

    public DiversityRadioButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void createRadioButton(JComponent parent) {
        radioButton = new JRadioButton(NAME);
        customizeRadioButton();
    }

    @Override
    protected void customizeRadioButton() {
        radioButton.setSelected(true);
        activate();
    }

    @Override
    protected void addListener() {
        radioButton.addActionListener(new DiversityOnlyRadioButtonClickHandler());
    }

    private class DiversityOnlyRadioButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isActive())
                activate();

            radioButton.setSelected(isActive());
            switchOffButtons(false);
        }

        private void switchOffButtons(boolean value) {
            switchOffNematodeButton();
            switchOffDatabaseButton();
            switchOffIndicesOptions(value);
        }

        private void switchOffNematodeButton() {
            for (BasicRadioButton button : app.getAllVSNematodesPanel().getRadioButtons()) {
                if (button instanceof NematodesOnlyRadioButton) {
                    button.deactivate();
                    button.selectingRadioButton(button.isActive());
                    app.setDiversityOn(!button.isActive());
                }
            }
        }

        private void switchOffDatabaseButton() {
            for (Button button : app.getButtonsList()) {
                if (button instanceof OpenDatabaseButton)
                    ((OpenDatabaseButton) button).switchOff();

            }
        }

        private void switchOffIndicesOptions(boolean value) {
            for (BasicPane pane : app.getPanesList()) {
                if (!(pane instanceof SourceDataPathPane)) {
                    pane.switchOnOff(value);
                }
            }

        }
    }
}
