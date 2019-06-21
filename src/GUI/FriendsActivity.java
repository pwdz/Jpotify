package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendsActivity extends JPanel {
    private JLabel titleLabel;
    private static final int WIDTH = 150,HEIGHT=30;
    public FriendsActivity()
    {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Essentials.getColor("heavy grey"));

//        titleLabel = new JLabel("Friend Activity",SwingConstants.CENTER);
//        titleLabel.setOpaque(true);
//        titleLabel.setBackground(Essentials.getColor("grey"));
//        titleLabel.setForeground(Color.WHITE);
//        titleLabel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        titleLabel=Essentials.labelMaker("Friend Activity","grey",WIDTH,HEIGHT);
        add(titleLabel);
    }
}
