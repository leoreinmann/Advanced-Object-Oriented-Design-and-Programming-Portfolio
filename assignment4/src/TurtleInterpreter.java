import java.util.*;

public class TurtleInterpreter {
    private Map<String, Double> variables = new HashMap<>();
    private Turtle turtle;
    private List<String> commandList = new ArrayList<>();

    private Map<String, Point> points;

    public TurtleInterpreter(Turtle turtle) {
        this.turtle = turtle;
    }

    public void interpret(String program) throws Exception {
        commandList = Arrays.asList(program.split("\\n"));
        for (int i = 0; i < commandList.size(); i++) {
            int skipLines = interpretCommand(commandList.get(i).trim(), i);
            i += Math.max(skipLines - 2, 0); // Increment by skipLines or not
        }
    }

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



    private void handleVariableAssignment(String[] parts) {
        String varName = parts[0].substring(1);
        if (parts[2].contains(",")) { // Point definition
            String[] pointParts = parts[2].split(",");
            double x = Double.parseDouble(pointParts[0]);
            double y = Double.parseDouble(pointParts[1]);
            points.put(varName, new Point(x, y));
        } else { // Numeric value
            double value = parseValue(parts[2]);
            variables.put(varName, value);
        }
    }



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
