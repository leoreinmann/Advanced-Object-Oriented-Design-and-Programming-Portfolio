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
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        tree.add(s1);
        tree.add(s2);
        tree.add(s3);

        NullNodeCounterVisitor nullCounterVisitor = new NullNodeCounterVisitor();
        tree.getRoot().accept(nullCounterVisitor);

        assertEquals(6, nullCounterVisitor.getCount());
    }

    @Test
    public void testPathLengthVisitor() {
        BinarySearchTree tree = new BinarySearchTree(new GpaStrategy());
        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        tree.add(s1);
        tree.add(s2);
        tree.add(s3);

        PathLengthVisitor pathLengthVisitor = new PathLengthVisitor();
        tree.getRoot().accept(pathLengthVisitor);

        assertEquals(3, pathLengthVisitor.getLongestPath());
        assertTrue(Math.abs(2.0 - pathLengthVisitor.getAveragePathLength()) < 0.001);
    }

}
