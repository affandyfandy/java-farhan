package q5;

public class Question {

    private static Question INSTANCE;
    private String info = "Initial info class";

    private Question() {
    }

    public static Question getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Question();
        }

        return INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        Question qsingleton = Question.getInstance();
        qsingleton.showMessage();
    }

}
