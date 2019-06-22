package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import GUI.Essentials;
public class AddPlaylist extends JFrame {
    private static final int WIDTH = 950,HEIGHT=550;
    JPanel panel1=new JPanel();
    public AddPlaylist(){
        super();

        this.setLayout(new BorderLayout());
        panel1.setBackground(Essentials.getColor("heavy grey"));
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2-100);
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(1,1,1,1);
        JButton button=new JButton("CREATE");
        button.setForeground(Essentials.getColor("grey"));
        JTextField name=new JTextField("",24);
        JTextField description= new JTextField("",24);
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setSize(new Dimension(10,12));
        JLabel title=new JLabel("Create Playlist");
        title.setPreferredSize(new Dimension(WIDTH,100));
        title.setFont(new Font("Serif",Font.PLAIN,40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        JLabel nameLabel=new JLabel("Name");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel descriptionLabel=new JLabel("Description");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel chooseImage=new JLabel("Choose Image");
        chooseImage.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Essentials.getColor("grey"));
        nameLabel.setForeground(Essentials.getColor("grey"));
        descriptionLabel.setForeground(Essentials.getColor("grey"));
        chooseImage.setForeground(Essentials.getColor("grey"));
        Essentials.gridBagSetups(gbc,3,1,2,1);
        panel1.add(nameLabel,gbc);

        Essentials.gridBagSetups(gbc,3,2,5,1);
        panel1.add(name,gbc);

        Essentials.gridBagSetups(gbc,4,3,2,1);
        panel1.add(descriptionLabel,gbc);

        Essentials.gridBagSetups(gbc,3,4,5,3);
        panel1.add(description,gbc);
        JPanel panel2 =new JPanel();
        JPanel panel3 =new JPanel();
        JPanel panel4 =new JPanel();
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel4.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));
        title.setVerticalAlignment(SwingConstants.CENTER);
        panel2.setLayout(new GridBagLayout());
        Essentials.gridBagSetups(gbc,0,1,5,1);
        panel2.add(chooseImage,gbc);
        Essentials.gridBagSetups(gbc,0,2,5,1);
        panel2.add(fileChooser,gbc);
        //panel3.setPreferredSize(new Dimension(WIDTH,100));
        panel3.add(title);
        panel4.add(button);
        this.setVisible(true);
        this.add(panel2,BorderLayout.WEST);
        this.add(panel4,BorderLayout.SOUTH);
        this.add(panel1,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.NORTH);
        pack();
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);
        panel4.setVisible(true);
    }

//    public static void main(String[] args) {
//        AddPlaylist addPlaylist=new AddPlaylist();
//    }
}
