/**
 * Implements the TurtleCommand interface to represent a turn command for a Turtle.
 * This command will turn the turtle by a specified angle.
 */
public class TurnCommand implements TurtleCommand {
    private double angle;

    /**
     * Constructor for TurnCommand.
     * Initializes the command with a specified angle to turn the turtle.
     *
     * @param angle The angle (in degrees) the turtle should turn when the command is executed. 
     *              Positive values turn it clockwise, and negative values turn it counterclockwise.
     */
    public TurnCommand(double angle) {
        this.angle = angle;
    }

    /**
     * Executes the turn command on the given Turtle.
     * Invokes the turtle's turn method with the specified angle.
     *
     * @param turtle The Turtle object on which the command will be executed.
     */
    @Override
    public void execute(Turtle turtle) {
        turtle.turn(angle);
    }
}
