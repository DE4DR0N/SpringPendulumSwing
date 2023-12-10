import javax.swing.*;
import java.awt.*;

public class About extends JDialog {
    public About() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5,0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(mainPanel);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlTitle.add(new JLabel("Колебательные системы: пружинный маятник"));
        mainPanel.add(pnlTitle, BorderLayout.NORTH);

        JPanel pnlInfImg = new JPanel();
        pnlInfImg.setLayout(new FlowLayout());

        JPanel pnlText = new JPanel();
        pnlText.setLayout(new BoxLayout(pnlText, BoxLayout.PAGE_AXIS));
        pnlText.add(new JLabel("Программа позволяет"));
        pnlText.add(new JLabel("1. Выводить графики скорости, усорения, положения тела в пространстве"));
        pnlText.add(new JLabel("2. Сохранять результаты в файл"));

        ImageIcon imageIcon = new ImageIcon("src/pictures/AppScreen.png");
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);

        pnlInfImg.add(imageLabel);
        pnlInfImg.add(pnlText);
        mainPanel.add(pnlInfImg, BorderLayout.CENTER);

        JPanel pnlVerBtn = new JPanel();
        pnlVerBtn.setLayout(new BorderLayout());

        JPanel version = new JPanel();
        JLabel txtVersion = new JLabel("Версия ver. 1.0.0.2023");
        txtVersion.setAlignmentX(Component.CENTER_ALIGNMENT);
        version.add(txtVersion);
        pnlVerBtn.add(version, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel();
        JButton btnExit = new JButton("Выход");
        btnExit.addActionListener(e -> dispose());
        pnlButton.add(btnExit);
        pnlVerBtn.add(pnlButton, BorderLayout.EAST);

        mainPanel.add(pnlVerBtn, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("О программе");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}