package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame mainFrame;
    private JPanel panel, panel5, panel2, panel3, panel4;
    private PlayList playListPanel;
    private static final int HEIGHT = 920, WIDTH = 1500;

    public MainFrame() {
        //set JFrame's default properties
        this.mainFrame = new JFrame("Spotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(WIDTH, HEIGHT);

        //set Main Panel default properties
        panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);

        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        playListPanel = new PlayList();

        panel2.setOpaque(true);
        panel2.setBackground(new Color(30, 30, 30));

        panel3.setOpaque(true);
        panel3.setBackground(Color.CYAN);

        panel4.setOpaque(true);
        panel4.setBackground(Color.LIGHT_GRAY);

        panel5.setOpaque(true);
        panel5.setBackground(Color.PINK);

        panel.add(playListPanel, BorderLayout.WEST);
//        panel.add(panel2,BorderLayout.WEST);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(panel3, BorderLayout.EAST);
        panel.add(panel4, BorderLayout.NORTH);
        panel.add(panel5, BorderLayout.SOUTH);

        mainFrame.add(panel);

    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }


}
