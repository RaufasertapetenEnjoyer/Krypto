import java.util.Arrays;
import java.util.List;

public class Polynomial {

    public List<Integer> polynom;

    public Polynomial(List<Integer> polynom) {
        this.polynom = polynom;
    }

    /**
     * method for polynomial division
     *
     * @param divisor Polynom that should divide the current Polynom
     * @return Array of Polynoms, index 0 is the result and index 1 the rest
     * @throws IllegalArgumentException division by zero or greater divisor throws this exception
     */
    public Polynomial[] divide(Polynomial divisor) throws IllegalArgumentException{
        List<Integer> divisorPolynom = divisor.polynom;
        int divisorDegree = divisorPolynom.size()-1;
        List<Integer> dividendPolynom = this.polynom;
        int dividendDegree = dividendPolynom.size()-1;
        boolean isZero = true;
        for (Integer factor : divisorPolynom) {
            if (factor != 0) {
                isZero = false;
                break;
            }
        }
        if (divisorDegree > dividendDegree || (divisorDegree == dividendDegree && divisorPolynom.get(divisorDegree) > dividendPolynom.get(dividendDegree)) ||isZero) {
            throw new IllegalArgumentException();
        }

        int resultDegree = dividendDegree - divisorDegree;
        Integer[] integerPolynomArr = new Integer[resultDegree+1];

        for(int i = dividendDegree; i >= dividendDegree-resultDegree; i--) {
            int a = Math.round((float)dividendPolynom.get(i) / divisorPolynom.get(divisorDegree));
            integerPolynomArr[i-divisorDegree] = a;
            int m = divisorDegree;
            for(int j = i; j >= i-divisorDegree; j--) {
                dividendPolynom.set(j, dividendPolynom.get(j) - a * divisorPolynom.get(m));
                m--;
            }
        }
        return new Polynomial[]{new Polynomial(Arrays.asList(integerPolynomArr)), new Polynomial(dividendPolynom)};
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = polynom.size()-1; i >= 0; i--) {
            if (polynom.get(i) != 0) {
                builder.append(!builder.isEmpty() ? " + " : "");
                builder.append(polynom.get(i));
                builder.append(i != 0 ? ("x^" + i) : "");
            } else if (polynom.get(i) == 0 && i == 0) {
                builder.append(!builder.isEmpty() ? (" + " + polynom.get(i)) : "");
            }
        }
        String result = builder.toString();
        return result.isEmpty() ? "" : result;
    }

    public String toStringAdvanced() {
        StringBuilder builder = new StringBuilder();
        for (int i = polynom.size()-1; i >= 0; i--) {
            if (polynom.get(i) != 0) {
                builder.append(!builder.isEmpty() ? " + " : "");
                if (polynom.get(i) == -1) {
                    builder.append("-");
                } else if (polynom.get(i) != 1 || i == 0) {
                    builder.append(polynom.get(i));
                }
                builder.append(polynom.get(i));
                if (i != 0 ) {
                    builder.append("x");
                    builder.append(i > 1 ? ("^" + i) : ""); // hübschere Ausgabe
                }
            }
        }
        String result = builder.toString();
        return result.isEmpty() ? "0" : result;
    }
}
