import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashWindow extends JWindow {
    JLabel lblTitle = new JLabel("Белорусский национальный технический универститет");
    JButton btnNext = new JButton("Далее");
    JButton btnExit = new JButton("Выход");
    JPanel panel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel informMain = new JPanel();

    SplashWindow() {
        titlePanel.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout(5,10));
        panel.add(lblTitle, BorderLayout.NORTH);
        panel.add(btnNext, BorderLayout.WEST);
        panel.add(btnExit, BorderLayout.EAST);
        add(panel);

        setSize(400, 50);
        setLocationRelativeTo(null);

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage();
                dispose();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}
