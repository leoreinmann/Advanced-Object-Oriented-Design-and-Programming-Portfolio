/**
 * Implements the SortingStrategy interface to compare students based on their GPA.
 * The primary comparison is based on the rounded GPA values. If the GPAs are identical,
 * the students are then compared based on their RedID.
 */
public class GpaStrategy implements SortingStrategy {

    /**
     * Compares two students first by their rounded GPA values. If the GPAs are identical,
     * it then compares them based on their RedID.
     *
     * @param s1 The first student to be compared.
     * @param s2 The second student to be compared.
     * @return A negative integer, zero, or a positive integer if the GPA of the first student
     *         is less than, equal to, or greater than the GPA of the second student, respectively.
     *         If GPAs are identical, comparison is based on their RedID.
     */
    @Override
    public int compare(Student s1, Student s2) {
        int gpaComparison = Integer.compare((int) Math.round(s1.getGpa()), (int) Math.round(s2.getGpa()));
        if (gpaComparison != 0) {
            return gpaComparison;
        }
        return s1.getRedId().compareTo(s2.getRedId());
    }
}
