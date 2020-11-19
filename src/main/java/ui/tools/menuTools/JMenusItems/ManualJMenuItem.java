package ui.tools.menuTools.JMenusItems;

import model.callsAndExceptions.exceptions.IOExceptionCall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class ManualJMenuItem extends BasicJMenuItem {

    private final String manualPath = "/Manual NEMESYS.jpg";
    private final String titleText = "Manual";
    private JLabel label;
    private JPanel panel;
    private int width = 1200;
    private int height = 640;

    //Constructor
    public ManualJMenuItem(JMenu parent, String name) {
        super(parent, name);
        title = titleText;
        initialize();
        customize();
    }

    //EFFECTS: initialize JLabel and JPanel
    private void initialize() {
        label = new JLabel();
        panel = new JPanel();
    }

    //MODIFIES: JPanel panel
    //EFFECTS: sets JPanel attributes (size,
    private void customize() {
        panel.setSize(new Dimension(width, height));
        panel.setLayout(new BorderLayout());
    }

    //MODIFIES: JPanel, JLabel
    //EFFECTS: adding manual.jpg to JPanel
    private void createJPanelWithManual() {
        URL imageURL = getClass().getResource(manualPath);

        Image image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            new IOExceptionCall().getCall();
        }

        //scaling the image containing manual.jpg to width and height of panel and converting it to ImageIcon for its
        //setting to JLabel
        Image image2 = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image2);

        label.setIcon(imageIcon);
        panel.add(label, BorderLayout.CENTER);

    }

    //getters
    public String getManualPath() {
        return manualPath;
    }

    public JPanel getJPanel() {
        return panel;
    }

    public JLabel getJLabel() {
        return label;
    }

    public int getActualWidth() {
        return width;
    }

    public int getActualHeight() {
        return height;
    }

    //MODIFIES: JMenuItem
    //EFFECTS: adds Listener to JMenuItem
    @Override
    protected void addListener() {
        menuItem.addActionListener(new ManualJMenuItemClickHandler());
    }

    private class ManualJMenuItemClickHandler implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECTS: calls createManualJPanel() method
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            createManualJPanel();
        }

        //EFFECTS: creates and customize (customizeFrame()) new JFrame, where the manual.jpg file will be displayed
        private void createManualJPanel() {
            createJPanelWithManual();
            JFrame frame = new JFrame();
            customizeFrame(frame);
        }

        //MODIFIES: JFrame
        //EFFECTS: sets various attributes of JFrame
        private void customizeFrame(JFrame frame) {
            frame.setTitle(getTitle());
            frame.setResizable(false);
            frame.setPreferredSize(new Dimension(width + 15, height + 36));
            frame.setVisible(true);

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
        }

    }
}
