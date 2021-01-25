package ui.tools_tests.buttons_tests;

import model.computing.data.NematodesDatabase;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.buttons.OpenDatabaseButton;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.DatabasePathPane;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class OpenDatabaseButtonTest {

    private OpenDatabaseButton testT;
    private AppInterface app;
    private JComponent parent;
    private int x, y, h, w;
    private int[] insets;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();

        x = 10;
        y = 10;
        h = 10;
        w = 10;
        insets = new int[]{10, 10, 10, 10};

        NematodesDatabase d1 = new NematodesDatabase("Rhabditis", "B1", 0.501);
        NematodesDatabase d2 = new NematodesDatabase("Acrobeloides", "B2", 0.978);
        NematodesDatabase d3 = new NematodesDatabase("Dorylaimus", "O5", 0.129);

        HashMap<String, NematodesDatabase> testMap = new HashMap<>();
        testMap.put(d1.getTaxaName(), d1);
        testMap.put(d2.getTaxaName(), d2);
        testMap.put(d3.getTaxaName(), d3);

        app.setDatabaseMap(testMap);
    }

    @Test
    public void testAddToParent() {
        assertEquals(parent.getComponents().length, 0);
        testT = new OpenDatabaseButton(app, parent);
        assertEquals(parent.getComponents().length, 1);
    }

    @Test
    public void testSwitchOnOffButton() {
        testT = new OpenDatabaseButton(app, parent);
        assertFalse(testT.isActive());
        testT.switchOnButton();
        assertTrue(testT.isActive());
        testT.switchOffButton();
        assertFalse(testT.isActive());
    }

    @Test
    public void testActivateDeactiveButton() {
        testT = new OpenDatabaseButton(app, parent);
        assertFalse(testT.isActive());
        testT.activate();
        assertTrue(testT.isActive());
        testT.deactivate();
        assertFalse(testT.isActive());
    }

    @Test
    public void testSetSpecificGrid() {
        testT = new OpenDatabaseButton(app, parent);
        assertEquals(testT.setSpecificGrid(x, y, h, w, insets).getClass(), GridBagConstraints.class);
    }

    @Test
    public void testClickOpenDatabaseButton() {
        testT = new OpenDatabaseButton(app, parent);

        String text = getText();
        assertEquals(text, "Default database");

        //Upload of custom database
        app.setDiversityOn(false);
        testT.switchOnButton();
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS demo\\Demo datasets\\NEMESYS Demo Nematodes Database.xlsx");

        //Re-upload of default database after custom database
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "Default database");

        //Re-upload of custom database after default database
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS demo\\Demo datasets\\NEMESYS Demo Nematodes Database.xlsx");

        //Re-upload of custom database after custom database
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS demo\\Demo datasets\\NEMESYS Demo Nematodes Database.xlsx");
    }

    private String getText() {
        for (BasicPane pane : app.getPanesList())
            if (pane instanceof DatabasePathPane)
                return pane.getFilePathString().trim();

        return null;
    }
}
