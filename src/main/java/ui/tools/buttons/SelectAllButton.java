package ui.tools.buttons;

import ui.AppInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectAllButton extends Button {

    private static final int GRIDX = 0;
    private static final int GRIDY = 7;
    private static final int GRIDWIDTH = 1;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 10};
    private static final String NAME = "Select All";

    public SelectAllButton(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(NAME);
        customizeButton(button);
    }

    @Override
    protected void customizeButton(JComponent panel) {
        button.setPreferredSize(BUTTONDIMENSION);
    }


    @Override
    protected void addListener() {
        button.addActionListener(new SelectAllButtonClickHandler());
    }

    private class SelectAllButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            selectingOptionPane(app.getPanesList(), true);
        }
    }
}
