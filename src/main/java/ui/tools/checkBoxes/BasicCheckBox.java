package ui.tools.checkBoxes;

import ui.AppInterface;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCheckBox extends Tool {

    JCheckBox checkBox;
    Color color;
    String name;
    final Font checkFont = new Font("Arial", Font.BOLD, 12);

    public BasicCheckBox(AppInterface appInterface, JComponent parent, Color color, String name) {
        super(appInterface, parent, 0, 0, 1, 1, new int[]{0, 0, 0, 0});
        this.color = color;
        this.name = name;
        createCheckBox(parent);
        addToParent(parent, checkBox);
        addListener();
    }

    private void createCheckBox(JComponent parent) {
        checkBox = new JCheckBox();
        customizeCheckBox(checkBox, color, name);
    }

    private void customizeCheckBox(JCheckBox box, Color color, String name) {
        box.setBackground(color);
        box.setText(name);
        box.setFont(checkFont);
    }

    public String getName() {
        return name;
    }

    public void boxOnOffSwitching(boolean value) {
        if (value) {
            checkBox.setSelected(value);
            activate();
        } else {
            checkBox.setSelected(false);
            deactivate();
        }
    }

    @Override
    protected void addListener() {
        checkBox.addActionListener(new BasicCheckBoxClickHandler());
    }

    private class BasicCheckBoxClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if (isActive()) {
                boxOnOffSwitching(false);
            } else {
                boxOnOffSwitching(true);

            }
        }
    }
}
