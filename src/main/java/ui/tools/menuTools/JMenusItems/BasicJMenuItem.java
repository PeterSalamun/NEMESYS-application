package ui.tools.menuTools.JMenusItems;

import javax.swing.*;

public abstract class BasicJMenuItem extends JMenuItem {

    protected static final String DEFAULTDATABASEPATH = "/fullDatabase.xlsx";

    JMenuItem menuItem;
    protected String title;
    protected String message;


    public BasicJMenuItem(JMenu parent, String name) {
        super(name);
        createMenuItem(parent, name);
        addListener();
    }

    protected void createMenuItem(JMenu parent, String name) {
        menuItem = new JMenuItem(name);
        parent.add(menuItem);
    }

    protected abstract void addListener();

    protected String getTitle() {return title;}

}
