public class Turtle {
    private double x, y; // Current position
    private double angle; // Current direction in degrees

    public Turtle() {
        // Assuming the turtle starts at the origin (0,0) facing East (0 degrees)
        this.x = 0.0;
        this.y = 0.0;
        this.angle = 0.0;
    }

    public void move(double distance) {
        // Convert angle to radians for trigonometric calculations
        double radians = Math.toRadians(angle);
        x += distance * Math.cos(radians);
        y += distance * Math.sin(radians);
    }

    public void turn(double degrees) {
        angle = (angle + degrees) % 360;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public double distanceTo(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public double bearingTo(double px, double py) {
        double radians = Math.atan2(py - y, px - x);
        double bearing = Math.toDegrees(radians) - angle;
        // Normalize the bearing to be between 0 and 360 degrees
        return (bearing + 360) % 360;
    }

}
