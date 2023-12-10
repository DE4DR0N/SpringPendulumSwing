import javax.swing.*;
import java.awt.*;

public class Author extends JDialog {
    public Author() {
        // Установка менеджера компоновки
        setLayout(new BorderLayout());
        // Добавление фотографии
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("src/pictures/IMG_2003.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(256,256, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.NORTH);

        // Создание панели для компонентов
        JPanel elementsPanel = new JPanel();
        elementsPanel.setLayout(new BoxLayout(elementsPanel, BoxLayout.Y_AXIS));
        elementsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ФИО
        JLabel nameLabel = new JLabel("ФИО: Гедревич Евгений Анатольевич");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(nameLabel);
        // Номер учебной группы
        JLabel groupLabel = new JLabel("Группа: 10702221");
        groupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(groupLabel);
        // Mail
        JLabel mailLabel = new JLabel("eugene.deadron@gmail.com");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(mailLabel);
        add(elementsPanel, BorderLayout.CENTER);
        elementsPanel.add(Box.createVerticalStrut(20));
        // Button
        JButton btnBack = new JButton("Назад");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> dispose());
        elementsPanel.add(btnBack);

        // Установка свойств окна
        setTitle("Об авторе");
        setSize(280, 420);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
