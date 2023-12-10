import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private final JPanel pnlCharts;

    private SpringPendulum pendulum;
    private XYSeries displacementSeries;
    private XYSeries velocitySeries;
    private XYSeries accelerationSeries;
    private static final double TARGET_TIME = 10.0; // Время, через которое таймер будет остановлен
    private static Timer timer;

    public MainPage() {
        super("Main Window");
        setLayout(new BorderLayout());

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(pnlMain, BorderLayout.CENTER);

        pnlCharts = new JPanel();
        pnlCharts.setLayout(new GridLayout(2, 2));
        pnlCharts.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        setupChartsUI();
        pnlMain.add(pnlCharts);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.PAGE_AXIS));
        JButton btnExecute = new JButton("Выполнить");
        btnExecute.addActionListener(e -> onButtonExecuteClick());
        pnlButtons.add(btnExecute);
        pnlMain.add(pnlButtons);

        JPanel pnlExtraButtons = new JPanel();
        pnlExtraButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(pnlExtraButtons, BorderLayout.SOUTH);
        JButton btnAbout = new JButton("О программе");
        btnAbout.addActionListener(e -> new About());
        pnlExtraButtons.add(btnAbout);
        JButton btnAuthor = new JButton("Об авторе");
        btnAuthor.addActionListener(e -> new Author());
        pnlExtraButtons.add(btnAuthor);
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> System.exit(1));
        pnlExtraButtons.add(btnExit);

        JScrollPane scrollPane = new JScrollPane(pnlMain);
        add(scrollPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1600,900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupChartsUI() {

        displacementSeries = new XYSeries("Смещение");
        velocitySeries = new XYSeries("Скорость");
        accelerationSeries = new XYSeries("Ускорение");

        pnlCharts.add(createChartPanel(displacementSeries, "Смещение"));
        pnlCharts.add(createChartPanel(velocitySeries, "Скорость"));
        pnlCharts.add(createChartPanel(accelerationSeries, "Ускорение"));
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

    private void onButtonExecuteClick() {
        pendulum = new SpringPendulum(1.0, 0.0, 0.0, 9.81);
        timer = new Timer(20, new ActionListener() {
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

                if (time >= TARGET_TIME) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage().setVisible(true));
    }
}