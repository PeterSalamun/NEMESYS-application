package ui.tools.menuTools.JMenusItems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditJMenuItem extends BasicJMenuItem {


    public CreditJMenuItem(JMenu parent, String name) {
        super(parent, name);
        message = "In case of any bugs or any questions please contact us at " +
                "salamun@saske.sk";
    }

    @Override
    protected void addListener() {
        menuItem.addActionListener(new CreditJMenuItemClickHandler());
    }

    private class CreditJMenuItemClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showConfirmDialog(null, message, getTitle(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
