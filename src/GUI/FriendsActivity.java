package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendsActivity extends JPanel {
    private JLabel titleLabel;
    private static final int WIDTH = 250,HEIGHT=30;
    public FriendsActivity()
    {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Essentials.getColor("heavy grey"));

        titleLabel=Essentials.labelMaker("Friend Activity","grey",WIDTH,HEIGHT);
        add(titleLabel);
    }
}
