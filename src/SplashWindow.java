import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashWindow extends JWindow {
    JLabel label = new JLabel("This is smth");
    JButton btnNext = new JButton("Next");
    JButton btnExit = new JButton("Exit");
    JPanel panel = new JPanel();

    SplashWindow() {
        setSize(400, 50);

        panel.setLayout(new BorderLayout(5,10));
        panel.add(label, BorderLayout.NORTH);
        panel.add(btnNext, BorderLayout.WEST);
        panel.add(btnExit, BorderLayout.EAST);
        add(panel);
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
