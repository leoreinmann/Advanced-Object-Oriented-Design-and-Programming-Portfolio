import java.util.List;

/**
 * Implements the TurtleCommand interface to represent a repeat command for a Turtle.
 * This command allows a list of other TurtleCommands to be executed repeatedly for a specified number of times.
 */
public class RepeatCommand implements TurtleCommand {
    private int times;
    private List<TurtleCommand> commands;

    /**
     * Constructor for RepeatCommand.
     * Initializes the command with a specified number of times to repeat and a list of commands to execute.
     *
     * @param times The number of times the list of commands should be executed.
     * @param commands The list of TurtleCommand objects to be executed repeatedly.
     */
    public RepeatCommand(int times, List<TurtleCommand> commands) {
        this.times = times;
        this.commands = commands;
    }

    /**
     * Executes the repeat command on the given Turtle.
     * Each command in the list is executed in order, and this sequence is repeated for the specified number of times.
     *
     * @param turtle The Turtle object on which the commands will be executed.
     */
    @Override
    public void execute(Turtle turtle) {
        for (int i = 0; i < times; i++) {
            for (TurtleCommand command : commands) {
                command.execute(turtle);
            }
        }
    }
}
