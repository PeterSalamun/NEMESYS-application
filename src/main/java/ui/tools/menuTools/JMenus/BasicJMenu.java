package ui.tools.menuTools.JMenus;

import javax.swing.*;

public abstract class BasicJMenu extends JMenu {

    JMenu menu;
    String upName;
    String downName;

    public BasicJMenu(JComponent parent, String name) {
        createJMenu(parent, name);
    }

    private void createJMenu(JComponent parent, String name) {
        menu = new JMenu(name);
        addMenuItems(menu);
        parent.add(menu);
    }

    protected abstract void addMenuItems(JMenu parent);
}
