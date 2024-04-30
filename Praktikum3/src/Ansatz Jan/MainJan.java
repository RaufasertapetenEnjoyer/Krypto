import java.math.BigInteger;

public class MainJan {
    public static void main(String[] args) {
        char[] text = "KSEXZZGHETHFXTCKJWFRWDEBKTNQKTGUQNIECCQYYQIPYLDWLHUCJRZZFVSYKKGUSFRNKTGOTACCRZCUVOCKPIXTLHFRPDBNLHNNSTTPDJMGHXPPWDFSYOESXECUVOWSKXHHSPLTCUVOROJBPJANHFIIGOLHKSEXZZGHETHFXTCKJWHHUCAKCCEWCPCUVOGFMVCCRJMZYLPILHANGUSFZRIPJWLHCVVELHEBIRWSJRLHKEODGJLHKSXQPPWDJBMUYJIRCCQYKTCUCVGOHFCGVEAWETHFSPNVIALHYFCUPFDJTNXTWQEMZNLHKEODGJKGTCBXMBHFSRWSPCWLGOIPUIIHKRJWMRKXWSJRWSYLWQVIDJEBDMKRSYLHTQVVKTMZMVGGVOAWCDMZCIYHNDHZBTFFYOESIPATWSCPWDLUCDANCVAEHQXTMRJWXTICKEFSYQHFLHKSEXXTHXPPIPCPSZQOQMXTYQQYIVJWKSXQIAGOMBHFINAJHFWLSFLH".toCharArray();
        int[][] key = {{6, 3}, {5, 3}};
        int[][] invertedKey = {{1, 25}, {7, 2}};
        int[][] invertedKeyAuto = inverseOf2x2mod26(key);
        System.out.print("Decrypted: ");
        System.out.print(hillTwo(text, invertedKeyAuto));
        System.out.println("\n");
        String text1 = "Schwarze Löcher im Universum sind Orte der Extreme. Die Masse ist in ihnen so stark zusammengepresst, dass nichts ihrer enorm hohen Anziehungskraft entkommt – nicht einmal Licht. Die Objekte selbst sind unsichtbar. Sie verraten sich jedoch über die Materie, die sie verschlucken: Fällt Materie in ein Schwarzes Loch, heizt sie sich auf Millionen Grad auf und strahlt dann hell. Dieses charakteristische Leuchten können Teleskope registrieren. Supermassereiche Schwarze Löcher erreichen durchaus die millionen-bis milliardenfache Masse unserer Sonne. Genau darum handelt es sich den Forschern zu folge bei den entdeckten kleinen roten Punkten – allerdings sind sie noch nicht unverhältnismäßig groß.";
        System.out.print("Encrypted: ");
        System.out.print(hillTwo(cutString(text1).toCharArray(), key));
        System.out.println("\n");
    }

    public static char[] hillTwo (char[] text, int[][] key) {
        char encodedText[] = new char[text.length % 2 == 0 ? text.length : text.length + 1];
        for (int i = 0; i < text.length; i = i + 2) {
            int[] part = new int[2];
            part[0] = Character.toUpperCase(text[i]) - 65;
            if (i == text.length - 1){
                part[1] = 'X' - 65;
            } else {
                part[1] = Character.toUpperCase(text[i + 1]) - 65;
            }
            int[] result= multiplyMatrixMod26(part, key);
            encodedText[i] = (char)(result[0] + 65);
            encodedText[i + 1] = (char)(result[1] + 65);
        }
        return encodedText;
    }

    public static int[] multiplyMatrixMod26(int[]part, int[][] key) {
        int[] result = new int[2];
        for (int i = 0; i < result.length; i++) {
            result[i] = (part[0] * key[i][0] + part[1] * key[i][1]) % 26;
        }
        return result;
    }

    public static int[][] inverseOf2x2mod26(int[][] key){
        BigInteger[][] numbers = new BigInteger[2][2];
        BigInteger a = BigInteger.valueOf(key[0][0]);
        BigInteger b = BigInteger.valueOf(key[0][1]);
        BigInteger c = BigInteger.valueOf(key[1][0]);
        BigInteger d = BigInteger.valueOf(key[1][1]);
        BigInteger modul = BigInteger.valueOf(26);

        numbers[0][0] = d;
        numbers[1][1] = a;
        numbers[0][1] = b.negate();
        numbers[1][0] = c.negate();

        BigInteger denumerator = a.multiply(d).subtract(b.multiply(c));

        int[][] inverse = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverse[i][j] = (int) numbers[i][j].mod(modul)
                        .multiply(denumerator.modInverse(modul))
                        .mod(modul)
                        .intValue();
            }
        }
        return inverse;
    }

    public static String cutString(String text) {
        text = text.replaceAll("\\s","");
        text = text.replaceAll(",","");
        text = text.replaceAll("\\.","");
        text = text.replaceAll(":","");
        text = text.replaceAll("–","");
        text = text.replaceAll("ä","a");
        text = text.replaceAll("ö","o");
        text = text.replaceAll("ü","u");
        return text;
    }
}