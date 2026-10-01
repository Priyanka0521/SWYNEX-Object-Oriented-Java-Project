public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("=================================");

        Student student = new Student(
                101,
                "Priyanka",
                "MSc Computer Science",
                85
        );

        GraduateStudent graduateStudent = new GraduateStudent(
                102,
                "Rahul",
                "MSc Computer Science",
                91,
                "Artificial Intelligence"
        );

        System.out.println("\n--- Student Details ---");
        student.displayDetails();

        System.out.println("\n--- Graduate Student Details ---");
        graduateStudent.displayDetails();

        // Runtime Polymorphism
        System.out.println("\n--- Runtime Polymorphism ---");

        Person person1 = student;
        Person person2 = graduateStudent;

        person1.displayDetails();

        System.out.println();

        person2.displayDetails();
    }
}