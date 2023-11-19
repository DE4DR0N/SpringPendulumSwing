import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SpringPendulumSimulation extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final double GRAVITY = 9.8; // Acceleration due to gravity (m/s^2)
    private static final double MASS = 1.0;    // Mass of the pendulum bob (kg)
    private static final double SPRING_CONSTANT = 10.0; // Spring constant (N/m)
    private static final double INITIAL_DISPLACEMENT = 0.5; // Initial displacement (m)
    private static final double INITIAL_VELOCITY = 0.0;    // Initial velocity (m/s)

    private static final double TIME_STEP = 0.01; // Time step for simulation (s)
    private static final double SIMULATION_TIME = 10.0; // Total simulation time (s)

    private XYSeries positionSeries;

    public SpringPendulumSimulation(String title) {
        super(title);

        positionSeries = new XYSeries("Position");

        XYSeriesCollection dataset = new XYSeriesCollection(positionSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Spring Pendulum Simulation",
                "Time (s)",
                "Position (m)",
                dataset
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRange(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private void runSimulation() {
        double time = 0.0;
        double position = INITIAL_DISPLACEMENT;
        double velocity = INITIAL_VELOCITY;

        while (time <= SIMULATION_TIME) {
            positionSeries.add(time, position);

            double acceleration = -GRAVITY / MASS - SPRING_CONSTANT / MASS * position;
            velocity += acceleration * TIME_STEP;
            position += velocity * TIME_STEP;

            time += TIME_STEP;

            try {
                Thread.sleep(10); // Slow down the simulation for better visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpringPendulumSimulation example = new SpringPendulumSimulation("Spring Pendulum Simulation");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
            example.runSimulation();
        });
    }
}
