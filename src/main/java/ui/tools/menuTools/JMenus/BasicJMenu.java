package ui.tools.menuTools.JMenus;

import javax.swing.*;

public abstract class BasicJMenu extends JMenu {

    protected JMenu menu;
    protected String name;

    //Constructor
    public BasicJMenu(JComponent parent, String name) {
        this.name = name;
        createJMenu(parent, name);
    }

    //REQUIRE: JPanel parent, String
    //MODIFIES: JPanel
    //EFFECTS: creates JMenu and adds it to parent, and add JMenuItems (addMenuItems()) to JMenu
    private void createJMenu(JComponent parent, String name) {
        menu = new JMenu(name);
        addMenuItems(menu);
        parent.add(menu);
    }

    // EFFECTS: abstract methods
    protected abstract void addMenuItems(JMenu parent);

    //getters
    public JMenu getMenu() {
        return menu;
    }

    @Override
    public String getName() {
        return name;
    }
}
