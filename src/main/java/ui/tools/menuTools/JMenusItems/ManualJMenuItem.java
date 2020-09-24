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

    String manualPath = "/Manual NEMESYS.jpg";
    JLabel label;
    JPanel panel;
    int width = 1200;
    int height = 640;


    public ManualJMenuItem(JMenu parent, String name) {
        super(parent, name);
        initialize();
    }

    private void initialize() {
        label = new JLabel();
        panel = new JPanel();
        customize();
    }

    private void customize() {
        panel.setSize(new Dimension(width, height));
        panel.setLayout(new BorderLayout());
    }

    private void createJPanelWithManual() {

        URL imageURL = getClass().getResource(manualPath);

        Image image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            new IOExceptionCall().getCall();
        }

        Image image2 = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image2);

        label.setIcon(imageIcon);
        panel.add(label, BorderLayout.CENTER);

    }

    @Override
    protected void addListener() {
        menuItem.addActionListener(new ManualJMenuItemClickHandler());
    }

    private class ManualJMenuItemClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            createJPanelWithManual();
            String title = getTitle();

            JFrame frame = new JFrame();

            frame.setTitle(title);
            frame.setResizable(false);
            frame.setPreferredSize(new Dimension(width+15, height+36));
            frame.setVisible(true);

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.pack();
        }
    }
}
