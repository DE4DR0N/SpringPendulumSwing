import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformAuth {
    public InformAuth() {
        JFrame frame = new JFrame("About the author");

        // Установка менеджера компоновки
        frame.setLayout(new BorderLayout());
        // Добавление фотографии
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("src/pictures/IMG_2003.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(256,256, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        frame.add(imagePanel, BorderLayout.NORTH);

        // Создание панели для компонентов
        JPanel cmpntsPanel = new JPanel();
        cmpntsPanel.setLayout(new BoxLayout(cmpntsPanel, BoxLayout.Y_AXIS));
        cmpntsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ФИО
        JLabel nameLabel = new JLabel("ФИО: Гедревич Евгений Анатольевич");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cmpntsPanel.add(nameLabel);
        // Номер учебной группы
        JLabel groupLabel = new JLabel("Группа: 10702221");
        groupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cmpntsPanel.add(groupLabel);
        // Mail
        JLabel mailLabel = new JLabel("eugene.deadron@gmail.com");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cmpntsPanel.add(mailLabel);
        frame.add(cmpntsPanel, BorderLayout.CENTER);
        cmpntsPanel.add(Box.createVerticalStrut(20));
        // Button
        JButton bttnBack = new JButton("Назад");
        bttnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        bttnBack.addActionListener(e -> frame.dispose());
        cmpntsPanel.add(bttnBack);

        // Установка свойств окна
        frame.setSize(280, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
