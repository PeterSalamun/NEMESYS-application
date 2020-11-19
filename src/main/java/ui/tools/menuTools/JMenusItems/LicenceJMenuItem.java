package ui.tools.menuTools.JMenusItems;

import model.callsAndExceptions.exceptions.IOExceptionCall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class LicenceJMenuItem extends BasicJMenuItem {

    private final String titleText = "Licence";
    private final String licence = "MIT License\n" +
            "\n" +
            "Copyright (c) [2020] [Peter Salamun]\n" +
            "\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n" +
            "\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE.";

    //Constructor
    public LicenceJMenuItem(JMenu parent, String name) {
        super(parent, name);
        title = titleText;
        message = licence;
    }

    //MODIFIES: JMenuItem
    //EFFECTS: adds Listener to JMenuItem
    @Override
    protected void addListener() {
        menuItem.addActionListener(new LicenceJMenuItemClickHandler());
    }

    private class LicenceJMenuItemClickHandler implements ActionListener {

        //REQUIRES: ActionEvent - click to JMenuItem
        //EFFECTS: displayed JOptionPane.showConfirmDialog() with licence parameter
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showMessageDialog(null, message, getTitle(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}


