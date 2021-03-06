package ui.tools_tests.buttons_tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.buttons.DeselectAllButton;
import ui.tools.buttons.ExitButton;
import ui.tools.buttons.SelectAllButton;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ExitButtonTest {
    private ExitButton testT;
    private AppInterface app;
    private JComponent parent;
    private int x, y, h, w;
    private int[] insets;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();

        x= 10;
        y= 10;
        h = 10;
        w = 10;
        insets = new int[] {10, 10, 10, 10};
    }

    @Test
    public void testAddToParent() {
        assertEquals(parent.getComponents().length, 0);
        testT = new ExitButton(app, parent);
        assertEquals(parent.getComponents().length, 1);
    }

    @Test
    public void testSwitchOnOffButton() {
        testT = new ExitButton(app, parent);
        assertTrue(testT.isActive());
        testT.switchOffButton();
        assertFalse(testT.isActive());
        testT.switchOnButton();
        assertTrue(testT.isActive());
    }

    @Test
    public void testActivateDeactiveButton() {
        testT = new ExitButton(app, parent);
        assertTrue(testT.isActive());
        testT.deactivate();
        assertFalse(testT.isActive());
        testT.activate();
        assertTrue(testT.isActive());
    }

    @Test
    public void testSetSpecificGrid() {
        testT = new ExitButton(app, parent);
        assertEquals(testT.setSpecificGrid(x, y, h, w, insets).getClass(), GridBagConstraints.class);
    }
}
