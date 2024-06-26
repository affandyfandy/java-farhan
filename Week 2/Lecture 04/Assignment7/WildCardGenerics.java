package Assignment7;

// Dengan wildcard
class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

public class WildCardGenerics {
    public static void printBoxContent(Box<?> box) {
        System.out.println("Box content: " + box.getContent());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>(123);
        printBoxContent(integerBox); // Berfungsi untuk Box<Integer>

        Box<String> stringBox = new Box<>("Hello");
        printBoxContent(stringBox); // Berfungsi untuk Box<String>
    }
}
