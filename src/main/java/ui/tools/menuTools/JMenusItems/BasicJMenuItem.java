package ui.tools.menuTools.JMenusItems;

import javax.swing.*;

public abstract class BasicJMenuItem extends JMenuItem {

    protected JMenuItem menuItem;
    protected String title;
    protected String message;
    protected String name;

    //Constructor
    public BasicJMenuItem(JMenu parent, String name) {
        super(name);
        this.name = name;
        createMenuItem(parent, name);
        addListener();
    }

    //REQUIRES: JMenu, String
    //MODIFIES: JMenu
    //EFFECTS: creates new JMenuItem and adds it to parent (parent.add())
    protected void createMenuItem(JMenu parent, String name) {
        menuItem = new JMenuItem(name);
        parent.add(menuItem);
    }

    //getters
    public String getTitle() {
        return title;
    }

    public String getItemName() {
        return name;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public String getMessage() {
        return message;
    }

    // EFFECTS: abstract method
    protected abstract void addListener();
}
