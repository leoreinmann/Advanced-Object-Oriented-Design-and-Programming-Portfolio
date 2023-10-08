import java.util.function.Consumer;

/**
 * Represents a node in the Binary Search Tree. Each node has a Student object and references to its left and right children.
 */
public class BinarySearchTree {
    private class TreeNode {
        Student student;
        TreeNode left;
        TreeNode right;

        /**
         * Constructs a new TreeNode with the given student data.
         *
         * @param student The student data to be stored in this node.
         */
        TreeNode(Student student) {
            this.student = student;
        }
    }

    private TreeNode root;
    private SortingStrategy strategy;

    /**
     * Constructs a new empty Binary Search Tree with the given sorting strategy.
     *
     * @param strategy The strategy used for sorting and comparing students.
     */
    public BinarySearchTree(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public SortingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Adds a student to the binary search tree. The position is determined by the sorting strategy.
     *
     * @param student The student object to add to the tree.
     */
    public void add(Student student) {
        root = addRecursive(root, student);
    }

    /**
     * Recursively adds a student to the binary search tree. The position is determined by the sorting strategy.
     *
     * @param current The current node to check.
     * @param student The student object to add.
     * @return The updated node after adding the student.
     */
    private TreeNode addRecursive(TreeNode current, Student student) {
        if (current == null) {
            return new TreeNode(student);
        }

        if (strategy.compare(student, current.student) < 0) {
            current.left = addRecursive(current.left, student);
        } else if (strategy.compare(student, current.student) > 0) {
            current.right = addRecursive(current.right, student);
        }
        return current;
    }

    /**
     * Iterates over the binary search tree in-order and applies the provided action to each student.
     *
     * @param action A Consumer function that processes each student in the tree.
     */
    public void iterate(Consumer<Student> action) {
        inOrderTraversal(root, action);
    }

    /**
     * Recursively traverses the tree in-order and applies the provided action to each student.
     *
     * @param node The current node to traverse.
     * @param action A Consumer function that processes each student in the tree.
     */
    private void inOrderTraversal(TreeNode node, Consumer<Student> action) {
        if (node != null) {
            inOrderTraversal(node.left, action);
            action.accept(node.student);
            inOrderTraversal(node.right, action);
        }
    }

}
