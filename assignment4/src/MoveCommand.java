public class MoveCommand implements TurtleCommand  {
    private double distance;

    public MoveCommand(double distance) {
        this.distance = distance;
    }

    @Override
    public void execute(Turtle turtle) {
        turtle.move(distance);
    }
}