import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleInterpreterTests {
    @Test
    void testSimpleCommandInterpretation() throws Exception {
        Turtle turtle = new Turtle();
        TurtleInterpreter interpreter = new TurtleInterpreter(turtle);
        interpreter.interpret("move 10");
        assertEquals(10.0, turtle.getX(), "X position should be 10 after moving 10 units");
    }

    @Test
    void testVariableAssignmentAndUsage() throws Exception {
        Turtle turtle = new Turtle();
        TurtleInterpreter interpreter = new TurtleInterpreter(turtle);
        interpreter.interpret("#distance = 10\nmove #distance");
        assertEquals(10.0, turtle.getX(), "X position should be 10 after moving a distance of 10 units stored in a variable");
    }

    @Test
    void testComplexCommandInterpretation() throws Exception {
        Turtle turtle = new Turtle();
        TurtleInterpreter interpreter = new TurtleInterpreter(turtle);
        String program = """
                move 10
                turn 90
                move 10
                repeat 2
                turn 90
                move 10
                end
                """;
        interpreter.interpret(program);
        assertEquals(0.0, formatValue(turtle.getX()), "X position should be back to 0 after executing the program");
        assertEquals(0.0, formatValue(turtle.getY()), "Y position should be 0 after executing the program");
        assertEquals(270.0, turtle.getAngle(), "Angle should be 270 after executing the program");
    }


    private static double formatValue(double value) {
        if (Math.abs(value) < 0.01) {
            return 0.0; // Treat very small values as zero
        }
        return value;
    }
}
