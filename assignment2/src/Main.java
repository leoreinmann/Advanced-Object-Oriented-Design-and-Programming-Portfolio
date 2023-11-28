public class Main {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree(new RedIdStrategy());

        Student s1 = new Student("John", "Doe", "12345", 3.2);
        Student s2 = new Student("Jane", "Doe", "12346", 3.7);
        Student s3 = new Student("Alice", "Cooper", "12344", 3.2);

        binarySearchTree.add(s1);
        binarySearchTree.add(s2);
        binarySearchTree.add(s3);

        // Sorted by RedID
        StringBuilder resultRedId = new StringBuilder();
        binarySearchTree.iterate(student -> resultRedId.append(student.getRedId()).append(" ").append(student.getLastName()).append(" ").append(student.getFirstName()).append("\n"));
        System.out.println("Students sorted by RedID: \n" +
                resultRedId + "\n");

        // Sorted by GPA
        binarySearchTree.setStrategy(new GpaStrategy());
        StringBuilder resultGpa = new StringBuilder();
        binarySearchTree.iterate(student -> resultGpa.append(student.getGpa()).append(" ").append(student.getLastName()).append(" ").append(student.getFirstName()).append("\n"));
        System.out.println("Students sorted by GPA: \n" +
                resultGpa + "\n");

        // Sorted by name
        binarySearchTree.setStrategy(new NameStrategy());
        StringBuilder resultName = new StringBuilder();
        binarySearchTree.iterate(student -> resultName.append(student.getLastName()).append(" ").append(student.getFirstName()).append("\n"));
        System.out.println("Students sorted by name: \n" +
                resultName);


    }
}
