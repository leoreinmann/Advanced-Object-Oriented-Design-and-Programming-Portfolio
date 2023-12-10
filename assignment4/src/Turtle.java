/**
 * This class represents a Turtle in a 2D space, where the turtle has a position (x, y) and an angle representing its
 * current direction.
 * The class provides methods for moving the turtle in its current direction, turning it, calculating distances and
 * bearings to other points, and resetting its position and direction.
 */
public class Turtle {
    private double x, y; // Current position
    private double angle; // Current direction in degrees

    /**
     * Constructor for the Turtle class.
     * Initializes the Turtle at the origin (0,0) facing East (0 degrees).
     */
    public Turtle() {
        this.x = 0.0;
        this.y = 0.0;
        this.angle = 0.0;
    }

    /**
     * Moves the turtle forward by a specified distance in the direction it is currently facing.
     * @param distance The distance to move the turtle.
     */
    public void move(double distance) {
        double radians = Math.toRadians(angle);
        x += distance * Math.cos(radians);
        y += distance * Math.sin(radians);
    }

    /**
     * Turns the turtle by a specified number of degrees.
     * @param degrees The number of degrees to turn the turtle. Positive values turn it clockwise, and negative values turn it counterclockwise.
     */
    public void turn(double degrees) {
        angle = (angle + degrees) % 360;
    }

    /**
     * Gets the current x-coordinate of the turtle.
     * @return The current x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the turtle.
     * @return The current y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the current angle of the turtle.
     * @return The current angle in degrees.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Calculates the distance from the turtle to a specified point.
     * @param x The x-coordinate of the point to measure the distance to.
     * @param y The y-coordinate of the point to measure the distance to.
     * @return The distance to the specified point.
     */
    public double distanceTo(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    /**
     * Calculates the bearing from the turtle's current position to a specified point.
     * @param px The x-coordinate of the target point.
     * @param py The y-coordinate of the target point.
     * @return The bearing in degrees from the current position to the specified point.
     */
    public double bearingTo(double px, double py) {
        double radians = Math.atan2(py - y, px - x);
        double bearing = Math.toDegrees(radians) - angle;
        return (bearing + 360) % 360;
    }

    /**
     * Resets the turtle's position to the origin (0, 0) and its direction to East (0 degrees).
     */
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.angle = 0;
    }
}
