package akerigan.android.clock;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UIHelper {
    public static ImageIcon readImageIcon(String fileName) {
        Image image = readImage(fileName);
        if (image == null) {
            return null;
        }

        return new ImageIcon(image);
    }

    public static Image readImage(String fileName) {
        URL url = UIHelper.class.getResource("/images/" + fileName);
        if (url == null) {
            return null;
        }

        return java.awt.Toolkit.getDefaultToolkit().getImage(url);
    }
}
