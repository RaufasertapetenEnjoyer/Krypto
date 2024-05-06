import java.util.*;

public class CipherCracker {

    private CipherCracker(){}

    public static char[] getKey(char[] encodedText, int length){
        if (length == 0) {
            return null;
        }
        char[] key = new char[length];
        for (int i = 0; i < length; i++) {
            key[i] = getKeyCharacterAtIndex(encodedText, i, length);
        }
        return key;
    }

    public static List<Character> keyHelper(char[] encodedText, int index, int length){
        List<Character> charactersToAnalyze = new ArrayList<>();

        for (int j = index; j < encodedText.length; j += length) {
            charactersToAnalyze.add(encodedText[j]);
        }
        return charactersToAnalyze;
    }

    public static char getKeyCharacterAtIndex(char[] encodedText, int index, int length){
        char encodedWithE = frequencyAnalysisForLetterE(keyHelper(encodedText, index, length));

        int number = (encodedWithE - 65) - ('e' - 97);
        if(number < 0){
            return (char) (number + 123);
        }else {
            return (char) (number + 97);
        }
    }

    public static char frequencyAnalysisForLetterE(List<Character> characters){
        Map<Character, Integer> frequency = new HashMap<>();

        for (Character c : characters){
            if(!frequency.containsKey(c)){
                frequency.put(c, 1);
            }else {
                frequency.put(c, frequency.get(c) + 1);
            }
        }

        List<Integer> occurrences = new ArrayList<>(frequency.values());
        occurrences.sort(Comparator.reverseOrder());
        Integer highestOccurrence = occurrences.getFirst();

        for (Character c : frequency.keySet()){
            if (Objects.equals(frequency.get(c), highestOccurrence)){
                return c;
            }
        }

        return 0;
    }

    public static char[] crackVigenere(char[] encodedText){
        char[] key = getKey(encodedText,
                CipherUtil.kasiskyTest(encodedText).getFirst());

        VigenereCipher cracked = new VigenereCipher(key,
                encodedText);

        return cracked.decode();
    }

    public static char[] crackCaesar(char[] encodedText){
        List<Character> characters = new ArrayList<>();
        for (char c : encodedText){
            characters.add(c);
        }

        char characterForE = frequencyAnalysisForLetterE(characters);
        CaesarCipher cracked = new CaesarCipher((char)((int) characterForE - (int) 'E' + 65) ,
                encodedText);

        return cracked.decode();
    }
}
