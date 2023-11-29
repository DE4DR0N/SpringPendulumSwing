import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    public MainPage() {
        super("Main Window");

        JButton btnSpringSim = new JButton("Open application");
        btnSpringSim.setFocusable(false);
        btnSpringSim.addActionListener(e -> new SpringSim());

        JButton btnAuthor = new JButton("About the author");
        btnAuthor.setFocusable(false);
        btnAuthor.addActionListener(e -> new Author());

        JButton btnAbout = new JButton("About the program");
        btnAbout.setFocusable(false);
        btnAbout.addActionListener(e -> new About());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640,480);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {

        new SplashWindow();
        //new About();
    }
}
