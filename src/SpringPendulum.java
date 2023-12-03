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

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpringPendulum().setVisible(true);
            }
        });
    }
}

class DynamicPendulum {
    private static final double MASS = 1.0;
    private static final double SPRING_CONSTANT = 10.0;

    private double displacement;
    private double velocity;
    private double acceleration;
    private double time;
    private double gravity;

    public DynamicPendulum(double initialDisplacement, double initialVelocity, double initialAcceleration, double gravity) {
        this.displacement = initialDisplacement;
        this.velocity = initialVelocity;
        this.acceleration = initialAcceleration;
        this.time = 0.0;
        this.gravity = gravity;
    }

    public double getDisplacement() {
        return displacement;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getTime() {
        return time;
    }

    public void update() {
        double springForce = -SPRING_CONSTANT * displacement;
        double netForce = MASS * gravity + springForce;

        acceleration = netForce / MASS;
        velocity += acceleration * 0.02; // time step = 0.02 seconds
        displacement += velocity * 0.02;
        time += 0.02;
    }
}
