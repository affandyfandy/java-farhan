class Subject {
    private String name;
    private int classId;

    public Subject(String name, int classId) {
        this.name = name;
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }
}

public class Teacher {
    private String name;
    private int age;
    private Subject subject;

    // Constructor to create a teacher with name and age
    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Constructor to create a teacher with a subject
    public Teacher(String name, int age, Subject subject) {
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    // Method to assign a subject to the teacher
    public void assignSubject(Subject subject) {
        this.subject = subject;
    }

    public void teaching() {
        if (subject != null) {
            System.out.println(
                    "Teacher " + name + " with " + age + " years old" + " teaching " + subject.getName()
                            + " for Class " + subject.getClassId());
        } else {
            System.out.println("Teacher " + name + " with " + age + " years old" + " has no subject assigned.");
        }
    }

    public static void main(String[] args) {
        // Create a subject
        Subject math = new Subject("Mathematics", 1);

        // Create a teacher with name and age
        Teacher teacher1 = new Teacher("Tam", 30);
        // before assign to subject
        teacher1.teaching();
        // Assign a subject to the teacher
        teacher1.assignSubject(math);
        // after assign to subject
        // Print the teaching info 
        teacher1.teaching();

        // Create another teacher with name, age, and subject
        Teacher teacher2 = new Teacher("Alice", 28, new Subject("Physics", 2));

        // Print the teaching info for the second teacher
        teacher2.teaching();
    }
}
