import q1.Question;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws Exception {
        Question question = new Question();

        int primitive1 = 10;
        int primitive2 = primitive1;
        question.primitiveInt(primitive1, primitive2);

        double price1 = 20.5;
        double price2 = 10.5;
        question.primitiveDouble(price1, price2);

        char letter1 = 'A';
        char letter2 = 'A';
        question.primitiveChar(letter1, letter2);

        boolean flag1 = true;
        boolean flag2 = false;
        question.primitiveBoolean(flag1, flag2);

        BigDecimal bigDecimal1 = new BigDecimal("10.5");
        BigDecimal bigDecimal2 = bigDecimal1;
        question.referenceBigDecimal(bigDecimal1, bigDecimal2);

        String string1 = "Hello";
        String string2 = string1;
        question.referenceString(string1, string2);
    }
}
