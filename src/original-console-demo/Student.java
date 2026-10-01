public class Student extends Person {

    private String course;
    private double marks;

    public Student(int id, String name, String course, double marks) {
        super(id, name);
        this.course = course;
        this.marks = marks;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Polymorphism - method overriding
    @Override
    public void displayDetails() {
        System.out.println("Student ID : " + getId());
        System.out.println("Name       : " + getName());
        System.out.println("Course     : " + course);
        System.out.println("Marks      : " + marks);
    }
}