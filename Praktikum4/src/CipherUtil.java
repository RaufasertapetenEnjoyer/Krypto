import java.math.BigInteger;
import java.util.*;

public class CipherUtil {
    static final double FRIED_INDEX_GERMAN = 0.0762;
    static final double FRIED_INDEX_RANDOM = 0.0385;

    private CipherUtil(){}

    public static int ggt(int number1, int number2){
        while (number2 != 0) {
            if (number1 > number2) {
                number1 = number1 - number2;
            } else {
                number2 = number2 - number1;
            }
        }
        return number1;
    }

    public static double calculateFriedmannIndex(char[] text){
        int[] occurrences = getOccurrencesCharacters(text);
        int sum = 0;
        for (int occurrence : occurrences) {
            sum += occurrence * (occurrence - 1);
        }
        return ((double) 1 / (text.length * (text.length - 1))) * sum;
    }

    public static int[] getOccurrencesCharacters(char[] text){
        int[] occurrences = new int[26];
        for (int i = 'A'; i <= 'Z'; i++) {
            for (char c : text) {
                if (c == (char) i) {
                    occurrences[i - 'A']++;
                }
            }
        }
        return occurrences;
    }

    public static double friedmannTest(char[] text, double friedIndexLang, double friedCipher){
        return text.length * (friedIndexLang - FRIED_INDEX_RANDOM)
                /
                (friedCipher * (text.length - 1) + friedIndexLang - (text.length * FRIED_INDEX_RANDOM));
    }

    private static List<Integer> getDistanceOfCommonWords(char[] text) {
        List<Integer> distanceList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < text.length; j++) {
                if(j != i){
                    if(text[i] == text[j]){
                        int length = 0;
                        while((i + length < text.length && j + length < text.length) && text[i + length] == text[j + length]){
                            length++;
                        }

                        if(length > 3){
                            Integer distance = (j >= i ? j - i : i - j);
                            if(!distanceList.contains(distance)){
                                distanceList.add(distance);
                            }
                        }
                    }
                }
            }
        }
        return distanceList;
    }

    public static List<Integer> kasiskyTest(char[] text){
        List<Integer> distanceList = getDistanceOfCommonWords(text);
        Map<Integer, Integer> mapOccurrences = new HashMap<>();

        for (int i = 0; i < distanceList.size(); i++) {
            for (int j = i + 1; j < distanceList.size(); j++) {
                Integer ggt = ggt(distanceList.get(i), distanceList.get(j));
                if(!mapOccurrences.containsKey(ggt)){
                    mapOccurrences.put(ggt, 1);
                }else{
                    mapOccurrences.put(ggt, mapOccurrences.get(ggt) + 1);
                }
            }
        }

        List<Integer> possibleLengths = new ArrayList<>();
        List<Integer> values = new ArrayList<>(mapOccurrences.values());

        values.sort(Collections.reverseOrder());

        for (int i = 0; i < 3; i++) {
            for (Integer key : mapOccurrences.keySet()){
                if(values.get(i).equals(mapOccurrences.get(key)) && !possibleLengths.contains(key)){
                    possibleLengths.add(key);
                    break;
                }
            }
        }
        return possibleLengths;
    }

    public static char[][] inverseOf2x2mod26(char[][] matrix){
        BigInteger[][] numbers = new BigInteger[2][2];
        BigInteger a = BigInteger.valueOf(matrix[0][0]);
        BigInteger b = BigInteger.valueOf(matrix[0][1]);
        BigInteger c = BigInteger.valueOf(matrix[1][0]);
        BigInteger d = BigInteger.valueOf(matrix[1][1]);
        BigInteger modul = BigInteger.valueOf(26);

        numbers[0][0] = d;
        numbers[1][1] = a;
        numbers[0][1] = b.negate();
        numbers[1][0] = c.negate();

        BigInteger denumerator = a.multiply(d).subtract(b.multiply(c));

        char[][] inverse = new char[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverse[i][j] = (char) numbers[i][j].mod(modul)
                        .multiply(denumerator.modInverse(modul))
                        .mod(modul)
                        .intValue();
            }
        }
        return inverse;
    }
}
