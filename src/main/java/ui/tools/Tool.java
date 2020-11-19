package ui.tools;

import ui.AppInterface;

import javax.swing.*;
import java.awt.*;

// basic abstract class for creating all the tools - Panels, Buttons, CheckBoxes, Panes, (except MenuBar) used in
// application graphic interface
public abstract class Tool extends JComponent {

    protected JComponent parent;
    protected boolean active;
    protected AppInterface app;

    protected GridBagConstraints grid;

    //Constructor
    public Tool(AppInterface app, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        this.app = app;
        this.parent = parent;
        active = false;
        this.grid = setSpecificGrid(GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    //REQUIRES: JComponent parent, JComponent child
    //MODIFIES: JComponent parent
    //EFFECTS: Adding child component to parent component e.g. button to panel
    public void addToParent(JComponent parent, JComponent child) {
        parent.add(child, this.grid);
    }

    //MODIFIES: this
    //EFFECTS: change the active status of this to true, in other words activates this
    public void activate() {
        active = true;
    }

    //MODIFIES: this
    //EFFECTS: change the active status of this to false, in other words deactivates this
    public void deactivate() {
        active = false;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    //setters
    //MODIFIES: this
    //EFFECTS: add this to specific place in the GridBagLayout of the parent component
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

    // EFFECTS: adds a listener to this
    protected void addListener(){}

}
