package ui.tools.menuTools.JMenus;

import ui.tools.menuTools.JMenusItems.DownloadDatabseJMenuItem;
import ui.tools.menuTools.JMenusItems.ExitJMenuItem;

import javax.swing.*;

public class FileJMenu extends BasicJMenu {

    public FileJMenu(JComponent parent, String name) {
        super(parent, name);
    }

    @Override
    protected void addMenuItems(JMenu parent) {
        upName = "Download database";
        downName = "Exit";

        DownloadDatabseJMenuItem downloadDatabaseItem = new DownloadDatabseJMenuItem(parent, upName);
        ExitJMenuItem exitItem = new ExitJMenuItem(parent, downName);

    }
}
