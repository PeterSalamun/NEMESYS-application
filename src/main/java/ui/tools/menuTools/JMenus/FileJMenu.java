package ui.tools.menuTools.JMenus;

import ui.tools.menuTools.JMenusItems.DownloadDatabaseJMenuItem;
import ui.tools.menuTools.JMenusItems.ExitJMenuItem;

import javax.swing.*;

public class FileJMenu extends BasicJMenu {

    private final String databaseName = "Download database";
    private final String exitName = "Exit";

    //Constructor
    public FileJMenu(JComponent parent, String name) {
        super(parent, name);
    }

    //REQUIRES: JMenu
    //MODIFIES: JMenu
    //EFFECTS: creates JMenuItems (downloadDatabaseItem, exitItem) in JMenu
    @Override
    protected void addMenuItems(JMenu parent) {
        DownloadDatabaseJMenuItem downloadDatabaseItem = new DownloadDatabaseJMenuItem(parent, databaseName);
        ExitJMenuItem exitItem = new ExitJMenuItem(parent, exitName);
    }

    //getters
    public String getDatabaseName() {
        return databaseName;
    }

    public String getExitName() {
        return exitName;
    }
}
