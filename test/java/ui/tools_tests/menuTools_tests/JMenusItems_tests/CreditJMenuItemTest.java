package ui.tools_tests.menuTools_tests.JMenusItems_tests;

import org.junit.Before;
import org.junit.Test;
import ui.tools.menuTools.JMenusItems.CreditJMenuItem;

import javax.swing.*;

import static org.junit.Assert.*;

public class CreditJMenuItemTest {

    private JMenu parent;
    private String name;
    private String message;
    private String title;
    private CreditJMenuItem testCredit;

    @Before
    public void setUp() {
        parent = new JMenu();
        name = "CreditTest";
        message = "In case of any bugs or any questions please contact us at " +
        "salamun@saske.sk";
        title = "Credit";
        testCredit = new CreditJMenuItem(parent, name);
    }


    @Test
    public void testGetMenuItem() {
        assertNotNull(testCredit.getMenuItem());
        assertTrue(testCredit.getMenuItem() instanceof JMenuItem);
    }

    @Test
    public void testGetMessage() {
        assertEquals(testCredit.getMessage(), message);
    }

    @Test
    public void testGetTitle() {
        assertEquals(testCredit.getTitle(), title);
    }

    @Test
    public void testGetName() {
        assertEquals(testCredit.getItemName(), name);
    }
}
