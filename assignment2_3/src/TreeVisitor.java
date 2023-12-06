/**
 * An interface for the Visitor pattern, providing methods to visit different types of nodes within a binary search tree.
 * This allows for the implementation of operations to be performed on each node, such as counting or path length calculations,
 * without changing the tree structure or node classes.
 */
public interface TreeVisitor {
    /**
     * Visits a TreeNode that does not contain a NullStudent, indicating it is either an internal node or a leaf with a Student.
     * Implementations of this method should define the operation to be performed on non-null tree nodes.
     *
     * @param node The TreeNode to visit.
     */
    void visitStudentNode(TreeNode node);

    /**
     * Visits a TreeNode that contains a NullStudent, indicating it is a leaf at the end of a path in the tree.
     * Implementations of this method should define the operation to be performed on null tree nodes.
     *
     * @param node The TreeNode representing a null node to visit.
     */
    void visitNullNode(TreeNode node);
}