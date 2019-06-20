package GUI;

import java.awt.*;

public class Colors {
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
            case "6":
                return new Color(0, 0, 0);
            default:
                return new Color(0, 0, 0);

        }
    }
}
