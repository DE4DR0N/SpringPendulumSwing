import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainPage extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private SpringPendulum pendulum;

    private XYSeries displacementSeries;
    private XYSeries velocitySeries;
    private XYSeries accelerationSeries;

    private JButton startButton;
    private JButton clearButton;
    private JButton aboutAuthorButton;
    private JButton aboutProgramButton;
    private JButton exitButton;
    private JButton saveButton;

    private JTextField massField;
    private JTextField springConstantField;
    private JTextField initialDisplacementField;
    private JTextField initialVelocityField;
    private JTextField gravityField;
    private JTextField targetTimeField;
    private JTextField periodField;



    private Timer timer;

    public MainPage() {
        super("Spring Pendulum");

        setupUI();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel chartPanel = new JPanel(new GridLayout(1, 3));
        displacementSeries = new XYSeries("Смещение");
        velocitySeries = new XYSeries("Скорость");
        accelerationSeries = new XYSeries("Ускорение");
        chartPanel.add(createChartPanel(displacementSeries, "Смещение"));
        chartPanel.add(createChartPanel(velocitySeries, "Скорость"));
        chartPanel.add(createChartPanel(accelerationSeries, "Ускорение"));

        mainPanel.add(chartPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        massField = new JTextField("1.0");
        springConstantField = new JTextField("2.3");
        initialDisplacementField = new JTextField("1.0");
        initialVelocityField = new JTextField("0.1");
        gravityField = new JTextField("9.81");
        targetTimeField = new JTextField("10.0");
        periodField = new JTextField();
        periodField.setEditable(false);

        inputPanel.add(new JLabel("Масса:"));
        inputPanel.add(massField);
        inputPanel.add(new JLabel("Постоянная пружины:"));
        inputPanel.add(springConstantField);
        inputPanel.add(new JLabel("Начальное отклонение:"));
        inputPanel.add(initialDisplacementField);
        inputPanel.add(new JLabel("Начальная скорость:"));
        inputPanel.add(initialVelocityField);
        inputPanel.add(new JLabel("Гравитационная постоянная:"));
        inputPanel.add(gravityField);
        inputPanel.add(new JLabel("Время моделирования:"));
        inputPanel.add(targetTimeField);
        inputPanel.add(new JLabel("Период колебания:"));
        inputPanel.add(periodField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        startButton = new JButton("Выполнить");
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            clearCharts();
            startTimer();
        });

        clearButton = new JButton("Очистить");
        clearButton.addActionListener(e -> {
            clearCharts();
            periodField.setText("");
        });

        saveButton = new JButton("Сохранить изображения графиков");
        saveButton.addActionListener(e -> saveChartsToFile());

        aboutAuthorButton = new JButton("Об авторе");
        aboutAuthorButton.addActionListener(e -> new Author());

        aboutProgramButton = new JButton("О программе");
        aboutProgramButton.addActionListener(e -> new About());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.add(inputPanel);
        buttonsPanel.add(startButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(saveButton);
        controlPanel.add(buttonsPanel);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(aboutAuthorButton);
        buttonPanel.add(aboutProgramButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        add(mainPanel);
    }

    private JPanel createChartPanel(XYSeries series, String chartTitle) {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle, "Время", "Значение", dataset);
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        return new ChartPanel(chart);
    }

    private void startTimer() {
        double mass = Double.parseDouble(massField.getText());
        double springConstant = Double.parseDouble(springConstantField.getText());
        double initialDisplacement = Double.parseDouble(initialDisplacementField.getText());
        double initialVelocity = Double.parseDouble(initialVelocityField.getText());
        double gravity = Double.parseDouble(gravityField.getText());
        double targetTime = Double.parseDouble(targetTimeField.getText());

        pendulum = new SpringPendulum(mass, springConstant, initialDisplacement, initialVelocity, gravity);

        timer = new Timer(20, new ActionListener() {
            private double elapsedTime = 0.0;

            @Override
            public void actionPerformed(ActionEvent e) {
                double time = pendulum.getTime();
                double displacement = pendulum.getDisplacement();
                double velocity = pendulum.getVelocity();
                double acceleration = pendulum.getAcceleration();

                displacementSeries.add(time, displacement);
                velocitySeries.add(time, velocity);
                accelerationSeries.add(time, acceleration);

                pendulum.update();
                elapsedTime += 0.02;

                if (elapsedTime >= targetTime) {
                    timer.stop();
                    startButton.setEnabled(true);
                    double period = pendulum.calculatePeriod();
                    periodField.setText(String.format("%.2f", period) + " секунд");
                }
            }
        });
        timer.start();
    }

    private void clearCharts() {
        displacementSeries.clear();
        velocitySeries.clear();
        accelerationSeries.clear();
    }

    private void saveChartsToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранение изображения графиков");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();

            try {
                saveChartToFile(displacementSeries, "Displacement", filePath + "_смещение.png");
                saveChartToFile(velocitySeries, "Velocity", filePath + "_скорость.png");
                saveChartToFile(accelerationSeries, "Acceleration", filePath + "_ускорение.png");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка сохранения изображения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveChartToFile(XYSeries series, String chartTitle, String filePath) throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle, "Время", "Значение", dataset);
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        File file = new File(filePath);
        ChartUtilities.saveChartAsPNG(file, chart, WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage().setVisible(true));
    }
}