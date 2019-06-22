package GUI;

import javax.swing.*;
import java.awt.*;

public class Essentials {
    public static Color getColor(String colorName) {
        switch (colorName) {
            case "light grey":
                return new Color(220, 220, 214);
            case "grey":
                return new Color(138, 138, 117);
            case "heavy grey":
                return new Color(50, 50, 40);
            case "blue":
                return new Color(12, 12, 100);
            case "red":
                return new Color(100, 12, 12);
            case "yellow":
                return new Color(160, 170, 0);
            case "near black":
                return new Color(30, 30, 30);
            case "grey2":
                return new Color(70,40,40);
            case "green":
                return new Color(10,100,50);
            default:
                return new Color(0, 0, 0);

        }
    }
    public static JLabel labelMaker(String text, String colorName, int width, int height)
    {
        JLabel label=new JLabel();
        label.setText(text);
        label.setOpaque(true);
        label.setBackground(Essentials.getColor(colorName));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(width,height));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
    public static ImageIcon imageProvider(String path,int width,int height)
    {
        ImageIcon img=new ImageIcon(path);
        return new ImageIcon(img.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));
    }
    public static  void gridBagSetups(GridBagConstraints gbc, int gridX,int gridY, int gridWidth,int gridHeight){
        gbc.gridx=gridX;
        gbc.gridy=gridY;
        gbc.gridwidth=gridWidth;
        gbc.gridheight=gridHeight;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.fill=GridBagConstraints.VERTICAL;
    }

}
