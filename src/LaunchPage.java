import javax.swing.*;
import java.awt.*;

public class LaunchPage extends JFrame{
    public LaunchPage(){
        super("Launch Page");
        JButton buttonSpringSim = new JButton("Open application");
        JButton buttonInformAuth = new JButton("About the author");

        buttonSpringSim.addActionListener(e -> new SpringSim());
        buttonInformAuth.addActionListener(e -> new InformAuth());

        setLayout(new GridLayout(2,1));
        add(buttonSpringSim);
        add(buttonInformAuth);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640,480);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new LaunchPage();
    }
}
