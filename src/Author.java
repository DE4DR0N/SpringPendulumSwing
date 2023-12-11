import javax.swing.*;
import java.awt.*;
/**
 * Класс программы реализующий информацию об авторе
 */
public class Author extends JDialog {
    public Author() {
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("src/pictures/Author.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(256,256, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.NORTH);

        JPanel elementsPanel = new JPanel();
        elementsPanel.setLayout(new BoxLayout(elementsPanel, BoxLayout.Y_AXIS));
        elementsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("ФИО: Гедревич Евгений Анатольевич");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(nameLabel);

        JLabel groupLabel = new JLabel("Группа: 10702221");
        groupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(groupLabel);

        JLabel mailLabel = new JLabel("eugene.deadron@gmail.com");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        elementsPanel.add(mailLabel);
        add(elementsPanel, BorderLayout.CENTER);
        elementsPanel.add(Box.createVerticalStrut(20));

        JButton btnBack = new JButton("Назад");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> dispose());
        elementsPanel.add(btnBack);

        setTitle("Об авторе");
        setSize(280, 420);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
