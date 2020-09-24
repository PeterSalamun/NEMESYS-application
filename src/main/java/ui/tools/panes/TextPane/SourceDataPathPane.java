package ui.tools.panes.TextPane;

import ui.AppInterface;
import ui.tools.panes.BasicPane;

import javax.swing.*;
import java.awt.*;

public class SourceDataPathPane extends BasicPane {

    private static final int GRIDX = 1;
    private static final int GRIDY = 2;
    private static final int GRIDWIDTH = 3;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5,5,5,5};
    private static final String NAME = "SOURCE DATA FILE";

    public SourceDataPathPane(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void createPane(JComponent parent) {
        textPane = new JTextPane();
        customizePane(textPane);
        addToParent(parent, textPane);
    }

    @Override
    protected void customizePane(JComponent pane) {
        textPane.setEditable(false);
        textPane.setPreferredSize(new Dimension(380, 60));
        textPane.setBorder(BorderFactory.createTitledBorder(NAME));
        textPane.setBackground(parent.getBackground());
        textPane.setFont(new Font("Arial", Font.PLAIN, 12));
        textPane.setText("");
    }

    @Override
    public void setDatabasePathString(String pathString) {
        if (!pathString.equals(null))
            textPane.setText(pathString);
        else
            textPane.setText(null);
    }

    @Override
    protected void addListener() {

    }
}
