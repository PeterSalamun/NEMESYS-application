package ui.tools_tests.panes_tests.TextPanes_Tests;

import org.junit.Before;
import org.junit.Test;
import ui.AppInterface;
import ui.tools.panes.TextPane.SourceDataPathPane;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class SourceDataPathPaneTests {

    private SourceDataPathPane testPane;
    private AppInterface app;
    private JPanel parent;
    private String path;

    @Before
    public void setUp() {
        app = new AppInterface();
        parent = new JPanel();
        testPane = new SourceDataPathPane(app, parent);
    }

    @Test
    public void testSetFilePathString() {
        testPane.setFilePathString(path);
        assertEquals(testPane.getTextPane().getText(), "");
        path = "ahoj";
        testPane.setFilePathString(path);
        assertEquals(testPane.getTextPane().getText(), path);
    }


    @Test
    public void testGetFilePathString() {
        assertEquals(testPane.getFilePathString(), "");
        path = "ahoj";
        testPane.setFilePathString(path);
        assertEquals(testPane.getFilePathString(), path);
    }
}
