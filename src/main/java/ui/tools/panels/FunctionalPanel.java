package ui.tools.panels;

import ui.AppInterface;

import javax.swing.*;
import java.awt.*;

public class FunctionalPanel extends BasicPanel {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final Color FUNCTIONAL_COLOR = new Color(0, 222, 67);
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

    //Constructor
    public FunctionalPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        basicColor = FUNCTIONAL_COLOR;
        title = TITLE;
        createPanel(parent, FUNC_INDICES, basicColor);
        switchOff();
    }

    //REQUIRES: JPanel
    //MODIFIES: JPanel, this
    //EFFECTS: setting different attributes of JPanel
    @Override
    protected void customizePanel(JComponent panel) {
        panel.setLayout(new GridLayout(5, FUNC_INDICES.length/5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(basicColor);

    }
}

