/**
 * Implements the SortingStrategy interface to compare students based on their RedID.
 */
public class RedIdStrategy implements SortingStrategy {

    /**
     * Compares two students based on their RedID values.
     *
     * @param s1 The first student to be compared.
     * @param s2 The second student to be compared.
     * @return A negative integer, zero, or a positive integer if the RedID of the first student is less than, equal to, or greater than the RedID of the second student, respectively.
     */
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getRedId().compareTo(s2.getRedId());
    }
}
