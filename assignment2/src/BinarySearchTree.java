import java.util.function.Consumer;

/**
 * Represents a node in the Binary Search Tree. Each node has a Student object and references to its left and right children.
 */
public class BinarySearchTree {

    private TreeNode root;
    private SortingStrategy strategy;

    /**
     * Constructs a new empty Binary Search Tree with the given sorting strategy.
     *
     * @param strategy The strategy used for sorting and comparing students.
     */
    public BinarySearchTree(SortingStrategy strategy)
    {
        this.root = new TreeNode(new NullStudent());
        this.strategy = strategy;
    }

    public TreeNode getRoot() {
        return root;
    }

    public SortingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void add(Student student) {
        root = insert(root, student);
    }

    private TreeNode insert(TreeNode current, Student student) {
        if (current == null || current.isNull()) {
            return new TreeNode(student);
        }

        if (strategy.compare(student, current.student) < 0) {
            current.left = insert(current.left, student);
        } else if (strategy.compare(student, current.student) > 0) {
            current.right = insert(current.right, student);
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
        if (node == null || node.isNull()) return;
        inOrderTraversal(node.left, action);
        action.accept(node.student);
        inOrderTraversal(node.right, action);
    }


    public void traverseWithVisitor(TreeVisitor visitor) {
        if (root != null) {
            root.accept(visitor);  // Start the traversal from the root
        }
    }
}
