public class GraduateStudent extends Student {

    private String specialization;

    public GraduateStudent(int id, String name, String course,
                           double marks, String specialization) {
        super(id, name, course, marks);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void displayDetails() {
        System.out.println("Graduate Student");
        System.out.println("Student ID    : " + getId());
        System.out.println("Name          : " + getName());
        System.out.println("Course        : " + getCourse());
        System.out.println("Marks         : " + getMarks());
        System.out.println("Specialization: " + specialization);
    }
}
