package GUI;

import javax.swing.*;
import java.awt.*;

public class HeaderGUI extends JPanel {
    private static final int WIDTH = 0,HEIGHT=30;
    public HeaderGUI() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Essentials.getColor("heavy grey"));
    }
}
