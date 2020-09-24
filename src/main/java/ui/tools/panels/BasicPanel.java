package ui.tools.panels;

import ui.AppInterface;
import ui.tools.Tool;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class BasicPanel extends Tool {

    JPanel panel;
    Color basicColor;
    String title;
    ArrayList<BasicCheckBox> checkboxesList;

    public BasicPanel(AppInterface appInterface, JComponent parent, int GRIDX, int GRIDY, int GRIDHEIGHT, int GRIDWIDTH, int[] INSETS) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        checkboxesList = new ArrayList<>();
        createPanel(parent);
        addToParent(parent, panel);
    }

    protected void customizePanel(JComponent panel){}

    protected void customizePanel(JComponent panel, JTabbedPane parent){}

    public void setPanel() {}

    public Color getActualColor() {return basicColor;}

    protected abstract void createPanel(JComponent parent);

    protected abstract void addTools(JComponent parent);

    protected JCheckBox addCheckBox(){return null;}

    public String getTitle() {return title;}

    public ArrayList<BasicCheckBox> getCheckBoxesList() {return checkboxesList;}

    public ArrayList<Boolean> getSelectedCheckBoxes() {
        ArrayList<Boolean> list = new ArrayList<>();

        for(BasicCheckBox box: checkboxesList) {
            if(box.isActive())
                list.add(true);
            else
                list.add(false);
        }

        return list;
    }

    public void switchOff() {
        deactivate();
        panel.setEnabled(isActive());
    }

    public void switchOn() {
        activate();
        panel.setEnabled(isActive());
    }
}
