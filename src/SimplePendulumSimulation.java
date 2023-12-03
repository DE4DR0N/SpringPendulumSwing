import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimplePendulumSimulation extends JFrame {

    private XYSeries displacementSeries;
    private XYSeries velocitySeries;
    private XYSeries accelerationSeries;

    private Timer timer;
    private double time = 0;
    private double dt = 0.01; // Шаг времени
    private double g = 9.8; // Ускорение свободного падения
    private double length = 1.0; // Длина маятника
    private double dampingFactor = 0.02; // Коэффициент затухания

    public SimplePendulumSimulation(String title) {
        super(title);

        displacementSeries = new XYSeries("Displacement");
        velocitySeries = new XYSeries("Velocity");
        accelerationSeries = new XYSeries("Acceleration");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(displacementSeries);
        dataset.addSeries(velocitySeries);
        dataset.addSeries(accelerationSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Simple Pendulum Simulation",
                "Time",
                "Value",
                dataset
        );

        XYPlot plot = chart.getXYPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePendulum();
                time += dt;
            }
        });
    }

    private void updatePendulum() {
        double displacement = Math.sin(Math.sqrt(g / length) * time);
        double velocity = Math.sqrt(g / length) * Math.cos(Math.sqrt(g / length) * time);
        double acceleration = -g / length * Math.sin(Math.sqrt(g / length) * time) - dampingFactor * velocity;

        displacementSeries.add(time, displacement);
        velocitySeries.add(time, velocity);
        accelerationSeries.add(time, acceleration);

        if (time > 60) {
            timer.stop();
        }
    }

    public void startSimulation() {
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimplePendulumSimulation example = new SimplePendulumSimulation("Simple Pendulum Simulation");
                example.pack();
                example.setLocationRelativeTo(null);
                example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                example.setVisible(true);
                example.startSimulation();
            }
        });
    }
}
