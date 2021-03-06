package ui.tools.panes;

import ui.AppInterface;
import ui.tools.Tool;
import ui.tools.panels.BasicPanel;

import javax.swing.*;
import java.util.ArrayList;

public abstract class BasicPane extends Tool {

    protected JTabbedPane tabbedPane = null;
    protected JTextPane textPane = null;

    //Constructor
    public BasicPane(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        createPane(parent);
        //depending on pane (text or tabbed) created in createPane(parent) method, the correct option is choose
        if(tabbedPane != null)
            addToParent(parent, tabbedPane);
        else
            addToParent(parent, textPane);
    }


    //abstract or override methods
    protected abstract void createPane(JComponent parent);

    protected abstract void customizePane(JComponent pane);

    protected void addPanels(JComponent parent) {
    }

    public void switchOnOff(boolean bValue){}


    //setters
    public void setFilePathString(String path){
        if(tabbedPane == null)
            textPane.setText(path);
    }


    //getters
    public BasicPanel getActivePanel() {return null;}

    public ArrayList<BasicPanel> getOptionPanels() {
        return null;
    }

    public String getFilePathString() {
        return textPane.getText();
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
