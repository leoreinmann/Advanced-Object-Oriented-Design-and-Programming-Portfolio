public class Execute {
    public static void main(String[] args) throws Exception {
        Turtle turtle = new Turtle();

        String program = "move 10\n" +
                "turn 90\n" +
                "move 20\n" +
                "turn -60\n" +
                "move 15"
                ;
        TurtleInterpreter interpreter = new TurtleInterpreter(turtle);
        interpreter.interpret(program);

        System.out.printf("Turtle Position: (%.2f, %.2f)\n", formatValue(turtle.getX()), formatValue(turtle.getY()));
        System.out.printf("Turtle Angle: %.2f\n", formatValue(turtle.getAngle()));
    }

    private static double formatValue(double value) {
        if (Math.abs(value) < 0.01) {
            return 0.0; // Treat very small values as zero
        }
        return value;
    }
}

