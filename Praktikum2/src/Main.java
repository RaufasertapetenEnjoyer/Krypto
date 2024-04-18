package src;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    private static final double friedLangDE = 0.0762;
    private static final double friedRand = 0.0385;

    public static void main(String[] args) {
        char[] encodeText = "LWFCOSYJYWTWHRYKUGKLHLLOMGMXLPYNABVJJLAWTCVGALUTQBUXLQUKOVZCLRBSNSETPRYPMFDTLEOXSSJILPFLGBULHIBJQBUXJLBAQFJEYIWZQBRTOILLEWTWKMYKQOIBLIOFESITYBMLMRKVSEOTFAZGDIHFUQYTBGBKMUVLPVBSNSETPRYOUFBAPGBKOVNTYITWUHMDYYHKMPVGEAYFZKZGKELSGTMDYFYJQWKTAWYAZKFASIHXPOECTYYKESELPVTMQFJIBRMWDSRCNWNVMJFGEEVDQUVCPGBKFSYTOMYJVSKOAZIJQITWCSYDXWXPUKMLRFVXDMYKASKLHAYAXWTWHRYLIOJMNPUMNSLCKMBJZWTWARYAZWTWZXYZQVZTYSBFQOEVZXQWUZZROINOMGEXJLNNQFXTZWYFTOSTEMWZTOSTUMWZFJVGNIMKQBUPZWCUTBZROXBAQFSXUAYYQBUTZAYYQGUTYZIJYWIAPIALECESLVHPISXTUHYKISXTZHYJNSITPXMZUBKTYQCJXWVVAMWZTOSTUMWZFJVGNIMKQBUPZWQADPVGLMNKJGVXALOFPSIIQEBJQBXTNIHVUSJTTEMUTWETUOUWYDWTUMWZTOSTUMWZFJVGNIMKQBUPZWMAQLJTPXBMZRVGANUZDSEXOVYSDAVTUWWZUQBTUYGMZGQJGILKFCVGLROFPBRROICFQAAPOVBMZRVGABXWEYIXLKYKTOSTPGBFUQYICILYQGJTUAUKPOJLPGBLUUJILMMLIWIHPRXFAQYWPILDMGJIBRMPTSLILRUUTHUXLWYJMFDTLZIFTWVGHYMWUBVQVXMUTOWIZGBAOYVCSEMKFIEHOIOLQBRROXRVUSJTOSYZXSEOBQYJLWKILVHTDWEVLRBWGHVCHGBLISISLRQADRZTZIBSXZVCHYMWDRVMZXUZXIESZXYAZSIQLFYFXOJHLRMAQGFASIHMZGYDLVYFHCDGVXYFWSICIMMRGAJROAUJLSEMOMGEQZYTBXYFMQYIDILVQBNXYHUXGSIHVVAWZRRHZWCWZWVBHPMNQFXTZWYFPOJXZXTAABLCKBQADVRQLREWUBVPUKML".toCharArray();
        double length = friedmannTest(encodeText.length, calculateFriedmannIndexForEncodedText(encodeText));
        System.out.println(length);
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
            if(Character.isUpperCase(textToDecode[i])){
                int number = (textToDecode[i] - 65) - (key[i % key.length] - 97);
                array[i] = (char) (number + 65);
                continue;
            }
            if(Character.isLowerCase(textToDecode[i])){
                int number = (textToDecode[i] - 97) - (key[i % key.length] - 97);
                if(number < 0){
                    array[i] = (char) (number + 123);
                }else {
                    array[i] = (char) (number + 97);
                }
            }
        }
        return array;
    }

    public static int findDistances(char[] decryptedText) {
        int distance = 0;
        int length = 0;
        char[] sequence;
        for (int i = 0; i < decryptedText.length; i++) {
            for (int j = 0; j < decryptedText.length; j++) {
                if (j != i) {
                    if (decryptedText[i] == decryptedText[j]) {
                        length++;
                        if(decryptedText[i+1] == decryptedText[j+1]){
                            length++;
                            if(decryptedText[i+2] == decryptedText[j+2]){
                                length++;
                                distance = j - i - 1;
                                sequence = new char[length];
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

}

/** Text für 2.
 *   LWFCOSYJYWTWHRYKUGKLHLLOMGMXLPYNABVJJLAWTCVGALUTQ
 *   BUXLQUKOVZCLRBSNSETPRYPMFDTLEOXSSJILPFLGBULHIBJQBUXJL
 *   BAQFJEYIWZQBRTOILLEWTWKMYKQOIBLIOFESITYBMLMRKVSEOTF
 *   AZGDIHFUQYTBGBKMUVLPVBSNSETPRYOUFBAPGBKOVNTYITWUHM
 *   DYYHKMPVGEAYFZKZGKELSGTMDYFYJQWKTAWYAZKFASIHXPOECT
 *   YYKESELPVTMQFJIBRMWDSRCNWNVMJFGEEVDQUVCPGBKFSYTOM
 *   YJVSKOAZIJQITWCSYDXWXPUKMLRFVXDMYKASKLHAYAXWTWHRY
 *   LIOJMNPUMNSLCKMBJZWTWARYAZWTWZXYZQVZTYSBFQOEVZXQ
 *   WUZZROINOMGEXJLNNQFXTZWYFTOSTEMWZTOSTUMWZFJVGNIM
 *   KQBUPZWCUTBZROXBAQFSXUAYYQBUTZAYYQGUTYZIJYWIAPIALEC
 *   ESLVHPISXTUHYKISXTZHYJNSITPXMZUBKTYQCJXWVVAMWZTOST
 *   UMWZFJVGNIMKQBUPZWQADPVGLMNKJGVXALOFPSIIQEBJQBXTNIH
 *   VUSJTTEMUTWETUOUWYDWTUMWZTOSTUMWZFJVGNIMKQBUPZ
 *   WMAQLJTPXBMZRVGANUZDSEXOVYSDAVTUWWZUQBTUYGMZGQJ
 *   GILKFCVGLROFPBRROICFQAAPOVBMZRVGABXWEYIXLKYKTOSTPG
 *   BFUQYICILYQGJTUAUKPOJLPGBLUUJILMMLIWIHPRXFAQYWPILDMG
 *   JIBRMPTSLILRUUTHUXLWYJMFDTLZIFTWVGHYMWUBVQVXMUTOW
 *   IZGBAOYVCSEMKFIEHOIOLQBRROXRVUSJTOSYZXSEOBQYJLWKILV
 *   HTDWEVLRBWGHVCHGBLISISLRQADRZTZIBSXZVCHYMWDRVMZXU
 *   ZXIESZXYAZSIQLFYFXOJHLRMAQGFASIHMZGYDLVYFHCDGVXYFWS
 *   ICIMMRGAJROAUJLSEMOMGEQZYTBXYFMQYIDILVQBNXYHUXGSIHV
 *   VAWZRRHZWCWZWVBHPMNQFXTZWYFPOJXZXTAABLCKBQADVRQ
 *   LREWUBVPUKML
 */