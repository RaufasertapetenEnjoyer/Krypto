import java.util.ArrayList;
import java.util.List;

public class Main {
    public static long powerModBin(long base, long exponent, long m) {
        long result = 1;
        base %= m;

        while (exponent > 0) {
            // Wenn das aktuelle Bit 1 ist, multipliziere das Ergebnis mit base modulo m
            if ((exponent & 1) == 1) {
                result = (result * base) % m;
            }
            // Quadriere base modulo m für das nächste Bit des Exponenten
            base = (base * base) % m;
            // Verschiebe den Exponenten um eine Stelle nach rechts (äquivalent zu exponent /= 2)
            exponent >>= 1;
        }

        return result;
    }

    public static long powerMod(long base, long exponent, long m) {
        List<Integer> list = new ArrayList<>();
        while (exponent != 2){
            if(exponent % 2 == 0){
                exponent /= 2;
                list.add(2);
            }else {
                exponent--;
                list.add(1);
            }
        }

        List<Integer> reversed = list.reversed();
        long result = (base * base) % m;

        for (Integer integer : reversed){
            if(integer == 2){
                result = (result * result) % m;
            }else{
                result = (base * result) % m;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long base = 700;
        long exponent = 12341;
        long m = 12343;

        long result = powerModBin(base, exponent, m);
        long result2 = powerMod(base, exponent, m);
        System.out.println("Das Ergebnis von " + base + "^" + exponent + " mod " + m + " ist: " + result);
        System.out.println("Das Ergebnis von " + base + "^" + exponent + " mod " + m + " ist: " + result2);
    }
}
