public class PathLengthVisitor implements TreeVisitor {
    private int longestPath = 0;
    private int totalPathLength = 0;
    private int pathCount = 0;

    @Override
    public void visitStudentNode(TreeNode node) {
        // Increment path length as we go down to children
        int currentPathLength = 1; // Start with 1 for the current node
        traverse(node, currentPathLength);
    }

    private void traverse(TreeNode node, int currentPathLength) {
        boolean isLeaf = true;

        if (node.getLeft() != null && !(node.getLeft().getStudent() instanceof NullStudent)) {
            isLeaf = false;
            traverse(node.getLeft(), currentPathLength + 1);
        }

        if (node.getRight() != null && !(node.getRight().getStudent() instanceof NullStudent)) {
            isLeaf = false;
            traverse(node.getRight(), currentPathLength + 1);
        }

        if (isLeaf) {
            pathCount++;
            totalPathLength += currentPathLength;
            longestPath = Math.max(longestPath, currentPathLength);
        }
    }

    @Override
    public void visitNullNode(TreeNode node) {
        // Null nodes are considered as leaves, so increment the path count and total path length
        pathCount++;
        totalPathLength++; // Each null node adds only 1 to the total path length
    }

    public int getLongestPath() {
        return longestPath;
    }

    public double getAveragePathLength() {
        return pathCount > 0 ? (double) totalPathLength / pathCount : 0;
    }
}
