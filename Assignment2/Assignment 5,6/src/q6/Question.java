package q6;

class StudentClass {
    String name;
    double gpa;
}

public class Question {
    public static void main(String[] args) {
        StudentClass s1 = new StudentClass();
        s1.gpa = 3.70;
        s1.name = "Saka";

        StudentClass s2 = new StudentClass();
        s2.gpa = 3.70;
        s2.name = "Saka";

        modifyObject(s1);
        System.out.println("obj.value after modifyObject: " + s1.name + ", " + s1.gpa);

        changeReferenceObject(s2);
        System.out.println("obj.value after changereference: " + s2.name + ", " + s2.gpa);
    }

    public static void modifyObject(StudentClass x) {
        x.name = "Yusuf";
        x.gpa = 3.90;
        
    }

    public static void changeReferenceObject(StudentClass x) {
        x = new StudentClass();
        x.name = "Putra";
        x.gpa = 3.9;
    }

}
