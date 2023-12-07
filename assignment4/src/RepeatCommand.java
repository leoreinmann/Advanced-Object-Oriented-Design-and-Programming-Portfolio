import java.util.List;

public class RepeatCommand implements TurtleCommand {
    private int times;
    private List<TurtleCommand> commands;

    public RepeatCommand(int times, List<TurtleCommand> commands) {
        this.times = times;
        this.commands = commands;
    }

    @Override
    public void execute(Turtle turtle) {
        for (int i = 0; i < times; i++) {
            for (TurtleCommand command : commands) {
                command.execute(turtle);
            }
        }
    }
}
