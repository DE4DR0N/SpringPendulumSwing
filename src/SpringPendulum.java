/**
 * Класс реализующий математические расчёты
 * пружинного маятника
 */
public class SpringPendulum {
    private final double mass;
    private final double springConstant;
    private double displacement;
    private double velocity;
    private double acceleration;
    private double time;
    private final double gravity;

    /**
     * Конструктор класса SpringPendulum
     * заполняет поля для расчётов
     * @param mass масса объекта
     * @param springConstant константа пружины
     * @param initialDisplacement начальный сдвиг
     * @param initialVelocity начальная скорость
     * @param gravity гравитационная постоянная
     */
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

    /**
     * Метод высчитывающий ускорение, скорость и смещение
     */
    public void update() {
        double springForce = -springConstant * displacement;
        double netForce = mass * gravity + springForce;

        acceleration = netForce / mass;
        velocity += acceleration * 0.02;
        displacement += velocity * 0.02;
        time += 0.02;
    }

    /**
     * Метод расчитыващий значение периода колебания
     * @return значение периода колебания
     */
    public double calculatePeriod() {
        return 2 * Math.PI * Math.sqrt(mass / springConstant);
    }
}
