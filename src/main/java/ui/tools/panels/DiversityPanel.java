package ui.tools.panels;

import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;

public class DiversityPanel extends BasicPanel {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private static final Color DIVERSITY_COLOR = new Color(255, 200, 0);
    private static final String TITLE = "Diversity Indices";
    private static final String[] DIVERSITY_INDICES = {
            "Total Abundance",
            "Taxon Number",
            "Brillouin's diversity",
            "Brillouin's max diversity",
            "Brillouin's min diversity",
            "Brillouin's evenness",
            "Brillouin's rel. evenness",
            "Heip's evenness",
            "Hill's evenness",
            "Hill's diversity (N1)",
            "Hill's N2 Index",
            "Margalef's richness",
            "Menhinick's Index",
            "Shannon's diversity",
            "Simpson's dominance"
    };


    public DiversityPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        basicColor = DIVERSITY_COLOR;
        title = TITLE;
    }

    @Override
    protected void customizePanel(JComponent panel) {
        panel.setLayout(new GridLayout(5, DIVERSITY_INDICES.length/5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(DIVERSITY_COLOR);
    }

    @Override
    protected void createPanel(JComponent parent) {
        panel = new JPanel();
        customizePanel(panel);
        addTools(panel);
    }

    @Override
    protected void addTools(JComponent parent) {
        for (String name : DIVERSITY_INDICES) {
            BasicCheckBox box = new BasicCheckBox(app, parent, DIVERSITY_COLOR, name);
            checkboxesList.add(box);
        }
    }


}
