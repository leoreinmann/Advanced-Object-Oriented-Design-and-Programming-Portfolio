public class PathLengthVisitor implements TreeNodeVisitor {
    private int currentDepth = 0;
    private int totalDepth = 0;
    private int nodeCount = 0;
    private int maxDepth = 0;

    @Override
    public void visit(Student student) {
        nodeCount++;
        currentDepth++;
        totalDepth += currentDepth;
        maxDepth = Math.max(maxDepth, currentDepth);
    }

    @Override
    public void visit(NullStudent nullStudent) {
        // Do nothing for NullStudent nodes for path calculations
    }

    public int getLongestPath() {
        return maxDepth;
    }

    public double getAveragePathLength() {
        return nodeCount == 0 ? 0 : (double) totalDepth / nodeCount;
    }

    public void increaseDepth() {
        currentDepth++;
    }

    public void decreaseDepth() {
        currentDepth--;
    }
}