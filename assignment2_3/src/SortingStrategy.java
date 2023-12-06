/**
 * Represents a strategy for comparing two students.
 */
public interface SortingStrategy {

    /**
     * Compares two student objects based on the defined sorting strategy.
     *
     * @param s1 The first student to be compared.
     * @param s2 The second student to be compared.
     * @return A negative integer, zero, or a positive integer if the first student is less than, equal to, or greater than the second student, respectively.
     */
    int compare(Student s1, Student s2);
}
