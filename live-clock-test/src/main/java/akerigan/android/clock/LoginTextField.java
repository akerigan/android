package akerigan.android.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTextField extends JPanel {
    private JTextField loginField;

    public LoginTextField() {
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());

        addLoginLabel();
        addLoginTextField();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loginField.requestFocus();
            }
        });
    }

    public String getText() {
        return loginField.getText();
    }

    public void addActionListner(ActionListener actionListener) {
        loginField.addActionListener(actionListener);
    }

    private void addLoginTextField() {
        loginField = new JTextField(System.getProperty("user.name"), 10);
        loginField.setBorder(BorderFactory.createEmptyBorder(3, 12, 3, 3));
        loginField.setOpaque(false);
        loginField.setSelectionColor(Color.GRAY);
        loginField.setSelectedTextColor(Color.WHITE);
        loginField.setSelectionStart(0);
        loginField.setSelectionEnd(loginField.getText().length());
        add(BorderLayout.EAST, loginField);
    }

    private void addLoginLabel() {
        JLabel loginLabel = new JLabel("login_");
        loginLabel.setForeground(Color.GRAY);
        loginLabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        add(BorderLayout.WEST, loginLabel);
    }

    public void paintComponent(Graphics g) {
        Color veil = new Color(255, 255, 255, 150);
        g.setColor(veil);
        Rectangle bounds = g.getClipBounds();
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
