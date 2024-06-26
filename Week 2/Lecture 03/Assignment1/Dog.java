public class Dog {
    private String name;
    private String color;
    private String breed;

    public Dog(String name, String color, String breed) {
        this.name = name;
        this.breed = breed;
        this.color = color;
    }

    private void wagging() {
        System.out.println(name + " with " + color + " fur, a " + breed + ", is wagging its tail.");
    }

    private void barking() {
        System.out.println(name + " the " + breed + " is barking.");
    }

    private void eating() {
        System.out.println(name + " the " + breed + " is eating.");
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("Buddy", "Brown", "Labrador");
        Dog dog2 = new Dog("Max", "Black", "German Shepherd");

        dog1.wagging();
        dog1.barking();
        dog1.eating();

        dog2.wagging();
        dog2.barking();
        dog2.eating();
    }
}
