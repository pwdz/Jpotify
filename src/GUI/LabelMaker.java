package GUI;

import javax.swing.*;
import java.awt.*;

public class LabelMaker {
    public static JLabel labelMaker(String text,String colorName,int width,int height)
    {
        JLabel label=new JLabel();
        label.setText(text);
        label.setOpaque(true);
        label.setBackground(Colors.getColor(colorName));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(width,height));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
