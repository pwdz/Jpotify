package GUI;
import javax.swing.*;
import java.awt.*;

public class PlayList extends JPanel {
    private GridBagConstraints gbc ;
    public PlayList() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(new Color(20,20,20));
        gbc = new GridBagConstraints();

        JTextField yourLibrary = new JTextField();
        yourLibrary.setVisible(true);
        yourLibrary.setEditable(false);
        yourLibrary.setSize(100,20);
        yourLibrary.setText("YOUR LIBRARY");
        yourLibrary.setBackground(new Color(30,30,30));
        yourLibrary.setForeground(Color.ORANGE);
        yourLibrary.setBorder(BorderFactory.createLineBorder(new Color(30,30,30)));

        gbc.gridx=0;
        gbc.gridy=0;
        add(yourLibrary,gbc);
        add(yourLibrary,gbc);
    }
}
