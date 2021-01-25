package ui.tools_tests.panels_tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.panels.AllVSNematodesPanel;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class AllVSNematodesPanelTest {
    private AllVSNematodesPanel testPanel;
    private AppInterface app;
    private JPanel parent;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testPanel = new AllVSNematodesPanel(app, parent);
    }

    @Test
    public void testGetTitle() {
        assertNull(testPanel.getTitle());
    }

    @Test
    public void testGetRadioButtons() {
        assertNotNull(testPanel.getRadioButtons());
        assertEquals(testPanel.getRadioButtons().size(), 2);
        assertTrue(testPanel.getRadioButtons().get(0).isActive());
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
