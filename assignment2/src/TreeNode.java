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

    public void accept(TreeNodeVisitor visitor) {
        student.accept(visitor);  // This will require adding an accept() method to the Student and NullStudent classes
        if (left != null && !left.isNull()) {
            if(visitor instanceof PathLengthVisitor) {
                ((PathLengthVisitor) visitor).increaseDepth();
            }
            left.accept(visitor);
            if(visitor instanceof PathLengthVisitor) {
                ((PathLengthVisitor) visitor).decreaseDepth();
            }
        }
        if (right != null && !right.isNull()) {
            if(visitor instanceof PathLengthVisitor) {
                ((PathLengthVisitor) visitor).increaseDepth();
            }
            right.accept(visitor);
            if(visitor instanceof PathLengthVisitor) {
                ((PathLengthVisitor) visitor).decreaseDepth();
            }
        }
    }

    public boolean isNull() {
        return student instanceof NullStudent;
    }

}