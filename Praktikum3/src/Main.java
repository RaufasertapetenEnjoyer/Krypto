import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static char[] hillChiffre(char[] textToDecode, char[][] key, boolean encode){
        if(!encode){
            key = inverseOf2x2mod26(key);
        }

        char[] encodeText = new char[textToDecode.length];
        char[] subStr = null;

        for (int i = 0; i < textToDecode.length; i++) {
            if(i % key.length == 0){
                subStr = new char[key.length];
                System.arraycopy(textToDecode, i, subStr, 0, key.length);
            }

            int sum = 0;

            for (int j = 0; j < key.length; j++) {
                sum += (int) (key[i % key.length][j]) * (subStr[j] % 65);
            }
            encodeText[i] = (char) ((sum % 26) + 65);
        }
        return encodeText;
    }

    public static char[][] inverseOf2x2mod26(char[][] key){
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

    public static void main(String[] args) {
        char[][] key = {
                {6, 3},
                {5, 3}
        };

        char[] encoded = "KSEXZZGHETHFXTCKJWFRWDEBKTNQKTGUQNIECCQYYQIPYLDWLHUCJRZZFVSYKKGUSFRNKTGOTACCRZCUVOCKPIXTLHFRPDBNLHNNSTTPDJMGHXPPWDFSYOESXECUVOWSKXHHSPLTCUVOROJBPJANHFIIGOLHKSEXZZGHETHFXTCKJWHHUCAKCCEWCPCUVOGFMVCCRJMZYLPILHANGUSFZRIPJWLHCVVELHEBIRWSJRLHKEODGJLHKSXQPPWDJBMUYJIRCCQYKTCUCVGOHFCGVEAWETHFSPNVIALHYFCUPFDJTNXTWQEMZNLHKEODGJKGTCBXMBHFSRWSPCWLGOIPUIIHKRJWMRKXWSJRWSYLWQVIDJEBDMKRSYLHTQVVKTMZMVGGVOAWCDMZCIYHNDHZBTFFYOESIPATWSCPWDLUCDANCVAEHQXTMRJWXTICKEFSYQHFLHKSEXXTHXPPIPCPSZQOQMXTYQQYIVJWKSXQIAGOMBHFINAJHFWLSFLH".toCharArray();
        char[] decoded = hillChiffre(encoded, key, false);

        System.out.print("Der decoded Text ist: ");
        System.out.println(decoded);

        System.out.print("Der encoded Text ist: ");
        System.out.println(hillChiffre(decoded, key, true));

        String filePath = "txt/decodedText.txt";
        try{
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write("Der Key Lautet: ");

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    fileWriter.write((char)(key[i][j] + 65));
                }
            }

            fileWriter.write("\nDer entschlüsselte Text lautet: ");
            fileWriter.write(decoded);
            fileWriter.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
