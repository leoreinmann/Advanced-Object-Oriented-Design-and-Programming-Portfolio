import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the BinarySearchTree class to ensure that different sorting strategies
 * (RedIdStrategy, NameStrategy, and GpaStrategy) work as expected.
 */
public class BinarySearchTreeTests {
    @Test
    public void testRedIdStrategy() {
        BinarySearchTree tree = new BinarySearchTree(new RedIdStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        tree.add(s1);
        tree.add(s2);
        tree.add(s3);

        StringBuilder result = new StringBuilder();
        tree.iterate(student -> result.append(student.getRedId()).append(" "));

        assertEquals("12344 12345 12346 ", result.toString());
    }

    @Test
    public void testNameStrategy() {
        BinarySearchTree tree = new BinarySearchTree(new NameStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        tree.add(s1);
        tree.add(s2);
        tree.add(s3);

        StringBuilder result = new StringBuilder();
        tree.iterate(student -> result.append(student.getLastName()).append(student.getFirstName()).append(" "));

        assertEquals("CooperAlice DoeJane DoeJohn ", result.toString());
    }

    @Test
    public void testGpaStrategy() {
        BinarySearchTree tree = new BinarySearchTree(new GpaStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        tree.add(s1);
        tree.add(s2);
        tree.add(s3);

        StringBuilder result = new StringBuilder();
        tree.iterate(student -> result.append((int) Math.round(student.getGpa())).append("/").append(student.getRedId()).append(" "));

        assertEquals("3/12344 3/12345 4/12346 ", result.toString());
    }

    @Test
    public void testNullObjectPattern() {
        BinarySearchTree tree = new BinarySearchTree(new GpaStrategy());

        // Ensure tree initializes with a NullStudent node.
        assertTrue(tree.getRoot().isNull());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        tree.add(s1);

        // Ensure that the root is no longer a NullStudent.
        assertFalse(tree.getRoot().isNull());

        // Ensure that left and right children of root are NullStudent nodes.
        assertTrue(tree.getRoot().left.isNull());
        assertTrue(tree.getRoot().right.isNull());
    }


    @Test
    public void testNullNodeCounterVisitor() {
        BinarySearchTree tree = new BinarySearchTree(new GpaStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        tree.add(s1);  // Adding one student should create two null nodes (left and right).

        NullNodeCounterVisitor nullVisitor = new NullNodeCounterVisitor();
        tree.traverseWithVisitor(nullVisitor);

        assertEquals(2, nullVisitor.getNullCount(), "There should be 2 null nodes after adding one student.");
    }

    @Test
    public void testPathLengthVisitor() {
        BinarySearchTree tree = new BinarySearchTree(new GpaStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);
        tree.add(s1);
        tree.add(s2);
        tree.add(s3);  // This should create a tree with a certain path length distribution.

        PathLengthVisitor pathVisitor = new PathLengthVisitor();
        tree.traverseWithVisitor(pathVisitor);

        // The longest path and average path length should be asserted based on the expected tree structure.
        // For simplicity, let's assume the tree is balanced and the longest path is 2 (root to any leaf).
        assertEquals(2, pathVisitor.getLongestPath(), "Longest path should be 2.");
        assertTrue(pathVisitor.getAveragePathLength() > 1, "Average path length should be greater than 1.");
    }


}
