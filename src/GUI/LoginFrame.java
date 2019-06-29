package GUI;

import Listeners.LoginFormListener;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private LoginFormListener loginFormListener;

    public LoginFrame() {
        setSize(new Dimension(500, 500));
        setResizable(false);
        setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 100);

        JLabel header = Essentials.labelMaker("Username", "heavy grey", 500, 200);
        add(header, BorderLayout.NORTH);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(500, 100));
        add(textField, BorderLayout.CENTER);

        JLabel bottom = Essentials.labelMaker("", "heavy grey", 500, 200);
        bottom.setLayout(new BorderLayout());
        JButton enter = new JButton("Enter");
        enter.setPreferredSize(new Dimension(100, 30));
//        enter.setBackground(Essentials.getColor());
        bottom.add(enter, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

//();
//        setLoginFormListener(con);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    loginFormListener.startProgram(textField.getText());
                }
            }
        });

        setVisible(true);
    }

    public void setLoginFormListener(LoginFormListener listener) {
        loginFormListener = listener;
    }
}