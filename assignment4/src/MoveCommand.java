/**
 * Implements the TurtleCommand interface to represent a move command for a Turtle.
 * This command will move the turtle a specified distance in its current direction.
 */
public class MoveCommand implements TurtleCommand  {
    private double distance;

    /**
     * Constructor for MoveCommand.
     * Initializes the command with a specified distance to move the turtle.
     *
     * @param distance The distance the turtle should move when the command is executed.
     */
    public MoveCommand(double distance) {
        this.distance = distance;
    }

    /**
     * Executes the move command on the given Turtle.
     * Invokes the turtle's move method with the specified distance.
     *
     * @param turtle The Turtle object on which the command will be executed.
     */
    @Override
    public void execute(Turtle turtle) {
        turtle.move(distance);
    }
}
