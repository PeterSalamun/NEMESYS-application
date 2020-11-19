package ui.tools.menuTools.JMenusItems;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditJMenuItem extends BasicJMenuItem {

    private final String titleText = "Credit";
    private final String messageText = "In case of any bugs or any questions please contact us at " +
            "salamun@saske.sk";

    //Constructor
    public CreditJMenuItem(JMenu parent, String name) {
        super(parent, name);
        message = messageText;
        title = titleText;
    }

    //MODIFIES: JMenuItem
    //EFFECTS: adds Listener to JMenuItem
    @Override
    protected void addListener() {
        menuItem.addActionListener(new CreditJMenuItemClickHandler());
    }

    private class CreditJMenuItemClickHandler implements ActionListener {

        //REQUIRES: ActionEvent - click to JMenuItem
        //EFFECTS: displayed JOptionPane.showConfirmDialog() with message parameter
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showConfirmDialog(null, message, getTitle(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
