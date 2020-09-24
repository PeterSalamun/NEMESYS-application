package ui.tools.panels;

import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;

public class FunctionalPanel extends BasicPanel {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final Color FUNCTIONALCOLOR = new Color(0, 222, 67);
    private static final String TITLE = "Functional Indices";
    private static final String[] FUNC_INDICES = {
            "Maturity Index",
            "Maturity Index 2-5",
            "Plant Parasite Index",
            "sum Maturity Index",
            "sum Maturity Index 2-5",
            "Structure Index",
            "Enrichment Index",
            "Channel Index",
            "Basal Index"};

    public FunctionalPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        basicColor = FUNCTIONALCOLOR;
        title = TITLE;
    }

    @Override
    protected void customizePanel(JComponent panel) {
        panel.setLayout(new GridLayout(5, FUNC_INDICES.length/5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(FUNCTIONALCOLOR);

    }

    @Override
    protected void createPanel(JComponent parent) {
        panel = new JPanel();
        customizePanel(panel);
        addTools(panel);
    }

    @Override
    protected void addTools(JComponent parent) {
        for (String name : FUNC_INDICES) {
            BasicCheckBox box = new BasicCheckBox(app, parent, FUNCTIONALCOLOR, name);
            checkboxesList.add(box);
        }
    }
}

