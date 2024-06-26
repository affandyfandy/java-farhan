package q1;

import java.math.BigDecimal;

public class Question {
    public void primitiveInt(int primitive1, int primitive2) {
        System.out.println("Primitive Data Type Int:");
        System.out.println("Primitive1 Value: " + primitive1);
        System.out.println("Primitive2 value: " + primitive2);
        primitive2 = 20;
        System.out.println("After change:");
        System.out.println("Primitive1 Value: " + primitive1);
        System.out.println("Primitive2 value: " + primitive2);
    }

    public void primitiveDouble(double price1, double price2) {
        System.out.println("Primitive Data Type Double:");
        System.out.println("Primitive1 Value: " + price1);
        System.out.println("Primitive2 value: " + price2);
        price2 = price1;
        System.out.println("After change:");
        System.out.println("Primitive1 Value: " + price1);
        System.out.println("Primitive2 value: " + price2);
    }

    public void primitiveChar(char letter1, char letter2) {
        System.out.println("Primitive Data Type Char:");
        System.out.println("Primitive1 Value: " + letter1);
        System.out.println("Primitive2 value: " + letter2);
        letter1 = letter2;
        letter2 = 'A';
        System.out.println("After change:");
        System.out.println("Primitive1 Value: " + letter1);
        System.out.println("Primitive2 value: " + letter2);
    }

    public void primitiveBoolean(boolean flag1, boolean flag2) {
        System.out.println("Primitive Data Type Boolean:");
        System.out.println("Primitive1 Value: " + flag1);
        System.out.println("Primitive2 value: " + flag2);
        flag2 = false;
        flag1 = flag2;
        flag2 = true;
        System.out.println("After change:");
        System.out.println("Primitive1 Value: " + flag1);
        System.out.println("Primitive2 value: " + flag2);
    }

    public void referenceBigDecimal(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        bigDecimal1 = bigDecimal1.add(new BigDecimal("10"));
        System.out.println("\nReferences Object BigDecimal:");
        System.out.println("BigDecimal value1: " + bigDecimal1);
        System.out.println("BigDecimal value2: " + bigDecimal2);
        bigDecimal2 = bigDecimal2.add(new BigDecimal("5.5"));
        System.out.println("Setelah menambah nilai bigDecimal2:");
        System.out.println("BigDecimal value1: " + bigDecimal1);
        System.out.println("Nilai bigDecimal2: " + bigDecimal2);
    }

    public void referenceString(String string1, String string2) {
        System.out.println("\nReferences Object String:");
        System.out.println("String Value1: " + string1);
        System.out.println("String value2: " + string2);
        string2 += " World";
        System.out.println("After Change:");
        System.out.println("String Value1: " + string1);
        System.out.println("String value2: " + string2);
    }
}
