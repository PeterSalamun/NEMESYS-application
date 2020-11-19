package ui.tools.checkBoxes;

import ui.AppInterface;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCheckBox extends Tool {

    private JCheckBox checkBox;
    private final Color color;
    private final String name;
    private static final int[] INSETS = new int[]{0, 0, 0, 0};
    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    //Constructor
    public BasicCheckBox(AppInterface appInterface, JComponent parent, Color color, String name) {
        super(appInterface, parent, GRIDX, GRIDY, HEIGHT, WIDTH, INSETS);
        this.color = color;
        this.name = name;
        createCheckBox();
        addToParent(parent, checkBox);
        addListener();
    }

    //EFFECTS: creates (customizeCheckBox() method) JCheckBox
    private void createCheckBox() {
        checkBox = new JCheckBox();
        customizeCheckBox(checkBox, color, name);
    }

    //REQUIRES: JCheckBox, Color, String
    //MODIFIES: JCheckBox
    //EFFECTS: customize JCHeckBox by setting various attributes
    private void customizeCheckBox(JCheckBox box, Color color, String name) {
        Font checkFont = new Font("Arial", Font.BOLD, 12);
        box.setBackground(color);
        box.setText(name);
        box.setFont(checkFont);
    }

    //REQUIRES: boolean
    //MODIFIES: JCheckBox
    //EFFECTS: switching JCheckBox.setSelected to true or false depending on value parameter, and
    // activating/deactivating JCheckBox
    public void boxOnOffSwitching(boolean value) {
        checkBox.setSelected(value);

        if (value)
            activate();
        else
            deactivate();
    }

    //getters
    public String getName() {
        return name;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    //MODIFIES: JCheckBox
    //EFFECTS: adds Listener to JCheckBox
    @Override
    protected void addListener() {
        checkBox.addActionListener(new BasicCheckBoxClickHandler());
    }

    private class BasicCheckBoxClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        //MODIFIES: JCheckBox
        //EFFECTS: see boxOnOffSwitching(boolean value) method
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boxOnOffSwitching(!isActive());
        }
    }
}
