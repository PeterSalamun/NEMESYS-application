package ui.tools_tests.panes_tests.TabbedPanes_Tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.panels.DiversityPanel;
import ui.tools.panels.FootprintsPanel;
import ui.tools.panels.FunctionalPanel;
import ui.tools.panes.TabbedPanes.OptionsPane;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionsPaneTest {

    private OptionsPane testPane;
    private AppInterface app;
    private JPanel parent;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testPane = new OptionsPane(app, parent);
    }

    @Test
    public void testGetOptionsPanels() {
        assertEquals(testPane.getOptionPanels().size(), 3);
    }

    @Test
    public void testGetDiversityPanel() {
        assertTrue(testPane.getOptionPanels().get(0) instanceof DiversityPanel);
    }
    @Test
    public void testGetFunctionalPanel() {
        assertTrue(testPane.getOptionPanels().get(1) instanceof FunctionalPanel);
    }
    @Test
    public void testGetFootprintsPanel() {
        assertTrue(testPane.getOptionPanels().get(2) instanceof FootprintsPanel);
    }

    @Test
    public void testGetActivePanel() {
        assertTrue(testPane.getActivePanel() instanceof DiversityPanel);
        testPane.getTabbedPane().setSelectedIndex(2);
        assertTrue(testPane.getActivePanel() instanceof FootprintsPanel);
    }
}
