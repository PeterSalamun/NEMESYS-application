package ui.tools.panes.TextPane;

import ui.AppInterface;
import ui.tools.panes.BasicPane;

import javax.swing.*;
import java.awt.*;

public class DatabasePathPane extends BasicPane {

    private static final int GRIDX = 1;
    private static final int GRIDY = 1;
    private static final int GRIDWIDTH = 3;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final String NAME = "DATABASE FILE";
    private static final String DEFAULT_TEXT = "   Default database";

    //Constructor
    public DatabasePathPane(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        deactivate();
    }

    //REQUIRES: JComponent parent - JPanel
    //MODIFIES: this, JTextPane textPane, parent
    //EFFECTS: creates and customize JTextPane textPane, adds textPane to parent JPanel (see appropriate methods
    // customize() and addToParent())
    @Override
    protected void createPane(JComponent parent) {
        textPane = new JTextPane();
        customizePane(textPane);
        addToParent(parent, textPane);
    }

    //REQUIRES: JTextPane textPane
    //MODIFIES: this, JTextPane textPane
    //EFFECTS: setting different attributes of JTextPane textPane
    @Override
    protected void customizePane(JComponent pane) {
        textPane.setEditable(false);
        textPane.setPreferredSize(new Dimension(380, 60));
        textPane.setBorder(BorderFactory.createTitledBorder(NAME));
        textPane.setBackground(parent.getBackground());
        textPane.setFont(new Font("Arial", Font.PLAIN, 12));
        textPane.setText(DEFAULT_TEXT);
        textPane.setEnabled(false);
    }

    //REQUIRES: boolean bValue
    //MODIFIES: this, JTextPane textPane
    //EFFECTS: setting enable attribute of JTextPane at boolean bValue
    public void switchOnOff(boolean bValue){
        textPane.setEnabled(bValue);
    }

}
