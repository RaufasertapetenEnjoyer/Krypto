public class Main2 {

    public static void main (String[] args) {
        long a1 = 504;
        long b1 = 65535;
        long m1 = 65537;
        System.out.println("Example 1:\na = " + a1 + "\nb = " + b1 + "\nm = " + m1);
        System.out.println(a1 + " ^ " + b1 + " mod " + m1 + " = " + repeatedSquaring(a1, b1, m1) + "\n");

        long a2 = 20;
        long b2 = 76549;
        long m2 = 90056;
        System.out.println("Example 2:\na = " + a2 + "\nb = " + b2 + "\nm = " + m2);
        System.out.println(a2 + " ^ " + b2 + " mod " + m2 + " = " + repeatedSquaring(a2, b2, m2) + "\n");

        long a3 = 700;
        long b3 = 12341;
        long m3 = 12343;
        System.out.println("Example 3:\na = " + a3 + "\nb = " + b3 + "\nm = " + m3);
        System.out.println(a3 + " ^ " + b3 + " mod " + m3 + " = " + repeatedSquaring(a3, b3, m3) + "\n");
    }

    /**
     *
     * @param a base
     * @param b exponent
     * @param m modulo
     * @return a^b mod m
     */
    public static long repeatedSquaring(long a, long b, long m) {
        String binary = Long.toBinaryString(b);
        long c = a % m;
        long d = 1;

        System.out.print("exponent in binary: ");
        for (int i = binary.length() - 1; i >= 0;  i--) {
            if (binary.charAt(i) == '1') {
                d = (d * c) % m;
                System.out.print("1");
            } else {
                System.out.print("0");
            }
            c = (c * c) % m;
        }
        System.out.println();
        return d;
    }
}
