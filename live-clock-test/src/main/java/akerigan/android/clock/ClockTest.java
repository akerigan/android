package akerigan.android.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * @author Vlad Vinichenko (akerigan@gmail.com)
 * @since 2011-07-22 15:47 (Europe/Moscow)
 */
public class ClockTest extends JComponent {
    private static Color m_tRed = new Color(255, 0, 0, 150);
    private static Color m_tGreen = new Color(0, 255, 0, 150);
    private static Color m_tBlue = new Color(0, 0, 255, 150);
    private static Font monoFont = new Font(
            "Monospaced", Font.BOLD | Font.ITALIC, 36
    );
    private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);
    private static Font serifFont = new Font("Serif", Font.BOLD, 24);
    private static ImageIcon java2sLogo = new ImageIcon("java2s.gif");

    private static int dotRadius = 5;

    private static int secsRadius = 100;
    private static int minsRadius = 90;
    private static int hoursRadius = 80;

    private long dayStart;
    private int[] secsX = new int[60];
    private int[] secsY = new int[60];
    private int[] minsX = new int[60];
    private int[] minsY = new int[60];
    private int[] hoursX = new int[24];
    private int[] hoursY = new int[24];

    public ClockTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dayStart = calendar.getTimeInMillis();

        for (int i = 0; i < 60; ++i) {
            double angle = (i - 15) * Math.PI / 30;
            secsX[i] = (int) (Math.cos(angle) * secsRadius);
            secsY[i] = (int) (Math.sin(angle) * secsRadius);
            minsX[i] = (int) (Math.cos(angle) * minsRadius);
            minsY[i] = (int) (Math.sin(angle) * minsRadius);
        }

        for (int hours = 0; hours < 24; ++hours) {
            double angle = (hours - 13) * Math.PI / 12;
            hoursX[hours] = (int) (Math.cos(angle) * hoursRadius);
            hoursY[hours] = (int) (Math.sin(angle) * hoursRadius);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintClock(g);
    }

    public void paintClock(Graphics g) {
        int xMax = getWidth();
        int yMax = getHeight();
        int xCent = xMax / 2;
        int yCent = yMax / 2;

        g.setColor(Color.white);
        g.fillRect(0, 0, xMax, yMax);
        g.setColor(Color.lightGray);
        int secsDiameter = 2 * secsRadius;
        g.fillOval(xCent - secsRadius, yCent - secsRadius, secsDiameter, secsDiameter);
        g.setColor(Color.black);
        g.drawLine(xCent, yCent, xCent + hoursX[7], yCent + hoursY[7]);
        g.drawLine(xCent, yCent, xCent + hoursX[18], yCent + hoursY[18]);
        g.drawLine(xCent, yCent, xCent + hoursX[23], yCent + hoursY[23]);

        int daySecs = (int)(System.currentTimeMillis() - dayStart) / 1000;
        int curHours = daySecs / 3600;
        System.out.println("curHours = " + curHours);
        int curMins = (daySecs - curHours * 3600) / 60;
        System.out.println("curMins = " + curMins);
        int curSecs = daySecs % 60;
        System.out.println("curSecs = " + curSecs);
        int dotX = xCent - dotRadius;
        int dotY = yCent - dotRadius;
        int dotDiameter = dotRadius * 2;
        for (int i = 0; i < 60; ++i) {
            if (i == curSecs) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.darkGray);
            }
            g.fillOval(secsX[i] + dotX, secsY[i] + dotY, dotDiameter, dotDiameter);
            if (i == curMins) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.darkGray);
            }
            g.fillOval(minsX[i] + dotX, minsY[i] + dotY, dotDiameter, dotDiameter);
        }
        for (int i = 0; i < 24; ++i) {
            if (i == curHours) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.darkGray);
            }
            g.fillOval(hoursX[i] + dotX, hoursY[i] + dotY, dotDiameter, dotDiameter);
        }
    }

    public void paintSample(Graphics g) {
        super.paintComponent(g);

        // draw entire component white
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        // yellow circle
        g.setColor(Color.yellow);
        g.fillOval(0, 0, 240, 240);

        // magenta circle
        g.setColor(Color.magenta);
        g.fillOval(160, 160, 240, 240);

        // paint the icon below blue sqaure
        int w = java2sLogo.getIconWidth();
        int h = java2sLogo.getIconHeight();
        java2sLogo.paintIcon(this, g, 280 - (w / 2), 120 - (h / 2));

        // paint the icon below red sqaure
        java2sLogo.paintIcon(this, g, 120 - (w / 2), 280 - (h / 2));

        // transparent red square
        g.setColor(m_tRed);
        g.fillRect(60, 220, 120, 120);

        // transparent green circle
        g.setColor(m_tGreen);
        g.fillOval(140, 140, 120, 120);

        // transparent blue square
        g.setColor(m_tBlue);
        g.fillRect(220, 60, 120, 120);

        g.setColor(Color.black);

        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        w = fm.stringWidth("Java Source");
        h = fm.getAscent();
        g.drawString("Java Source", 120 - (w / 2), 120 + (h / 4));

        g.setFont(sanSerifFont);
        fm = g.getFontMetrics();
        w = fm.stringWidth("and");
        h = fm.getAscent();
        g.drawString("and", 200 - (w / 2), 200 + (h / 4));

        g.setFont(serifFont);
        fm = g.getFontMetrics();
        w = fm.stringWidth("Support.");
        h = fm.getAscent();
        g.drawString("Support.", 280 - (w / 2), 280 + (h / 4));
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public static void main(String args[]) {
        final JFrame mainFrame = new JFrame("Graphics demo");
        mainFrame.getContentPane().add(new ClockTest());
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Timer animation;
        animation = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.repaint();
            }
        });
        animation.start();
    }
}
