package ui.tools.panels;

import ui.AppInterface;

import javax.swing.*;
import java.awt.*;

public class FootprintsPanel extends BasicPanel {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final Color FOOT_COLOR = new Color(0, 188, 235);
    private static final String TITLE = "Metabolic Footprints";
    private static final String[] FOOT_INDICES = {
            "Structure footprints",
            "Enrichment footprints",
            "Functional footprints",
            "Composite footprints",
            "Herbivore footprints",
            "Bacterivore footprints",
            "Fungivore footprints",
            "Omnivore footprints",
            "Predators footprints"
    };

    //Constructor
    public FootprintsPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        basicColor = FOOT_COLOR;
        title = TITLE;
        createPanel(parent, FOOT_INDICES, basicColor);
        switchOff();
    }

    //REQUIRES: JPanel
    //MODIFIES: JPanel, this
    //EFFECTS: setting different attributes of JPanel
    @Override
    protected void customizePanel(JComponent panel) {
        panel.setLayout(new GridLayout(4, FOOT_INDICES.length / 4));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(basicColor);
    }
}
