package ui.tools_tests.menuTools_tests.JMenus_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenus.FileJMenu;
import ui.tools.menuTools.JMenus.HelpJMenu;

import javax.swing.*;

import static org.junit.Assert.*;

public class HelpJMenuTest {

    private JMenuBar menuBar;
    private HelpJMenu testMenu;
    private String name;

    @Before
    public void setUp() {
        menuBar = new JMenuBar();
        name = "Help";
        testMenu = new HelpJMenu(menuBar, name);
    }

    @Test
    public void testGetName() {
        assertEquals(testMenu.getName(), "Help");
    }

    @Test
    public void testGetCreditName() {
        assertEquals(testMenu.getCreditName(), "Credit");
    }

    @Test
    public void testGetManualName() {
        assertEquals(testMenu.getManualName(), "Manual");
    }

    @Test
    public void testGetLicenceName() {
        assertEquals(testMenu.getLicenceName(), "Licence");
    }

    @Test
    public void testGetMenu() {
        assertNotNull(testMenu.getMenu());
        assertTrue(testMenu.getMenu() instanceof JMenu);
    }
}
