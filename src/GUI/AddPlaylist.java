package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import ClientPackage.Library;
import GUI.Essentials;
import Listeners.AddPlaylistListener;

public class AddPlaylist extends JFrame {
    private static final int WIDTH = 950, HEIGHT = 550;
    private JButton createButton;
    private JTextField name, description;
    private JFileChooser fileChooser;
    JPanel panel1 = new JPanel();

    private AddPlaylistListener addPlaylistListener;

    public AddPlaylist() {
        super();


        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 100);

        panel1.setBackground(Essentials.getColor("heavy grey"));
        panel1.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);

        createButton = new JButton("CREATE");
        createButton.setForeground(Essentials.getColor("grey"));
        addCreateButtonActionListener(createButton);

        name = new JTextField("", 24);
        description = new JTextField("", 24);

        fileChooser = new JFileChooser();
        fileChooser.setSize(new Dimension(10, 12));
        fileChooser.setControlButtonsAreShown(false);

        JLabel title = Essentials.labelMaker("Create Playlist", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 30), "grey");

        JLabel nameLabel = new JLabel("name");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setForeground(Essentials.getColor("grey"));

        JLabel chooseImage = new JLabel("Choose Image");
        chooseImage.setHorizontalAlignment(SwingConstants.CENTER);
        chooseImage.setForeground(Essentials.getColor("grey"));

        nameLabel.setForeground(Essentials.getColor("grey"));

        Essentials.gridBagSetups(gbc, 3, 1, 2, 1);
        panel1.add(nameLabel, gbc);

        Essentials.gridBagSetups(gbc, 3, 2, 5, 1);
        panel1.add(name, gbc);

        Essentials.gridBagSetups(gbc, 4, 3, 2, 1);
        panel1.add(descriptionLabel, gbc);

        Essentials.gridBagSetups(gbc, 3, 4, 5, 3);
        panel1.add(description, gbc);

        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel2.setBackground(Essentials.getColor("heavy grey"));
        panel4.setBackground(Essentials.getColor("heavy grey"));
        panel3.setBackground(Essentials.getColor("heavy grey"));

        panel2.setLayout(new GridBagLayout());
        Essentials.gridBagSetups(gbc, 0, 1, 5, 1);
        panel2.add(chooseImage, gbc);
        Essentials.gridBagSetups(gbc, 0, 2, 5, 1);
        panel2.add(fileChooser, gbc);

        panel3.add(title);
        panel4.add(createButton);

//        this.setVisible(true);
        this.add(panel2, BorderLayout.WEST);
        this.add(panel4, BorderLayout.SOUTH);
        this.add(panel1, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.NORTH);
        pack();
    }

    private void addCreateButtonActionListener(JButton createButton) {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!name.getText().equals("") && !fileChooser.getSelectedFile().getAbsolutePath().equals("")) {
                    addPlaylistListener.makePlaylist(name.getText(), description.getText(), fileChooser.getSelectedFile().getAbsolutePath());
                    name.setText("");
                    description.setText("");
                    fileChooser.setSelectedFile(new File(""));
                    dispose();
                }
            }
        });
    }

    public void setAddPlaylistListener(AddPlaylistListener listener) {
        addPlaylistListener = listener;
    }
}
