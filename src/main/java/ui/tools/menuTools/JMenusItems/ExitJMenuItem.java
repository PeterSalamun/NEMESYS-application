package ui.tools.menuTools.JMenusItems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitJMenuItem extends BasicJMenuItem {

    //Constructor
    public ExitJMenuItem(JMenu parent, String name) {
        super(parent, name);
    }

    //MODIFIES: JMenuItem
    //EFFECTS: adds Listener to JMenuItem
    @Override
    protected void addListener() {
        menuItem.addActionListener(new ExitJMenuItemClickHandler());
    }

    private class ExitJMenuItemClickHandler implements ActionListener {

        //EFFECTS: Ends application
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
