package ui.tools_tests.panels_tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.panels.DiversityPanel;
import ui.tools.panels.FunctionalPanel;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class DiversityPanelTest {

    private DiversityPanel testPanel;
    private AppInterface app;
    private JPanel parent;
    private int[] rgb;
    private String title;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        rgb = new int[]{255, 200, 0};
        title = "Diversity Indices";
        testPanel = new DiversityPanel(app, parent);
    }

    @Test
    public void testGetActualColor() {
        assertEquals(testPanel.getActualColor().getRed(), rgb[0]);
        assertEquals(testPanel.getActualColor().getGreen(), rgb[1]);
        assertEquals(testPanel.getActualColor().getBlue(), rgb[2]);
    }

    @Test
    public void testGetTitle() {
        assertEquals(testPanel.getTitle(), title);
    }

    @Test
    public void testGetChceckBoxesList() {
        assertNotNull(testPanel.getCheckBoxesList());
        assertEquals(testPanel.getCheckBoxesList().size(), 15);
    }

    @Test
    public void testSelectedCheckBoxes() {
        assertEquals(testPanel.getSelectedCheckBoxes().size(), 15);

        for (int i = 0; i < testPanel.getCheckBoxesList().size(); i = i + 2) {
            testPanel.getCheckBoxesList().get(i).boxOnOffSwitching(true);
        }

        for (int i = 0; i < testPanel.getCheckBoxesList().size(); i++) {
            if (i % 2 == 0)
                assertTrue(testPanel.getSelectedCheckBoxes().get(i));
            else
                assertFalse(testPanel.getSelectedCheckBoxes().get(i));
        }
    }

    @Test
    public void switchOnOff() {
        assertTrue(testPanel.isActive());
        testPanel.switchOff();
        assertFalse(testPanel.isActive());
        testPanel.switchOn();
        assertTrue(testPanel.isActive());

    }

}
