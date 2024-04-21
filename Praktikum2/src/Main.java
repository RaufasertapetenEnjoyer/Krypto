
import javax.sound.midi.Soundbank;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {

    private static final double friedLangDE = 0.0762;
    private static final double friedRand = 0.0385;

    public static void main(String[] args) {
        char[] encodeText = "LWFCOSYJYWTWHRYKUGKLHLLOMGMXLPYNABVJJLAWTCVGALUTQBUXLQUKOVZCLRBSNSETPRYPMFDTLEOXSSJILPFLGBULHIBJQBUXJLBAQFJEYIWZQBRTOILLEWTWKMYKQOIBLIOFESITYBMLMRKVSEOTFAZGDIHFUQYTBGBKMUVLPVBSNSETPRYOUFBAPGBKOVNTYITWUHMDYYHKMPVGEAYFZKZGKELSGTMDYFYJQWKTAWYAZKFASIHXPOECTYYKESELPVTMQFJIBRMWDSRCNWNVMJFGEEVDQUVCPGBKFSYTOMYJVSKOAZIJQITWCSYDXWXPUKMLRFVXDMYKASKLHAYAXWTWHRYLIOJMNPUMNSLCKMBJZWTWARYAZWTWZXYZQVZTYSBFQOEVZXQWUZZROINOMGEXJLNNQFXTZWYFTOSTEMWZTOSTUMWZFJVGNIMKQBUPZWCUTBZROXBAQFSXUAYYQBUTZAYYQGUTYZIJYWIAPIALECESLVHPISXTUHYKISXTZHYJNSITPXMZUBKTYQCJXWVVAMWZTOSTUMWZFJVGNIMKQBUPZWQADPVGLMNKJGVXALOFPSIIQEBJQBXTNIHVUSJTTEMUTWETUOUWYDWTUMWZTOSTUMWZFJVGNIMKQBUPZWMAQLJTPXBMZRVGANUZDSEXOVYSDAVTUWWZUQBTUYGMZGQJGILKFCVGLROFPBRROICFQAAPOVBMZRVGABXWEYIXLKYKTOSTPGBFUQYICILYQGJTUAUKPOJLPGBLUUJILMMLIWIHPRXFAQYWPILDMGJIBRMPTSLILRUUTHUXLWYJMFDTLZIFTWVGHYMWUBVQVXMUTOWIZGBAOYVCSEMKFIEHOIOLQBRROXRVUSJTOSYZXSEOBQYJLWKILVHTDWEVLRBWGHVCHGBLISISLRQADRZTZIBSXZVCHYMWDRVMZXUZXIESZXYAZSIQLFYFXOJHLRMAQGFASIHMZGYDLVYFHCDGVXYFWSICIMMRGAJROAUJLSEMOMGEQZYTBXYFMQYIDILVQBNXYHUXGSIHVVAWZRRHZWCWZWVBHPMNQFXTZWYFPOJXZXTAABLCKBQADVRQLREWUBVPUKML".toCharArray();
        double length = friedmannTest(encodeText.length, calculateFriedmannIndexForEncodedText(encodeText));

        System.out.println("Das Ergebnis für die mögliche Länge, ermittelt mittels Friedmanntest, ist:         " + length);

        List<Integer> possibleKeyLengths = kasiskyTest(encodeText);

        System.out.println("Die drei längen mit der höchsten Häufigkeit: " + possibleKeyLengths.toString() + "\n");
        System.out.println("Schritt 1: Alle längen testen");

        for (int i = 0; i < possibleKeyLengths.size(); i++) {
            char[] calculatedKey = getKey(encodeText, possibleKeyLengths.get(i));

            System.out.println("Für Länge: " + possibleKeyLengths.get(i));
            printKeyAndDecodedText(encodeText, calculatedKey);
        }

        System.out.println("\nSchritt 2: Länge 8 passt am ehesten, aber der letzte Buchstabe ist falsch. Teste das Wort für alle möglichen Buchstabenfolgen");

        char[] calculatedKey = getKey(encodeText, possibleKeyLengths.getFirst());
        int index = 7;
        Set<Character> possibleLetters = frequencyAnalysisForLetterESet(keyHelper(encodeText, index, 8));

        for (Character c : possibleLetters) {
            calculatedKey[index] = c;
            printKeyAndDecodedText(encodeText, calculatedKey);
        }

        System.out.println("\nSchritt 3: Der Buchstabe s passt am besten. Die finale Lösung lautet: ");
        calculatedKey[7] = 's';
        printKeyAndDecodedText(encodeText, calculatedKey);

        char[] decodedText = decode(encodeText, calculatedKey);
        String filePath = "txt/decodedText.txt";
        try{
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write("Der Key Lautet: ");
            fileWriter.write(calculatedKey);
            fileWriter.write("\nDer entschlüsselte Text lautet: ");
            fileWriter.write(decodedText);
            fileWriter.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void printKeyAndDecodedText(char[] encodedText, char[] key){
        System.out.print("Der Key Lautet:");
        System.out.println(key);
        System.out.print("Der entschlüsselte Text lautet: ");
        System.out.println(decode(encodedText, key));
        System.out.println();
    }
    public static int[] countOccurrencesOfAllCharacters(char[] encodedText){
        int[] occurrences = new int[26];
        for (int i = 'A'; i <= 'Z'; i++) {
            for (char c : encodedText) {
                if (c == (char) i) {
                    occurrences[i - 'A']++;
                }
            }
        }
        return occurrences;
    }

    public static double calculateFriedmannIndexForEncodedText(char[] encodedText){
        int[] occurrences = countOccurrencesOfAllCharacters(encodedText);
        int sum = 0;
        for (int occurrence : occurrences) {
            sum += occurrence * (occurrence - 1);
        }
        return ((double) 1 / (encodedText.length * (encodedText.length - 1))) * sum;
    }
    public static double friedmannTest(int lengthEncodedText, double friedChiffre){
        return lengthEncodedText * (friedLangDE - friedRand)
                /
                (friedChiffre * (lengthEncodedText - 1) + friedLangDE - (lengthEncodedText * friedRand));
    }

    private static List<Integer> getDistanceOfCommonWords(char[] encodedText) {
        List<Integer> distanceList = new ArrayList<>();
        for (int i = 0; i < encodedText.length; i++) {
            for (int j = 0; j < encodedText.length; j++) {
                if(j != i){
                    if(encodedText[i] == encodedText[j]){
                        int length = 0;
                        while((i + length < encodedText.length && j + length < encodedText.length) && encodedText[i + length] == encodedText[j + length]){
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

    private static int ggt(int zahl1, int zahl2) {
        while (zahl2 != 0) {
            if (zahl1 > zahl2) {
                zahl1 = zahl1 - zahl2;
            } else {
                zahl2 = zahl2 - zahl1;
            }
        }
        return zahl1;
    }

    public static List<Integer> kasiskyTest(char[] encodedText){
        List<Integer> distanceList = getDistanceOfCommonWords(encodedText);
        System.out.println("Die List an Abständen für Wörter mit einer länge von min. drei: " + distanceList);

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

        System.out.println("Die Map an ggTs von den Abständen lautet: " + mapOccurrences);

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

    public static char getKeyCharacterAtIndex(char[] encodedText, int index, int length){
        char encodedWithE = frequencyAnalysisForLetterE(keyHelper(encodedText, index, length));

        int number = (encodedWithE - 65) - ('e' - 97);
        if(number < 0){
            return (char) (number + 123);
        }else {
            return (char) (number + 97);
        }
    }

    public static List<Character> keyHelper(char[] encodedText, int index, int length){
        List<Character> charactersToAnalyze = new ArrayList<>();

        for (int j = index; j < encodedText.length; j += length) {
            charactersToAnalyze.add(encodedText[j]);
        }
        return charactersToAnalyze;
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

    public static Set<Character> frequencyAnalysisForLetterESet(List<Character> characters){
        Map<Character, Integer> frequency = new HashMap<>();

        for (Character c : characters){
            if(!frequency.containsKey(c)){
                frequency.put(c, 1);
            }else {
                frequency.put(c, frequency.get(c) + 1);
            }
        }

        Set<Character> characterForKey = new HashSet<>();

        for(Character encodedWithE : frequency.keySet()){
            int number = (encodedWithE - 65) - ('e' - 97);
            if(number < 0){
                characterForKey.add ((char) (number + 123));
            }else {
                characterForKey.add ((char) (number + 97));
            }
        }
        return characterForKey;
    }

    public static char[] decode(char[] textToDecode, char[] key){
        char[] array = new char[textToDecode.length];
        for (int i = 0; i < textToDecode.length; i++) {
            if(textToDecode[i] == ' '){
                array[i] = ' ';
                continue;
            }
            int additionToAscii = Character.isUpperCase(textToDecode[i]) ? 65 : 97;
            int number = (textToDecode[i] - additionToAscii) - (key[i % key.length] - 97);
            if(number < 0){
                array[i] = (char) (number + additionToAscii + 26);
            }else {
                array[i] = (char) (number + additionToAscii);
            }
        }
        return array;
    }
}