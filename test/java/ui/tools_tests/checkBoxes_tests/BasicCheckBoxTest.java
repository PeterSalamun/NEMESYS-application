package ui.tools_tests.checkBoxes_tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.checkBoxes.BasicCheckBox;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class BasicCheckBoxTest {

    private BasicCheckBox testBox;
    private AppInterface app;
    private JPanel panel;
    private Color color;
    private String name;

    @Before
    public void setUp() {
        app = new AppInterface();
        panel = new JPanel();
        color = Color.BLUE;
        name = "testBox";

        testBox = new BasicCheckBox(app, panel, color, name);
    }

    @Test
    public void testGetName() {
        assertEquals(testBox.getName(), name);
    }

    @Test
    public void testGetCheckBox() {
        assertNotNull(testBox.getCheckBox());
    }

    @Test
    public void testSwitchingBoxOnOff() {
        assertFalse(testBox.getCheckBox().isSelected());
        assertFalse(testBox.isActive());
        testBox.boxOnOffSwitching(true);
        assertTrue(testBox.getCheckBox().isSelected());
        assertTrue(testBox.isActive());
        testBox.boxOnOffSwitching(false);
        assertFalse(testBox.getCheckBox().isSelected());
        assertFalse(testBox.isActive());
    }
}
