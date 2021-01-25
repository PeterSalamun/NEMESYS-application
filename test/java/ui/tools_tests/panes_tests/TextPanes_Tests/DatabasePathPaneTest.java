package ui.tools_tests.panes_tests.TextPanes_Tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.panes.TextPane.DatabasePathPane;

import javax.swing.*;

import static org.junit.Assert.*;

public class DatabasePathPaneTest {

    private DatabasePathPane testPane;
    private AppInterface app;
    private JPanel parent;
    private String path;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testPane = new DatabasePathPane(app, parent);
    }

    @Test
    public void testSetFilePathString() {
        assertEquals(testPane.getTextPane().getText(), "   Default database");
        path = "ahoj";
        testPane.setFilePathString(path);
        assertEquals(testPane.getTextPane().getText(), path);
    }

    @Test
    public void testSwitchOnOff() {
        assertFalse(testPane.getTextPane().isEnabled());
        testPane.switchOnOff(true);
        assertTrue(testPane.getTextPane().isEnabled());
    }

    @Test
    public void testGetFilePathString() {
        assertEquals(testPane.getFilePathString(), "   Default database");
        path = "ahoj";
        testPane.setFilePathString(path);
        assertEquals(testPane.getFilePathString(), path);
    }
}
