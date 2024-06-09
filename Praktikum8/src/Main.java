import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Polynomial test1 = new Polynomial(Arrays.asList(-20, -2 , 5, 7, 10));
        Polynomial test2 = new Polynomial(Arrays.asList(2, 6 , 3));
        test1.divide(test2);

        Polynomial p1 = new Polynomial(Arrays.asList(0, 0, -12, 1)); // x^3 - 12x + 0x^2 + 0
        Polynomial p2 = new Polynomial(Arrays.asList(-10, 1)); // -10 + x
        p1.divide(p2);
    }
}
