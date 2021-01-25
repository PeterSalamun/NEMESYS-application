package ui.tools_tests.radioButtons_tests;

import model.fileProcessing.fileManagement.OpenDatabase;
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

public class DiversityRadioButtonTest {

    DiversityRadioButton testRadio;
    AppInterface app;
    JPanel parent;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testRadio = new DiversityRadioButton(app, parent);
    }

    @Test
    public void testGetName() {
        assertEquals(testRadio.getButtonName(), "Any organisms");
    }

    @Test
    public void testRadioButtonSelected() {
        assertTrue(testRadio.getRadioButton().isSelected());
    }

    @Test
    public void testEverythingIsOff() {
        ArrayList<BasicRadioButton> butt = app.getAllVSNematodesPanel().getRadioButtons();
        DiversityRadioButton divB = (DiversityRadioButton) butt.get(0);

        assertTrue(divB.isActive());
        butt.get(1).getRadioButton().doClick();
        divB.getRadioButton().doClick();

        testIsNematodesOnlyRadioButtonOff(butt);
        testIsDatabaseButtonOff();
        testAreFunctionalAndFootprintsPanelsOff();
    }

    private void testIsNematodesOnlyRadioButtonOff( ArrayList<BasicRadioButton> butt) {
        NematodesOnlyRadioButton nemB = (NematodesOnlyRadioButton) butt.get(1);

        assertFalse(nemB.getRadioButton().isSelected());
        assertFalse(nemB.isActive());
    }

    private void testIsDatabaseButtonOff() {
        ArrayList<Button> buttons = app.getButtonsList();
        OpenDatabaseButton dataB = null;

        for(Button b: buttons) {
            if(b instanceof OpenDatabaseButton)
                dataB = (OpenDatabaseButton) b;
        }

        assertFalse(dataB.isActive());
    }

    private void testAreFunctionalAndFootprintsPanelsOff() {
        OptionsPane optP = app.getOptionsPane();
        assertTrue(optP.getDiversityPanel().isActive());
        assertFalse(optP.getFunctionalPanel().isActive());
        assertFalse(optP.getFootprintsPanel().isActive());
    }

}
