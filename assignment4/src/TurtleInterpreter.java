import java.util.*;

/**
 * TurtleInterpreter class interprets and executes a set of turtle commands represented as strings.
 * It supports commands for moving and turning the turtle, repeating a set of commands, and variable assignments.
 */
public class TurtleInterpreter {
    private final Map<String, Double> variables = new HashMap<>(); // Stores variables and their values
    private final Turtle turtle; // The Turtle object to manipulate
    private List<String> commandList = new ArrayList<>(); // List of commands to interpret

    private final Map<String, Point> points = new HashMap<>(); // Stores named points for reference in commands

    /**
     * Constructor for the TurtleInterpreter.
     * @param turtle The Turtle object that will be controlled by the interpreter.
     */
    public TurtleInterpreter(Turtle turtle) {
        this.turtle = turtle;
    }

    /**
     * Interprets and executes a program consisting of turtle commands.
     * @param program A string containing turtle commands separated by newlines.
     * @throws Exception If an unrecognized or invalid command is encountered.
     */
    public void interpret(String program) throws Exception {
        commandList = Arrays.asList(program.split("\\n"));
        for (int i = 0; i < commandList.size(); i++) {
            int skipLines = interpretCommand(commandList.get(i).trim(), i);
            i += Math.max(skipLines - 2, 0); // Increment by skipLines or not
        }
    }


    /**
     * Interprets and executes a single command.
     * @param command The command string to interpret.
     * @param currentLine The current line number in the command list (for error reporting).
     * @return The number of lines to skip in the command list, if any.
     * @throws Exception If the command is unrecognized or invalid.
     */
    private int interpretCommand(String command, int currentLine) throws Exception {
        String[] parts = command.split(" ");
        String commandType = parts[0];

        if (commandType.charAt(0) == '#') {
            commandType = "#";
        }

        switch (commandType) {
            case "move":
                double distance = parseValue(parts[1]);
                new MoveCommand(distance).execute(turtle);
                break;
            case "turn":
                double angle = parseValue(parts[1]);
                new TurnCommand(angle).execute(turtle);
                break;
            case "#": // Variable assignment
                handleVariableAssignment(parts);
                break;
            case "repeat":
                return handleRepeatCommand(parts, currentLine); // Returns lines to skip
            case "end":
                break;
            default:
                throw new Exception("Command could not interpreted; \n" + "Command: " + commandType + "\tline: " + currentLine );
        }
        return 0;
    }


    /**
     * Parses a string to extract a numeric value, which could be a direct number, a variable reference, or a special function.
     * @param value The string to parse.
     * @return The numeric value represented by the string.
     */
    private double parseValue(String value) {
        if (value.matches("-?\\d+(\\.\\d+)?")) { // Check if it's a number
            return Double.parseDouble(value);
        } else if (value.startsWith("#")) { // Variable reference
            String variableName = value.substring(1);
            return variables.getOrDefault(variableName, 0.0);
        } else if (value.contains("distanceTo") || value.contains("bearingTo")) {
            return calculateSpecialFunction(value);
        }
        // Default case, if the value can't be parsed
        return 0;
    }



    /**
     * Handles the assignment of values to variables, including direct numeric assignments and point definitions.
     * @param parts The split parts of the command string.
     */
    private void handleVariableAssignment(String[] parts) {
        String varName = parts[0].substring(1);
        if (parts[2].contains(",")) { // Point definition
            String[] pointParts = parts[2].split(",");
            double x = Double.parseDouble(pointParts[0]);
            double y = Double.parseDouble(parts[3]);
            points.put(varName.substring(1,2), new Point(x, y));
        } else if (parts[2].contains("distanceTo") || parts[2].contains("bearingTo")) { // Handling special function
            double value = parseValue(parts[2] + " " + parts[3]);
            variables.put(varName, value);
        } else { // Numeric value
            double value = parseValue(parts[2]);
            variables.put(varName, value);
        }
    }



    /**
     * Calculates the value of special functions like distanceTo and bearingTo.
     * @param function The function string to evaluate.
     * @return The numeric result of the function.
     */
    private double calculateSpecialFunction(String function) {
        String[] parts = function.split(" ");
        String pointName = parts[1].substring(1);
        Point point = points.get(pointName);

        if (function.startsWith("distanceTo")) {
            return turtle.distanceTo(point.getX(), point.getY());
        } else if (function.startsWith("bearingTo")) {
            return turtle.bearingTo(point.getX(), point.getY());
        }
        return 0;
    }


    /**
     * Handles the execution of a repeat command, which repeats a block of commands a specified number of times.
     * @param parts The split parts of the repeat command string.
     * @param currentLine The current line number in the command list.
     * @return The number of lines to skip in the command list.
     * @throws Exception If there's an error in processing the repeat block.
     */
    private int handleRepeatCommand(String[] parts, int currentLine) throws Exception {
        int repeatTimes = Integer.parseInt(parts[1]);
        int repeatEndLine = findEndOfRepeatBlock(currentLine);
        int commandsProcessed = 0;

        for (int i = 0; i < repeatTimes; i++) {
            for (int j = currentLine + 1; j < repeatEndLine; j++) {
                interpretCommand(commandList.get(j).trim(), j);
                commandsProcessed++;
            }
        }

        return repeatEndLine - currentLine + commandsProcessed / repeatTimes; // Adjust to skip processed commands
    }


    /**
     * Finds the end line number of a repeat block.
     * @param startLine The line number where the repeat block starts.
     * @return The line number where the repeat block ends.
     */
    private int findEndOfRepeatBlock(int startLine) {
        int depth = 1;
        for (int i = startLine + 1; i < commandList.size(); i++) {
            if (commandList.get(i).trim().startsWith("repeat")) {
                depth++;
            } else if (commandList.get(i).trim().equals("end")) {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        return commandList.size(); // In case 'end' is not found
    }
}
