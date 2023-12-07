public class TurnCommand implements TurtleCommand {
    private double angle;

    public TurnCommand(double angle) {
        this.angle = angle;
    }

    @Override
    public void execute(Turtle turtle) {
        turtle.turn(angle);
    }
}