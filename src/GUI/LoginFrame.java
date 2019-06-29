package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final int WIDTH = 950, HEIGHT = 550;
    private JButton loginButton;
    private JTextField username, password;
    private JLabel usernameLable;
    private JLabel passwordLable;
    public LoginFrame() {
        super();
        this.setVisible(true);
        this.setBackground(Essentials.getColor("heavy grey"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        this.setSize(new Dimension(WIDTH, HEIGHT));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        loginButton=new JButton("Login");
        username=new JTextField("",24);
        password=new JTextField("",24);

        username.setPreferredSize(new Dimension(500,50));
        password.setPreferredSize(new Dimension(500,50));
        usernameLable=Essentials.labelMaker("Username", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 30), "grey");
        passwordLable=Essentials.labelMaker("Password", "heavy grey", WIDTH, 100, new Font("Serif", Font.PLAIN, 30), "grey");
        this.setBackground(Essentials.getColor("heavy grey"));
        Essentials.gridBagSetups(gbc, 3, 1, 2, 1);
        this.add(usernameLable, gbc);

        Essentials.gridBagSetups(gbc, 3, 2, 5, 1);
       this.add(username, gbc);

        Essentials.gridBagSetups(gbc, 4, 3, 2, 1);
        this.add(passwordLable, gbc);

        Essentials.gridBagSetups(gbc, 3, 4, 5, 3);
        this.add(password, gbc);
        Essentials.gridBagSetups(gbc, 3, 5, 5, 3);
        this.add(loginButton, gbc);

    }

    public static void main(String[] args) {
        LoginFrame loginFrame=new LoginFrame();
    }
}