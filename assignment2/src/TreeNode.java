public class TreeNode {
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
        if (!(student instanceof NullStudent)) {
            this.left = new TreeNode(new NullStudent());
            this.right = new TreeNode(new NullStudent());
        }
    }

    public Student getStudent() {
        return student;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void accept(TreeVisitor visitor) {
        if (this.student instanceof NullStudent) {
            visitor.visitNullNode(this);
        } else {
            visitor.visitStudentNode(this);
        }

        // No need to check for null because NullStudent represents a null node
        if (this.left != null) {
            this.left.accept(visitor);
        }
        if (this.right != null) {
            this.right.accept(visitor);
        }
    }

    public boolean isNull() {
        return student instanceof NullStudent;
    }

}