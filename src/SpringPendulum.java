public class SpringPendulum {
    private double mass;
    private double springConstant;
    private double displacement;
    private double velocity;
    private double acceleration;
    private double time;
    private double gravity;

    public SpringPendulum(double mass, double springConstant, double initialDisplacement, double initialVelocity, double gravity) {
        this.mass = mass;
        this.springConstant = springConstant;
        this.displacement = initialDisplacement;
        this.velocity = initialVelocity;
        this.acceleration = 0.0;
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
        double springForce = -springConstant * displacement;
        double netForce = mass * gravity + springForce;

        acceleration = netForce / mass;
        velocity += acceleration * 0.02;
        displacement += velocity * 0.02;
        time += 0.02;
    }
    public double calculatePeriod() {
        return 2 * Math.PI * Math.sqrt(mass / springConstant);
    }
}
