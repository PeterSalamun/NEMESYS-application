package ui;

import org.junit.Before;
import org.junit.Test;
import ui.tools.panels.AllVSNematodesPanel;
import ui.tools.panes.TabbedPanes.OptionsPane;

import static org.junit.Assert.*;

public class AppInterfaceTest {

    AppInterface app;

    @Before
    public void setUp() {
        app = new AppInterface();
    }

    @Test
    public void testGetters() {
        assertFalse(app.getButtonsList().isEmpty());
        assertEquals(app.getButtonsList().size(), 6);

        assertTrue(app.getAllVSNematodesPanel() instanceof AllVSNematodesPanel);

        assertFalse(app.getPanesList().isEmpty());
        assertEquals(app.getPanesList().size(), 3);

        assertTrue(app.getOptionsPane() instanceof OptionsPane);
    }

}
