package ui.tools.menuTools.JMenusItems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitJMenuItem extends BasicJMenuItem {

    public ExitJMenuItem(JMenu parent, String name) {
        super(parent, name);
    }


    @Override
    protected void addListener() {
        menuItem.addActionListener(new ExitJMenuItemClickHandler());
    }

    private class ExitJMenuItemClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
