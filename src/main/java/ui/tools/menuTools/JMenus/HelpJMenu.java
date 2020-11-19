package ui.tools.menuTools.JMenus;

import ui.tools.menuTools.JMenusItems.CreditJMenuItem;
import ui.tools.menuTools.JMenusItems.LicenceJMenuItem;
import ui.tools.menuTools.JMenusItems.ManualJMenuItem;

import javax.swing.*;

public class HelpJMenu extends BasicJMenu {

    private final String creditName = "Credit";
    private final String manualName = "Manual";
    private final String licenceName = "Licence";

    //Constructor
    public HelpJMenu(JComponent parent, String name) {
        super(parent, name);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    @Override
    protected void addMenuItems(JMenu parent) {
        CreditJMenuItem creditItem = new CreditJMenuItem(parent, this.creditName);
        ManualJMenuItem manualItem = new ManualJMenuItem(parent, this.manualName);
        LicenceJMenuItem licenceItem  = new LicenceJMenuItem(parent, this.licenceName);
    }

    public String getCreditName() {
        return creditName;
    }

    public String getManualName() {
        return manualName;
    }

    public String getLicenceName() {
        return licenceName;
    }
}
