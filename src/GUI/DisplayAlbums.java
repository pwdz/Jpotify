package GUI;

import Listeners.AlbumPanelLinkerToMainframe;
import Listeners.AlbumPanelListener;
import Lists.Album;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DisplayAlbums extends JPanel {
    ArrayList<Album> albums = new ArrayList<>();
    AlbumPanelLinkerToMainframe albumPanelLinkerToMainframe;
    private AlbumPanel albumPanel;
    JPanel panel1,panel3,panel2;
    public DisplayAlbums(ArrayList<Album> albums) {
        this.setVisible(true);
        ImageIcon artworkImage;
        artworkImage = new ImageIcon("./pics/Music.png");
        artworkImage = new ImageIcon(artworkImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        JLabel artworkLable = new JLabel(artworkImage, JLabel.RIGHT);
        artworkLable.setPreferredSize(new Dimension(300, 200));
        artworkLable.setHorizontalAlignment(SwingConstants.CENTER);
        artworkLable.setVerticalAlignment(SwingConstants.TOP);
        artworkLable.setVisible(true);
        JLabel title = Essentials.labelMaker("Albums", "red", 100, 30, new Font("Serif", Font.PLAIN, 60), "grey");
        JLabel nothing = Essentials.labelMaker("", "red", WIDTH, 50, new Font("Serif", Font.PLAIN, 20), "grey");
        this.albums = albums;
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.setLayout(new BorderLayout());
        panel2.add(title, BorderLayout.CENTER);
        panel2.add(artworkLable, BorderLayout.WEST);
        panel2.setPreferredSize(new Dimension(600, 300));
        panel1.setPreferredSize(new Dimension(0, 400));
        panel2.add(nothing, BorderLayout.NORTH);
        panel2.add(nothing, BorderLayout.SOUTH);
        panel3.setLayout(new BorderLayout());
        panel3.add(panel2, BorderLayout.WEST);

        panel1.setLayout(new GridLayout(0, 3, 10, 10));
        albumPanel = null;


    }

    public void contniue() {
        for (Album album : albums) {
            albumPanel = new AlbumPanel(album);
            albumPanelLinkerToMainframe.albumLabelMade(album.getName());
            panel1.add(albumPanel);
        }
        this.add(panel1);
        panel1.setBackground(Essentials.getColor("light red"));
        panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel2.setBackground(Essentials.getColor("red"));
        panel2.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panel3.setBackground(Essentials.getColor("red"));
        this.setBackground(Essentials.getColor("heavy grey"));
        JScrollPane js = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVisible(true);

        js.setBackground(Essentials.getColor("near black"));
        this.add(panel3, BorderLayout.CENTER);
        this.add(js, BorderLayout.SOUTH);
    }

    public AlbumPanel getAlbumPanel() {
        return albumPanel;
    }

    public void setAlbumPanelLinkerToMainframe(AlbumPanelLinkerToMainframe linker) {
        albumPanelLinkerToMainframe = linker;
    }

}