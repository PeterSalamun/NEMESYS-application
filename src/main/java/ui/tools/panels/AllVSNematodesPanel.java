package ui.tools.panels;

import ui.AppInterface;
import ui.tools.radioButtons.BasicRadioButton;
import ui.tools.radioButtons.DiversityRadioButton;
import ui.tools.radioButtons.NematodesOnlyRadioButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllVSNematodesPanel extends BasicPanel {

    private static final int GRIDX = 0;
    private static final int GRIDY = 0;
    private static final int GRIDWIDTH = 4;
    private static final int GRIDHEIGHT = 1;
    private static final int[] INSETS = {5, 5, 5, 5};
    private ArrayList<BasicRadioButton> radioButtons;

    public AllVSNematodesPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
    }

    @Override
    protected void customizePanel(JComponent panel) {
        panel.setPreferredSize(new Dimension(510, 50));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("CALCULATION MODE" ));

    }

    @Override
    protected void createPanel(JComponent parent) {
        panel = new JPanel();
        radioButtons = new ArrayList<>();
        customizePanel(panel);
        addTools(panel);
    }

    @Override
    protected void addTools(JComponent parent) {
        DiversityRadioButton diversityButton = new DiversityRadioButton(app, parent);
        radioButtons.add(diversityButton);

        NematodesOnlyRadioButton nematodesButton = new NematodesOnlyRadioButton(app, parent);
        radioButtons.add(nematodesButton);
    }

    public ArrayList<BasicRadioButton> getRadioButtons() {return radioButtons;}

}
