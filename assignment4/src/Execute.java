public class Execute {
    /**
     * Main method for executing the turtle program.
     * This method creates a Turtle object, defines two programs for it to execute, and then uses a TurtleInterpreter to interpret and execute these programs.
     * After executing each program, the final position and angle of the Turtle are printed to the console.
     *
     * @param args Command line arguments (not used in this application).
     * @throws Exception If there is an error in interpreting the turtle commands.
     */
    public static void main(String[] args) throws Exception {
        Turtle turtle = new Turtle();

        // Define the first turtle program
        String program = """
                move 10
                turn 90
                move 20
                turn -60
                move 15
                """;

        // Define the second turtle program
        String program2 = """
                #Ps = 10, 10
                #Pt = 10, 20
                #d = distanceTo #s
                #a = bearingTo #s
                turn #a
                move #d
                #u = bearingTo #t
                move 5
                turn 90
                move 5
                repeat 4
                turn 90
                move 10
                end
                """;

        // Create a TurtleInterpreter and execute the first program
        TurtleInterpreter interpreter = new TurtleInterpreter(turtle);
        interpreter.interpret(program);

        // Print the turtle's position and angle after the first program
        System.out.printf("Turtle Position: (%.2f, %.2f)\n", formatValue(turtle.getX()), formatValue(turtle.getY()));
        System.out.printf("Turtle Angle: %.2f\n", formatValue(turtle.getAngle()));

        turtle.reset();

        // Execute the second program and print the turtle's position and angle
        interpreter.interpret(program2);

        System.out.printf("Turtle Position: (%.2f, %.2f)\n", formatValue(turtle.getX()), formatValue(turtle.getY()));
        System.out.printf("Turtle Angle: %.2f\n", formatValue(turtle.getAngle()));
    }

    /**
     * Formats a double value for display, treating very small values as zero.
     * This is useful for avoiding the display of insignificant decimal places due to floating-point precision limitations.
     *
     * @param value The value to format.
     * @return The formatted value.
     */
    private static double formatValue(double value) {
        if (Math.abs(value) < 0.01) {
            return 0.0; // Treat very small values as zero
        }
        return value;
    }
}
