import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class SpringPendulumSimulation extends JFrame {

    private static final double GRAVITY = 9.81;  // ускорение свободного падения
    private static final double MASS = 1.0;      // масса груза
    private static final double SPRING_CONSTANT = 10.0;  // коэффициент жесткости пружины
    private static final double INITIAL_DISPLACEMENT = 0.5;  // начальное смещение
    private static final double INITIAL_VELOCITY = 0.0;      // начальная скорость

    private static final double TIME_STEP = 0.05;  // временной шаг для численного интегрирования
    private static final int NUM_STEPS = 1000;     // количество шагов интегрирования

    private XYSeries displacementSeries;
    private XYSeries velocitySeries;
    private XYSeries accelerationSeries;

    public SpringPendulumSimulation(String title) {
        super(title);
        this.setLayout(new BorderLayout());

        displacementSeries = new XYSeries("Displacement");
        velocitySeries = new XYSeries("Velocity");
        accelerationSeries = new XYSeries("Acceleration");

        integrateMotion();

        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        this.add(chartPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void integrateMotion() {
        double displacement = INITIAL_DISPLACEMENT;
        double velocity = INITIAL_VELOCITY;

        for (int i = 0; i < NUM_STEPS; i++) {
            double acceleration = calculateAcceleration(displacement, velocity);
            velocity += acceleration * TIME_STEP;
            displacement += velocity * TIME_STEP;

            displacementSeries.add(i * TIME_STEP, displacement);
            velocitySeries.add(i * TIME_STEP, velocity);
            accelerationSeries.add(i * TIME_STEP, acceleration);
        }
    }

    private double calculateAcceleration(double displacement, double velocity) {
        double springForce = -SPRING_CONSTANT * displacement;
        double dampingForce = -0.1 * velocity;  // простое приближение для затухания
        double totalForce = springForce + dampingForce;
        return totalForce / MASS;
    }

    private JFreeChart createChart() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(displacementSeries);
        dataset.addSeries(velocitySeries);
        dataset.addSeries(accelerationSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Spring Pendulum Simulation",
                "Time (s)",
                "Value",
                dataset
        );

        XYPlot plot = chart.getXYPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(0.5));

        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpringPendulumSimulation("Spring Pendulum Simulation"));
    }
}
