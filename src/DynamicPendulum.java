public class DynamicPendulum {
    private static final double MASS = 1.0;
    private static final double SPRING_CONSTANT = 10.0;
    private final double gravity;

    private double displacement;
    private double velocity;
    private double acceleration;
    private double time;


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
