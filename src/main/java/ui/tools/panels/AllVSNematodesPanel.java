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

    //Constructor
    public AllVSNematodesPanel(AppInterface appInterface, JComponent parent) {
        super(appInterface, parent, GRIDX, GRIDY, GRIDHEIGHT, GRIDWIDTH, INSETS);
        createPanel(parent);
        switchOn();
    }

    //REQUIRES: JPanel
    //MODIFIES: JPanel, this
    //EFFECTS: setting different attributes of JPanel
    @Override
    protected void customizePanel(JComponent panel) {
        panel.setPreferredSize(new Dimension(510, 50));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("CALCULATION MODE"));
    }

    //REQUIRES: OptionsPane parent
    //MODIFIES: this
    //EFFECTS: creating new JPanel for this, customizing the JPanel (customizePanel() method) and adding checkBoxes to
    // the JPanel (addTools())
    protected void createPanel(JComponent parent) {
        panel = new JPanel();
        customizePanel(panel);
        initializeFields();
        addTools(panel);
        addToParent(parent, panel);
    }

    //EFFECTS: initialize ArrayList<BasicRadioButtons>
    private void initializeFields() {
        radioButtons = new ArrayList<>();
    }

    //REQUIRES: JPanel
    //MODIFIES: JPanel
    //EFFECTS: adding two BasicRadioButtons to the JPanel parent and to the radioButtons arrayList
    protected void addTools(JComponent parent) {
        DiversityRadioButton diversityButton = new DiversityRadioButton(app, parent);
        radioButtons.add(diversityButton);

        NematodesOnlyRadioButton nematodesButton = new NematodesOnlyRadioButton(app, parent);
        radioButtons.add(nematodesButton);
    }

    //getters
    public ArrayList<BasicRadioButton> getRadioButtons() {
        return radioButtons;
    }

}
