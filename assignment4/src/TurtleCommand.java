/**
 * Interface for TurtleCommand.
 * This interface defines a single method for executing commands on a Turtle object.
 * Implementing classes will specify the actual behavior of the command when executed.
 */
public interface TurtleCommand {

    /**
     * Executes a command on the given Turtle.
     * The specific behavior of the command depends on the implementation.
     *
     * @param turtle The Turtle object on which the command will be executed.
     */
    void execute(Turtle turtle);
}
