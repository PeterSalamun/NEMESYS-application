package ui.tools_tests.menuTools_tests.JMenusItems_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenusItems.ExitJMenuItem;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ExitJMenuTest {

    private JMenu parent;
    private String name;
    private ExitJMenuItem testExit;

    @Before
    public void setUp() {
        parent = new JMenu();
        name = "ExitTest";
        testExit = new ExitJMenuItem(parent, name);
    }


    @Test
    public void testGetMenuItem() {
        assertNotNull(testExit.getMenuItem());
        assertTrue(testExit.getMenuItem() instanceof JMenuItem);
    }

    @Test
    public void testGetMessage() {
        assertNull(testExit.getMessage());
    }

    @Test
    public void testGetTitle() {
        assertNull(testExit.getTitle());
    }

    @Test
    public void testGetName() {
        assertEquals(testExit.getItemName(), name);
    }
}
