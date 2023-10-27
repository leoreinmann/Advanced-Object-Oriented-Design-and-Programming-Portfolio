public class NullNodeCounterVisitor implements TreeNodeVisitor {
    private int count = 0;

    @Override
    public void visit(Student student) {
        // Do nothing
    }

    @Override
    public void visit(NullStudent nullStudent) {
        count++;
    }

    public int getCount() {
        return count;
    }
}