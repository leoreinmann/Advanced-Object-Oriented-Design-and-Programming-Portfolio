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
}
