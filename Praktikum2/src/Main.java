package src;
import java.util.*;


public class Main {

    private static final double friedLangDE = 0.0762;
    private static final double friedRand = 0.0385;

    public static void main(String[] args) {
        char[] encodeText = "LWFCOSYJYWTWHRYKUGKLHLLOMGMXLPYNABVJJLAWTCVGALUTQBUXLQUKOVZCLRBSNSETPRYPMFDTLEOXSSJILPFLGBULHIBJQBUXJLBAQFJEYIWZQBRTOILLEWTWKMYKQOIBLIOFESITYBMLMRKVSEOTFAZGDIHFUQYTBGBKMUVLPVBSNSETPRYOUFBAPGBKOVNTYITWUHMDYYHKMPVGEAYFZKZGKELSGTMDYFYJQWKTAWYAZKFASIHXPOECTYYKESELPVTMQFJIBRMWDSRCNWNVMJFGEEVDQUVCPGBKFSYTOMYJVSKOAZIJQITWCSYDXWXPUKMLRFVXDMYKASKLHAYAXWTWHRYLIOJMNPUMNSLCKMBJZWTWARYAZWTWZXYZQVZTYSBFQOEVZXQWUZZROINOMGEXJLNNQFXTZWYFTOSTEMWZTOSTUMWZFJVGNIMKQBUPZWCUTBZROXBAQFSXUAYYQBUTZAYYQGUTYZIJYWIAPIALECESLVHPISXTUHYKISXTZHYJNSITPXMZUBKTYQCJXWVVAMWZTOSTUMWZFJVGNIMKQBUPZWQADPVGLMNKJGVXALOFPSIIQEBJQBXTNIHVUSJTTEMUTWETUOUWYDWTUMWZTOSTUMWZFJVGNIMKQBUPZWMAQLJTPXBMZRVGANUZDSEXOVYSDAVTUWWZUQBTUYGMZGQJGILKFCVGLROFPBRROICFQAAPOVBMZRVGABXWEYIXLKYKTOSTPGBFUQYICILYQGJTUAUKPOJLPGBLUUJILMMLIWIHPRXFAQYWPILDMGJIBRMPTSLILRUUTHUXLWYJMFDTLZIFTWVGHYMWUBVQVXMUTOWIZGBAOYVCSEMKFIEHOIOLQBRROXRVUSJTOSYZXSEOBQYJLWKILVHTDWEVLRBWGHVCHGBLISISLRQADRZTZIBSXZVCHYMWDRVMZXUZXIESZXYAZSIQLFYFXOJHLRMAQGFASIHMZGYDLVYFHCDGVXYFWSICIMMRGAJROAUJLSEMOMGEQZYTBXYFMQYIDILVQBNXYHUXGSIHVVAWZRRHZWCWZWVBHPMNQFXTZWYFPOJXZXTAABLCKBQADVRQLREWUBVPUKML".toCharArray();
        double length = friedmannTest(encodeText.length, calculateFriedmannIndexForEncodedText(encodeText));
        System.out.println("Die Länge des verschlüsselten Textes:   " + encodeText.length);
        System.out.println("Das Ergebnis für die Länge ist:         " + length);

        kasiskyTest(encodeText);

        char[] text = "Ocs yif jcs Cvtirsfcv scvsf udscvsv bfivzesocoatsv Rif, jcs Oatstsfszijs tcsoo, jio scvzcks Deuid cv jsf Msjcvi, jio yistfsvj jsf kivzsv Viatx ebbsv yif. So yif mivatmid kivz dssf, mivatmid oioosv jfsc ejsf pcsf Dsgxs jifcv. Ysvv so irsf pedd yif, im tisgbckoxsv zycoatsv zysc gvj jfsc Gtf viatxo, tesfxs miv lsjso Yefx, jio jcs ivjsfsv Kisoxs oikxsv, gfj uim mcx lsjsm cvo Ksonfisat.".toCharArray();
        int number = 8;
        char[] calculatedKey = getKey(encodeText, number);
        System.out.println(calculatedKey);
        System.out.println(decode(encodeText, calculatedKey));
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
        System.out.println(possibleLengths);
        return possibleLengths;
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

    public static char addChars(char uncryptedChar, char keyChar) {
        if(uncryptedChar == ' '){
            return uncryptedChar;
        }

        int additionToAscii = Character.isUpperCase(uncryptedChar) ? 65 : 97;
        int numberToAdd = (uncryptedChar) - additionToAscii + keyChar - 97;
        return (char) ((numberToAdd % 26) + additionToAscii);
    }

    public static char[] decode(char[] textToDecode, char[] key){
        char[] array = new char[textToDecode.length];
        for (int i = 0; i < textToDecode.length; i++) {
            if(textToDecode[i] == ' '){
                array[i] = ' ';
                continue;
            }
            int number = 0;
            if(Character.isUpperCase(textToDecode[i])){
                number = (textToDecode[i] - 65) - (key[i % key.length] - 97);
                if(number < 0){
                    array[i] = (char) (number + 91);
                }else {
                    array[i] = (char) (number + 65);
                }
                continue;
            }
            if(Character.isLowerCase(textToDecode[i])){
                number = (textToDecode[i] - 97) - (key[i % key.length] - 97);
                if(number < 0){
                    array[i] = (char) (number + 123);
                }else {
                    array[i] = (char) (number + 97);
                }
                continue;
            }
        }
        return array;
    }

    public static char[] getKey(char[] encodedText, int length){
        if (length == 0) {
            return null;
        }
        char[] key = new char[length];
        for (int i = 0; i < length; i++) {
            List<Character> charactersToAnalyze = new ArrayList<>();

            for (int j = i; j < encodedText.length; j += length) {
                charactersToAnalyze.add(encodedText[j]);
            }

            char encodedWithE = frequencyAnalysisForLetterE(charactersToAnalyze);

            System.out.println(encodedWithE);
            int number = (encodedWithE - 65) - ('e' - 97);
            if(number < 0){
                key[i] = (char) (number + 123);
            }else {
                key[i] = (char) (number + 97);
            }
        }
        return key;
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
        System.out.println(frequency);
        Integer highestOccurrence = occurrences.getFirst();

        for (Character c : frequency.keySet()){
            if (Objects.equals(frequency.get(c), highestOccurrence)){
                return c;
            }
        }
        return 0;
    }
}