package ui.tools_tests.radioButtons_tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.buttons.Button;
import ui.tools.buttons.OpenDatabaseButton;
import ui.tools.panes.TabbedPanes.OptionsPane;
import ui.tools.radioButtons.BasicRadioButton;
import ui.tools.radioButtons.DiversityRadioButton;
import ui.tools.radioButtons.NematodesOnlyRadioButton;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class NematodesOnlyRadioButtonTest {

    NematodesOnlyRadioButton testRadio;
    AppInterface app;
    JPanel parent;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testRadio = new NematodesOnlyRadioButton(app, parent);
    }

    @Test
    public void testGetName() {
        assertEquals(testRadio.getButtonName(), "Nematodes only");
    }

    @Test
    public void testRadioButtonSelected() {
        assertFalse(testRadio.getRadioButton().isSelected());
    }

    @Test
    public void testEverythingIsOn() {
        ArrayList<BasicRadioButton> butt = app.getAllVSNematodesPanel().getRadioButtons();
        DiversityRadioButton divB = (DiversityRadioButton) butt.get(0);

        assertTrue(divB.isActive());


        testIsNematodesOnlyRadioButtonOn(butt);
        testIsDatabaseButtonOn();
        testAreFunctionalAndFootprintsPanelsOn();
    }

    private void testIsNematodesOnlyRadioButtonOn( ArrayList<BasicRadioButton> butt) {
        NematodesOnlyRadioButton nemB = (NematodesOnlyRadioButton) butt.get(1);

        assertFalse(nemB.getRadioButton().isSelected());
        assertFalse(nemB.isActive());

        nemB.getRadioButton().doClick();
        assertTrue(nemB.getRadioButton().isSelected());
        assertTrue(nemB.isActive());
    }

    private void testIsDatabaseButtonOn() {
        ArrayList<Button> buttons = app.getButtonsList();
        OpenDatabaseButton dataB = null;

        for(Button b: buttons) {
            if(b instanceof OpenDatabaseButton)
                dataB = (OpenDatabaseButton) b;
        }

        assertTrue(dataB.isActive());
    }

    private void testAreFunctionalAndFootprintsPanelsOn() {
        OptionsPane optP = app.getOptionsPane();
        assertTrue(optP.isActive());
        assertTrue(optP.getDiversityPanel().isActive());
        assertTrue(optP.getFunctionalPanel().isActive());
        assertTrue(optP.getFootprintsPanel().isActive());
    }
}
