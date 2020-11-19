package ui.tools.buttons;

import ui.AppInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends Button {

    private static final int GRIDX = 3;
    private static final int GRIDY = 7;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 10};
    private static final String NAME = "Exit";

    //Constructor
    public ExitButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        switchOnButton();
    }

    //REQUIRES: JPanel
    //EFFECTS: creates and customize (see customizeButton() method) JButton
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }

    //MODIFIES: JButton
    //EFFECTS: customize JButton by setting its attributes
    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(BUTTONDIMENSION);
    }

    //MODIFIES: JButton
    //EFFECTS: adds Listener to JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ExitButtonClickHandler());
    }

    private class ExitButtonClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECTS: shuts down the application
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
