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

public class SpringPendulum extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final double TARGET_TIME = 10.0; // Время, через которое таймер будет остановлен

    private DynamicPendulum pendulum;

    private XYSeries displacementSeries;
    private XYSeries velocitySeries;
    private XYSeries accelerationSeries;

    private Timer timer;

    public SpringPendulum() {
        super("Spring Pendulum");

        pendulum = new DynamicPendulum(1.0, 0.1, 0.0, 9.81);

        displacementSeries = new XYSeries("Displacement");
        velocitySeries = new XYSeries("Velocity");
        accelerationSeries = new XYSeries("Acceleration");

        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(createChartPanel(displacementSeries, "Displacement"));
        mainPanel.add(createChartPanel(velocitySeries, "Velocity"));
        mainPanel.add(createChartPanel(accelerationSeries, "Acceleration"));

        add(mainPanel);

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

    private JPanel createChartPanel(XYSeries series, String chartTitle) {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle, "Time", "Value", dataset);
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        return new ChartPanel(chart);
    }
}

