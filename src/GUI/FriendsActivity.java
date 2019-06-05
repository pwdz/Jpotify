package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendsActivity extends JPanel {
    private JLabel titleLabel;
    public FriendsActivity()
    {
        super();
        setPreferredSize(new Dimension(100,0));
        setBackground(Colors.getColor("heavy grey"));

        titleLabel = new JLabel("Friend Activity",SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Colors.getColor("grey"));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(100,30));
        add(titleLabel);
    }
}
