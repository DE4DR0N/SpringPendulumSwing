import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    public MainPage() {
        super("Main Window");
        JButton buttonSpringSim = new JButton("Open application");
        JButton buttonInformAuth = new JButton("About the author");

        buttonSpringSim.setFocusable(false);
        buttonSpringSim.addActionListener(e -> new SpringSim());
        buttonInformAuth.setFocusable(false);
        buttonInformAuth.addActionListener(e -> new Author());

        setLayout(new GridLayout(3,1));
        add(buttonSpringSim);
        add(buttonInformAuth);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640,480);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SplashWindow();
    }
}
