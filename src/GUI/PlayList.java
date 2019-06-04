package GUI;

import org.w3c.dom.html.HTMLLabelElement;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class PlayList extends JPanel {
    private GridBagConstraints gbc;
    private JLabel yourLibrary;
    private JLabel songs,albums,playList;

    public PlayList() {
        super();
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Colors.getColor("heavy grey"));
        gbc = new GridBagConstraints();

        yourLibrary = makeLabelReady("YOUR LIBRARY","grey");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(yourLibrary, gbc);

        songs = makeLabelReady("Songs","heavy grey");
        gbc.gridy=1;
        add(songs,gbc);

         albums= makeLabelReady("Albums","heavy grey");
         gbc.gridy=2;
         add(albums,gbc);

         playList = makeLabelReady("PLAYLISTS","grey");
         gbc.gridy=3;
         add(playList,gbc);
    }

    private JLabel makeLabelReady(String labelName,String colorName) {
        JLabel label=new JLabel(labelName,SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Colors.getColor(colorName));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 30));
        return label;
    }
}
