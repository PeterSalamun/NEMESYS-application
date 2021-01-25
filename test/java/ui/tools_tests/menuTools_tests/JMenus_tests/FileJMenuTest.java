package ui.tools_tests.menuTools_tests.JMenus_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenus.FileJMenu;

import javax.swing.*;

import static org.junit.Assert.*;

public class FileJMenuTest {

    private JMenuBar menuBar;
    private FileJMenu testMenu;
    private String name;

    @Before
    public void setUp() {
        menuBar = new JMenuBar();
        name = "Test";
        testMenu = new FileJMenu(menuBar, name);
    }

    @Test
    public void testGetName() {
        assertEquals(testMenu.getName(), "Test");
    }

    @Test
    public void testGetUpName() {
        assertEquals(testMenu.getDatabaseName(), "Download database");
    }

    @Test
    public void testGetDownName() {
        assertEquals(testMenu.getExitName(), "Exit");
    }

    @Test
    public void testGetMenu() {
        assertNotNull(testMenu.getMenu());
        assertTrue(testMenu.getMenu() instanceof JMenu);
    }
}
