import java.util.ArrayList;

public class Student {
    private String name;
    private int age;
    private ArrayList<Subject> subjects;

    public Student(String name, int age, Subject[] subjects) {
        this.name = name;
        this.age = age;
        this.subjects = new ArrayList<>();
        for (Subject subject : subjects) {
            addSubject(subject);
        }
    }

    public void addSubject(Subject newSubject) {
        for (Subject subject : subjects) {
            if (subject.getClassId() == newSubject.getClassId()) {
                System.out.println("Class ID " + newSubject.getClassId() + " already exists for another subject.");
                return;
            }
        }
        subjects.add(newSubject);
    }

    public void learning() {
        System.out.println("Student " + name + " with " + age + " years old " + " is learning:");
        for (Subject subject : subjects) {
            System.out.println("- " + subject.getName() + " for Class " + subject.getClassId());
        }
    }

    public static void main(String[] args) {
        // Create subjects
        Subject math = new Subject("Mathematics", 1);
        Subject physics = new Subject("Physics", 2);
        Subject chemistry = new Subject("Chemistry", 3);
        Subject duplicateMath = new Subject("Advanced Mathematics", 1); // Duplicate classId for testing
        // will be error and not save into list

        // Create a teacher with name and age
        Teacher teacher1 = new Teacher("Tam", 30);

        // Assign a subject to the teacher
        teacher1.assignSubject(math);

        // Print the teaching info
        teacher1.teaching();

        // Create another teacher with name, age, and subject
        Teacher teacher2 = new Teacher("Alice", 28, physics);

        // Print the teaching info for the second teacher
        teacher2.teaching();

        // Create a student with subjects
        Subject[] studentSubjects = { math, physics, chemistry, duplicateMath };
        Student student1 = new Student("John", 20, studentSubjects);

        // Print the learning info for the student
        student1.learning();
    }
}
