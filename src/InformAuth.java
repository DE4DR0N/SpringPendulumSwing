import javax.swing.*;
import java.awt.*;

public class InformAuth {
    public InformAuth() {
        JFrame frame = new JFrame("About the author");

        // Установка менеджера компоновки
        frame.setLayout(new BorderLayout());

        // Добавление фотографии
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("src/Imgs/IMG_2003.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(256,256, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        frame.add(imagePanel, BorderLayout.NORTH);

        // Создание панели для текста
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ФИО
        JLabel nameLabel = new JLabel("ФИО: Гедревич Евгений Анатольевич");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(nameLabel);
        // Номер учебной группы
        JLabel groupLabel = new JLabel("Группа: 10702221");
        groupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(groupLabel);
        // Mail
        JLabel mailLabel = new JLabel("eugene.deadron@gmail.com");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(mailLabel);
        frame.add(textPanel, BorderLayout.CENTER);

        // Установка свойств окна
        frame.setSize(280, 480);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
