/**
 * Represents a student with a first name, last name, RedID, and GPA.
 */
public class Student {
    private final String firstName;
    private final String lastName;
    private final String redId;
    private final double gpa;

    /**
     * Constructs a new student with the given attributes.
     *
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param redId The unique RedID of the student.
     * @param gpa The Grade Point Average of the student.
     */
    public Student(String firstName, String lastName, String redId, double gpa) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.redId = redId;
        this.gpa = gpa;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getRedId() {
        return redId;
    }
    public double getGpa() {
        return gpa;
    }




    /**
     * Returns a string representation of the student, including first name, last name, RedID, and GPA.
     *
     * @return A string representation of the student.
     */    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + redId + ", GPA: " + gpa + ")";
    }
}
