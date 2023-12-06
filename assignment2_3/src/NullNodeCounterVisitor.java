public class NullNodeCounterVisitor implements TreeVisitor {
    private int nullCount = 0;

    @Override
    public void visitStudentNode(TreeNode node) {
        // No action needed for non-null nodes
    }

    @Override
    public void visitNullNode(TreeNode node) {
        if (node.getStudent() instanceof NullStudent) {
            nullCount++;
        }
    }

    public int getNullCount() {
        return nullCount;
    }
}
