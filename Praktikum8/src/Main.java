import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(Arrays.asList(1, 1 , 0, 1, 1, 0, 0, 0, 1));
        Polynomial p2 = new Polynomial(Arrays.asList(1, 1));
        Polynomial[] result = p1.divide(p2);
        System.out.println("Ergebnis: " + result[0].toString());
        System.out.println("Rest: " + result[1].toString());
    }
}
