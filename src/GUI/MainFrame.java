package GUI;
import javax.swing.*;
import java.awt.*;

public class MainFrame{
    private JFrame mainFrame;
    private static final int HEIGHT = 920,WIDTH=1500;
    public MainFrame() {
        this.mainFrame = new JFrame("Spotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(WIDTH,HEIGHT);

        JPanel panel,panel1, panel2, panel3, panel4, panel5;
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel.setLayout(new BorderLayout(5,5));
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);

        panel1.setOpaque(true);
        panel1.setBackground(Color.ORANGE);
        panel.setPreferredSize(new Dimension(100,100));

        panel2.setOpaque(true);
        panel2.setBackground(new Color(30,30,30));

        panel3.setOpaque(true);
        panel3.setBackground(Color.CYAN);

        panel4.setOpaque(true);
        panel4.setBackground(Color.LIGHT_GRAY);

        panel5.setOpaque(true);
        panel5.setBackground(Color.PINK);

        panel.add(panel1,BorderLayout.NORTH);
        panel.add(panel2,BorderLayout.CENTER);
        panel.add(panel3,BorderLayout.EAST);
        panel.add(panel4,BorderLayout.WEST);
        panel.add(panel5,BorderLayout.SOUTH);

        mainFrame.add(panel);

    }

    public static void main(String[] args) {
        MainFrame mainFrame=new MainFrame();
    }


}
