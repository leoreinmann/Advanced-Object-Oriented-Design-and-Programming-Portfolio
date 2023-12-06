/**
 * Implements the SortingStrategy interface to compare students based on their names.
 * The primary comparison is based on the last name. If the last names are identical, the first names are compared.
 */
public class NameStrategy implements SortingStrategy {

    /**
     * Compares two students first by their last names. If the last names are identical,
     * it then compares their first names.
     *
     * @param s1 The first student to be compared.
     * @param s2 The second student to be compared.
     * @return A negative integer, zero, or a positive integer if the name of the first student
     *         is less than, equal to, or greater than the name of the second student, respectively.
     */
    @Override
    public int compare(Student s1, Student s2) {
        int lastNameComparison = s1.getLastName().compareTo(s2.getLastName());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return s1.getFirstName().compareTo(s2.getFirstName());
    }
}
