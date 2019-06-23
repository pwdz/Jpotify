package GUI;

import javax.swing.*;
import java.awt.*;

public class ChooseSong extends JFrame {
    private static final int WIDTH = 950,HEIGHT=550;
    public ChooseSong(){
        this.setSize(new Dimension(WIDTH,HEIGHT));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setResizable(false);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        JPanel panel2 =new JPanel();
        JPanel panel3=new JPanel();
        //GridBagConstraints gbc=new GridBagConstraints();
        JLabel title=new JLabel("Add Song");
        JButton button= new JButton("ADD TO LIBRARY");
        button.setForeground(Essentials.getColor("grey"));
        title.setPreferredSize(new Dimension(WIDTH,60));
        button.setPreferredSize(new Dimension(250,20));
        title.setFont(new Font("Serif",Font.PLAIN,40));
        title.setForeground(Essentials.getColor("grey"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        JFileChooser fileChooser=new JFileChooser();
        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));

        panel1.add(title);
        panel2.add(fileChooser);
        panel3.add(button);
        this.setVisible(true);
        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        pack();
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);


    }

//    public static void main(String[] args) {
//        ChooseSong chooseSong=new ChooseSong();
//    }

}
