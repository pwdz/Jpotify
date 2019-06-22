package GUI;

import javazoom.jl.decoder.Header;

import javax.swing.*;
import java.awt.*;

public class HeaderGUI extends JPanel {
    private static final int WIDTH = 0,HEIGHT=35;
    private JTextField searchField;
    private JLabel searchLabel;
    private  JLabel username;
    private JLabel userPictureLabel;
    public HeaderGUI() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Essentials.getColor("heavy grey"));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        searchField= new JTextField();
        searchField.setEditable(true);
        searchField.setPreferredSize(new Dimension(100,30));
        searchField.setBackground(new Color(200,200,200));

        JButton button=new JButton();
        button.setBackground(Essentials.getColor("grey"));
        button.setPreferredSize(new Dimension(35,30));
        button.setIcon(Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Search.png",25,25));

//        searchLabel = Essentials.labelMaker("","heavy grey",35,30);
//        searchLabel.setIcon(Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\Search.png",30,30));

        username = Essentials.labelMaker("Mohammad","heavy grey",100,HEIGHT);

        userPictureLabel = Essentials.labelMaker("","heavy grey",HEIGHT,HEIGHT);
        userPictureLabel.setIcon(Essentials.imageProvider("C:\\Users\\acer\\Desktop\\Jpotify\\pics\\userPhoto.JPG",55,HEIGHT));

        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=0;
        gbc.gridy=0;
        add(searchField,gbc);

        gbc.gridx=1;
        add(button,gbc);

        gbc.gridx=2;
        gbc.weightx=1;
        gbc.fill=GridBagConstraints.BOTH;
        add(Essentials.labelMaker("","heavy grey",0,30),gbc);

        gbc.gridx=3;
        gbc.weightx=0;
        gbc.fill=GridBagConstraints.CENTER;
        add(userPictureLabel,gbc);

        gbc.gridx=4;
        add(username,gbc);
//        ImageIcon searchImageIcon = Essentials.imageProvider("",45,45);
//        searchLabel.setIcon(searchImageIcon);
    }
}
