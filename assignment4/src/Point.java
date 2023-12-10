/**
 * Point class represents a point in 2D space with x and y coordinates.
 * This class is used to store and access the coordinates of a point.
 */
public class Point {
    double x, y; // x and y coordinates of the point

    /**
     * Constructor for Point.
     * Initializes a point with specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate.
     */
    double getX() { return x; }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate.
     */
    double getY() { return y; }
}
