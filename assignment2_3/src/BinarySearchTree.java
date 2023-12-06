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

    /**
     * Adds a new student to the binary search tree using the specified sorting strategy.
     * This method initiates the insertion process starting from the root of the tree.
     *
     * @param student The student to be added to the tree.
     */
    public void add(Student student) {
        root = insert(root, student);
    }

    /**
     * Recursively inserts a new student into the binary search tree in the correct position according to the sorting strategy.
     * If the current node is null or represents a NullStudent, a new TreeNode with the student is returned.
     * Otherwise, the method compares the student with the current node and decides to insert either to the left or right.
     *
     * @param current The current TreeNode being inspected or compared against.
     * @param student The student to insert into the tree.
     * @return The TreeNode after insertion, which may be a new node or the current node if no insertion occurs.
     */
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

    /**
     * Initiates a traversal of the binary search tree with the provided visitor. The visitor's methods are called
     * for each node in the tree according to the Visitor pattern, allowing for various operations to be performed
     * on the tree's nodes, such as calculations or modifications.
     *
     * @param visitor The TreeVisitor instance whose methods will be called on each node of the tree.
     */
    public void traverseWithVisitor(TreeVisitor visitor) {
        if (root != null) {
            root.accept(visitor);  // Start the traversal from the root
        }
    }
}
