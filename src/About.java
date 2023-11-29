import javax.swing.*;
import java.awt.*;

public class About extends JDialog {
    About() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5,0));

        JPanel pnlTitle = new JPanel();
        pnlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlTitle.add(new JLabel("Колебательные системы: пружинный маятник"));
        mainPanel.add(pnlTitle, BorderLayout.NORTH);

        JPanel infImg = new JPanel();
        infImg.setLayout(new FlowLayout());

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));
        text.add(new JLabel("Программа позволяет"));
        text.add(new JLabel("1. Выводить графики скорости, усорения, положения тела в пространстве"));
        text.add(new JLabel("2. Сохранять результаты в файл"));

        ImageIcon imageIcon = new ImageIcon("src/pictures/IMG_2003.jpg");
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);

        infImg.add(imageLabel);
        infImg.add(text);
        mainPanel.add(infImg, BorderLayout.CENTER);

        JPanel vrsnBttn = new JPanel();
        vrsnBttn.setLayout(new BorderLayout());

        JPanel version = new JPanel();
        JLabel txtVersion = new JLabel("Версия ver. 1.0.2023");
        txtVersion.setAlignmentX(Component.CENTER_ALIGNMENT);
        version.add(txtVersion);
        vrsnBttn.add(version, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel();
        JButton btnExit = new JButton("Выход");
        btnExit.addActionListener(e -> dispose());
        pnlButton.add(btnExit);
        vrsnBttn.add(pnlButton, BorderLayout.EAST);

        mainPanel.add(vrsnBttn, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}