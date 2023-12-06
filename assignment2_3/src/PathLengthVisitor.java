public class PathLengthVisitor implements TreeVisitor {
    private int longestPath = 0;
    private int totalPathLength = 0;
    private int pathCount = 0;

    /**
     * Visits a student node during tree traversal. This method initiates the traversal from the current node,
     * starting with a path length of 1 to account for the current node itself.
     *
     * @param node The TreeNode object that represents the student node being visited.
     */
    @Override
    public void visitStudentNode(TreeNode node) {
        // Increment path length as we go down to children
        int currentPathLength = 1; // Start with 1 for the current node
        traverse(node, currentPathLength);
    }

    /**
     * Recursively traverses the binary search tree from a given node, calculating the path length as it proceeds.
     * This method determines if the current node is a leaf (i.e., children are null nodes)
     * and updates the path length and longest path statistics accordingly.
     *
     * @param node The TreeNode object to start the traversal from.
     * @param currentPathLength The length of the path from the root to the current node.
     */
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

    /**
     * Visits a null node in the binary search tree. This method treats null nodes as leaves and updates
     * the path count and total path length accordingly. Each null node signifies the end of a path,
     * hence contributing a value of 1 to the total path length count.
     *
     * @param node The TreeNode object that represents the null node being visited.
     */
    @Override
    public void visitNullNode(TreeNode node) {
        // Null nodes are considered as leaves, so increment the path count and total path length
        pathCount++;
        totalPathLength++; // Each null node adds only 1 to the total path length
    }

    public int getLongestPath() {
        return longestPath;
    }

    /**
     * Calculates and returns the average path length of all paths in the binary search tree.
     * The average is computed as the total path length divided by the number of paths (path count).
     * If there are no paths in the tree (path count is zero), the average path length is defined to be 0.
     *
     * @return The average path length of the paths in the binary search tree.
     */
    public double getAveragePathLength() {
        return pathCount > 0 ? (double) totalPathLength / pathCount : 0;
    }
}
