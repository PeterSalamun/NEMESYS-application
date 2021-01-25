package ui.tools_tests.menuTools_tests.JMenusItems_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenusItems.ManualJMenuItem;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ManualJMenuItemTest {

    private JMenu parent;
    private String name;
    private String title;
    private String path;
    private int height;
    private int width;
    private ManualJMenuItem testManual;

    @Before
    public void setUp() {
        parent = new JMenu();
        name = "ManualTest";
        title = "Manual";
        path = "/Manual NEMESYS.jpg";
        height = 640;
        width = 1200;
        testManual = new ManualJMenuItem(parent, name);
    }


    @Test
    public void testGetMenuItem() {
        assertNotNull(testManual.getMenuItem());
        assertTrue(testManual.getMenuItem() instanceof JMenuItem);
    }

    @Test
    public void testGetTitle() {
        assertEquals(testManual.getTitle(), title);
    }

    @Test
    public void testGetManualPath() {
        assertEquals(testManual.getManualPath(), path);
    }

    @Test
    public void testGetHeight() {
        assertEquals(testManual.getActualHeight(), height);
    }
    @Test
    public void testGetWidth() {
        assertEquals(testManual.getActualWidth(), width);
    }

    @Test
    public void testJPanel() {
        assertNotNull(testManual.getJPanel());
    }

    @Test
    public void testJLabel() {
        assertNotNull(testManual.getJLabel());
    }

    @Test
    public void testGetName() {
        assertEquals(testManual.getItemName(), name);
    }
}
