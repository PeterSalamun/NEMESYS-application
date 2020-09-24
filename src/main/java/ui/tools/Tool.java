package ui.tools;

import ui.AppInterface;

import javax.swing.*;
import java.awt.*;

public abstract class Tool extends JComponent {

    protected JComponent parent;
    protected boolean active;
    protected AppInterface app;

    protected GridBagConstraints grid;

    public Tool(AppInterface app, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        this.app = app;
        this.parent = parent;
        active = false;
        this.grid = setSpecificGrid(GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    public void addToParent(JComponent parent, JComponent child) {
        parent.add(child, this.grid);
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public GridBagConstraints setSpecificGrid(int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = GRIDX;
        grid.gridy = GRIDY;
        grid.gridwidth = GRIDWIDTH;
        grid.gridheight = GRIDHEIGHT;
        grid.insets = new Insets(INSETS[0], INSETS[1], INSETS[2], INSETS[3]);
        grid.anchor = GridBagConstraints.WEST;
        return grid;
    }

    // EFFECTS: adds a listener for this tool
    protected void addListener(){}

}
