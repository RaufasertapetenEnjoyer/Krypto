import java.util.List;

public class Polynomial {

    public List<Integer> polynom;

    public Polynomial(List<Integer> polynom) {
        this.polynom = polynom;
    }

    public Polynomial[] divide(Polynomial divisor) {
        return null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = polynom.size()-1; i >= 0; i--) {
            if (polynom.get(i) != 0) {
                builder.append(!builder.isEmpty() ? " + " : "");
                if (polynom.get(i) == -1) {
                    builder.append("-");
                } else if (polynom.get(i) != 1 || i == 0) {
                    builder.append(polynom.get(i));
                }
                if (i != 0 ) {
                    builder.append("x");
                    if (i > 1) {
                        builder.append("^" + i);
                    }
                }
            }
        }
        String result = builder.toString();
        return result.isEmpty() ? "0" : result;
    }


}
