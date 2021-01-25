package ui.tools_tests.buttons_tests;

import model.computing.data.NematodesDatabase;
import model.fileProcessing.fileManagement.OpenDatabase;
import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.buttons.Button;
import ui.tools.buttons.CalculateButton;
import ui.tools.buttons.OpenDatabaseButton;
import ui.tools.buttons.OpenSourceDataButton;
import ui.tools.panes.BasicPane;
import ui.tools.panes.TextPane.DatabasePathPane;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class OpenSourceDataButtonTest {

    private OpenSourceDataButton testT;
    private AppInterface app;
    private JComponent parent;
    private int x, y, h, w;
    private int[] insets;

    private HashMap<String, NematodesDatabase> testMap;

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

        testMap = new HashMap<>();
        testMap.put(d1.getTaxaName(), d1);
        testMap.put(d2.getTaxaName(), d2);
        testMap.put(d3.getTaxaName(), d3);


    }

    @Test
    public void testAddToParent() {
        assertEquals(parent.getComponents().length, 0);
        testT = new OpenSourceDataButton(app, parent);
        assertEquals(parent.getComponents().length, 1);
    }

    @Test
    public void testSwitchOnOffButton() {
        testT = new OpenSourceDataButton(app, parent);
        assertTrue(testT.isActive());
        testT.switchOffButton();
        assertFalse(testT.isActive());
        testT.switchOnButton();
        assertTrue(testT.isActive());
    }

    @Test
    public void testActivateDeactiveButton() {
        testT = new OpenSourceDataButton(app, parent);
        assertTrue(testT.isActive());
        testT.deactivate();
        assertFalse(testT.isActive());
        testT.activate();
        assertTrue(testT.isActive());
    }

    @Test
    public void testSetSpecificGrid() {
        testT = new OpenSourceDataButton(app, parent);
        assertEquals(testT.setSpecificGrid(x, y, h, w, insets).getClass(), GridBagConstraints.class);
    }

    @Test
    public void testClickOpenDatabaseButton() {
        CalculateButton cal = null;

        for (Button butt : app.getButtonsList())
            if (butt instanceof CalculateButton)
                cal = (CalculateButton) butt;
            
        testT = new OpenSourceDataButton(app, parent);
        String text = getText();
        assertEquals(text, "");

        //Upload of data file only diversity
        testT.switchOnButton();
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS demo\\Demo datasets\\NEMESYS Demo Animals.xlsx");
        assertTrue(cal.isActive());


        //Upload of data file only nematodes with default database
        app.setDiversityOn(false);
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS demo\\Demo datasets\\NEMESYS Demo Nematodes.xlsx");
        assertTrue(cal.isActive());

        //Upload of data file only nematodes with custom database
        app.setDatabaseMap(testMap);
        app.setDiversityOn(false);
        testT.getButton().doClick();
        text = getText();
        assertEquals(text, "C:\\Users\\Peter\\Desktop\\NEMESYS Demo.xlsx");
        assertTrue(cal.isActive());
        assertFalse(app.getDatabaseMap().isEmpty());


    }

    private String getText() {
        for (BasicPane pane : app.getPanesList())
            if (pane instanceof SourceDataPathPane)
                return pane.getFilePathString().trim();

        return null;
    }
}
