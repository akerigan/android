package akerigan.android.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadingPanel extends JComponent implements ActionListener {
    private Timer ticker = null;
    private int alpha = 0;
    private int step;
    private FadeListener fadeListener;

    public FadingPanel(FadeListener fadeListener) {
        this.fadeListener = fadeListener;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (ticker != null) {
                ticker.stop();
            }
            alpha = 0;
            step = 25;
            ticker = new Timer(50, this);
            ticker.start();
        } else {
            if (ticker != null) {
                ticker.stop();
                ticker = null;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255, alpha));
        Rectangle clip = g.getClipBounds();
        g.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    public void switchDirection() {
        step = -step;
        ticker.start();
    }

    public void actionPerformed(ActionEvent e) {
        alpha += step;
        if (alpha >= 255) {
            alpha = 255;
            ticker.stop();
            fadeListener.fadeOutFinished();
        } else if (alpha < 0) {
            alpha = 0;
            ticker.stop();
            fadeListener.fadeInFinished();
        }
        repaint();
    }
}
