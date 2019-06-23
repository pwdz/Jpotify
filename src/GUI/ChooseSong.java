package GUI;

import Listeners.ChooseSongListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseSong extends JFrame {
    private static final int WIDTH = 950, HEIGHT = 550;
    private JButton addBtn;
    private JFileChooser fileChooser;

    private ChooseSongListener chooseSongListener;

    public ChooseSong() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        //GridBagConstraints gbc=new GridBagConstraints();

        JLabel title = new JLabel("Add Song");
        title.setPreferredSize(new Dimension(WIDTH, 60));
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Essentials.getColor("grey"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        addBtn = new JButton("ADD TO LIBRARY");
        addBtn.setForeground(Essentials.getColor("grey"));
        addBtn.setPreferredSize(new Dimension(250, 20));
        addAddBtnActionListener(addBtn);

        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setControlButtonsAreShown(false);

        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));

        panel1.add(title);
        panel2.add(fileChooser);
        panel3.add(addBtn);
//        this.setVisible(true);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
        pack();
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);


    }

    private void addAddBtnActionListener(JButton addBtn) {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!String.valueOf(fileChooser.getSelectedFile()).equals(null) && !String.valueOf(fileChooser.getSelectedFile()).equals("")) {
//                    System.out.println("!!!!!!!!!!!!!!!:" + String.valueOf(fileChooser.getSelectedFile()));
                    chooseSongListener.addSongToLibrary(String.valueOf(fileChooser.getSelectedFile()));
                    fileChooser.setSelectedFile(new File(""));
//                    fileChooser
                    dispose();
                }
            }
        });
    }

    public void setChooseSongListener(ChooseSongListener listener) {
        chooseSongListener = listener;
    }

//    public static void main(String[] args) {
//        ChooseSong chooseSong=new ChooseSong();
//    }

}
