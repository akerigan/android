package akerigan.android.clock;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpYourshelf extends JFrame implements FadeListener {
    private JComponent contentPane;
    private LoginTextField loginField;
    private Timer animation;
    private FadingPanel glassPane;

    public HelpYourshelf() {
        glassPane = new FadingPanel(this);
        setGlassPane(glassPane);

        buildContentPane();
        buildLoginForm();
        startAnimation();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(new Dimension(400, 300));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void buildContentPane() {
        contentPane = new CurvesPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
    }

    private void startAnimation() {
        animation = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.repaint();
            }
        });
        animation.start();
    }

    private void buildLoginForm() {
        FormLayout layout = new FormLayout("pref:grow, left:pref, pref:grow",
                                           "40dlu, pref, 9dlu, pref, fill:default");
        PanelBuilder builder = new PanelBuilder(layout);

        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel(UIHelper.readImageIcon("title.png")), cc.xy(2, 2));

        loginField = new LoginTextField();
        builder.add(loginField, cc.xy(2, 4, "right, default"));

        loginField.addActionListner(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        glassPane.setVisible(true);
                    }
                });
            }
        });

        JPanel form = builder.getPanel();
        form.setOpaque(false);
        contentPane.add(form, BorderLayout.CENTER);
    }

    public void fadeInFinished() {
        glassPane.setVisible(false);
    }

    public void fadeOutFinished() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                contentPane = new CirclesPanel();
                contentPane.setLayout(new BorderLayout());
                WaitAnimation waitAnimation = new WaitAnimation();
                contentPane.add(waitAnimation, BorderLayout.CENTER);
                setContentPane(contentPane);
                validate();
                glassPane.switchDirection();
            }
        });
    }

    public String getTitle() {
        return "Help Your Shelf";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelpYourshelf frame = new HelpYourshelf();
                frame.setVisible(true);
            }
        });
    }
}
