package ui.tools_tests.menuTools_tests.JMenusItems_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenusItems.DownloadDatabaseJMenuItem;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class DownloadDatabaseJMenuItemTest {

    private JMenu parent;
    private String name;
    private String path;
    private DownloadDatabaseJMenuItem testDownload;

    @Before
    public void setUp() {
        parent = new JMenu();
        name = "DownloadDatabaseTest";
        path = "/fullDatabase.xlsx";
        testDownload = new DownloadDatabaseJMenuItem(parent, name);
    }


    @Test
    public void testGetMenuItem() {
        assertNotNull(testDownload.getMenuItem());
        assertTrue(testDownload.getMenuItem() instanceof JMenuItem);
    }

    @Test
    public void testGetPath() {
        assertEquals(testDownload.getDefaultDatabasePath(), path);
    }

    @Test
    public void testGetName() {
        assertEquals(testDownload.getItemName(), name);
    }

    @Test
    public void testGetMessage() {
        assertNull(testDownload.getMessage());
    }

}
