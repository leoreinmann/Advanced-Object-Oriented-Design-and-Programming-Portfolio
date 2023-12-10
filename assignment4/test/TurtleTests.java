import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTests {

    @Test
    void testInitialPositionAndAngle() {
        Turtle turtle = new Turtle();
        assertEquals(0.0, turtle.getX(), "Initial X position should be 0");
        assertEquals(0.0, turtle.getY(), "Initial Y position should be 0");
        assertEquals(0.0, turtle.getAngle(), "Initial angle should be 0");
    }

    @Test
    void testMoveCommand() {
        Turtle turtle = new Turtle();
        new MoveCommand(10).execute(turtle);
        assertEquals(10.0, turtle.getX(), "X position should be 10 after moving 10 units");
    }

    @Test
    void testTurnCommand() {
        Turtle turtle = new Turtle();
        new TurnCommand(90).execute(turtle);
        assertEquals(90.0, turtle.getAngle(), "Angle should be 90 after turning 90 degrees");
    }

    @Test
    void testRepeatCommand() {
        Turtle turtle = new Turtle();
        List<TurtleCommand> commands = Arrays.asList(new MoveCommand(10), new TurnCommand(90));
        new RepeatCommand(2, commands).execute(turtle);
        assertEquals(10.0, formatValue(turtle.getX()), "X position should be back to 10 after repeating move and turn");
        assertEquals(10.0, formatValue(turtle.getY()), "Y position should be 10 after moving 10 units twice");
        assertEquals(180.0, turtle.getAngle(), "Angle should be 180 after two 90-degree turns");
    }

    @Test
    void testDistanceTo() {
        Turtle turtle = new Turtle();
        Point point = new Point(10, 0);
        double expectedDistance = 10.0;
        assertEquals(expectedDistance, turtle.distanceTo(point.getX(), point.getY()), "Distance to point (10, 0) should be 10 units");
    }

    @Test
    void testBearingTo() {
        Turtle turtle = new Turtle();
        // Assuming the turtle starts facing East (0 degrees)
        Point pointEast = new Point(10, 0);
        Point pointNorth = new Point(0, 10);
        assertEquals(0.0, turtle.bearingTo(pointEast.getX(), pointEast.getY()), "Bearing to point (10, 0) should be 0 degrees");
        assertEquals(90.0, turtle.bearingTo(pointNorth.getX(), pointNorth.getY()), "Bearing to point (0, 10) should be 90 degrees");
    }

    @Test
    void testReset() {
        Turtle turtle = new Turtle();
        new MoveCommand(10).execute(turtle);
        new TurnCommand(90).execute(turtle);
        turtle.reset();
        assertEquals(0.0, turtle.getX(), "X position should be 0 after reset");
        assertEquals(0.0, turtle.getY(), "Y position should be 0 after reset");
        assertEquals(0.0, turtle.getAngle(), "Angle should be 0 after reset");
    }

    private static double formatValue(double value) {
        if (Math.abs(value) < 0.01) {
            return 0.0; // Treat very small values as zero
        }
        return value;
    }
}
