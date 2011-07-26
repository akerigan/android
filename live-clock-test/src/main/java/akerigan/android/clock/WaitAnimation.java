package akerigan.android.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitAnimation extends JComponent implements ActionListener {
    private Image[] animation;
    private int index;
    private int direction;

    public WaitAnimation() {
        setOpaque(false);

        index = 0;
        direction = 1;

        MediaTracker tracker = new MediaTracker(this);
        animation = new Image[6];

        for (int i = 0; i < 6; i++) {
            animation[i] = UIHelper.readImage("auth_" + String.valueOf(i) + ".png");
            tracker.addImage(animation[i], i);
        }

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
        }

        Timer animationTimer = new Timer(150, this);
        animationTimer.start();
    }

    public void paintComponent(Graphics g) {
        int x = (int) ((getWidth() - animation[index].getWidth(this)) / 2.0);
        int y = (int) ((getHeight() - animation[index].getHeight(this)) / 2.0);

        g.drawImage(animation[index], x, y, this);
    }

    public void actionPerformed(ActionEvent e) {
        index += direction;
        if (index > 5) {
            index = 5;
            direction = -1;
        } else if (index < 0) {
            index = 0;
            direction = 1;
        }
    }
}
