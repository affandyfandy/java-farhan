public class Try {

    interface FirstAbility {
        public default boolean doSomething() {
            return true;
        }
    }

    interface SecondAbility {
        public default boolean doSomething() {
            return true;
        }
    }

    class Dupe implements FirstAbility, SecondAbility {
        @Override
        public boolean doSomething() {
            return true;
        }
    }

    public static void main(String[] args) {
        Try ddif = new Try();
        Dupe dupe = ddif.new Dupe();
        System.out.println(dupe.doSomething());

    }
}