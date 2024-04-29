public class Main {
    public static void main(String[] args) {
        char[] text = "KSEXZZGHETHFXTCKJWFRWDEBKTNQKTGUQNIECCQYYQIPYLDWLHUCJRZZFVSYKKGUSFRNKTGOTACCRZCUVOCKPIXTLHFRPDBNLHNNSTTPDJMGHXPPWDFSYOESXECUVOWSKXHHSPLTCUVOROJBPJANHFIIGOLHKSEXZZGHETHFXTCKJWHHUCAKCCEWCPCUVOGFMVCCRJMZYLPILHANGUSFZRIPJWLHCVVELHEBIRWSJRLHKEODGJLHKSXQPPWDJBMUYJIRCCQYKTCUCVGOHFCGVEAWETHFSPNVIALHYFCUPFDJTNXTWQEMZNLHKEODGJKGTCBXMBHFSRWSPCWLGOIPUIIHKRJWMRKXWSJRWSYLWQVIDJEBDMKRSYLHTQVVKTMZMVGGVOAWCDMZCIYHNDHZBTFFYOESIPATWSCPWDLUCDANCVAEHQXTMRJWXTICKEFSYQHFLHKSEXXTHXPPIPCPSZQOQMXTYQQYIVJWKSXQIAGOMBHFINAJHFWLSFLH".toCharArray();
        int[][] key = {{6, 3}, {5, 3}};
        int[][] invertedKey = {{1, 25}, {7, 2}};
        System.out.println(hillTwo(text, invertedKey));
        String text1 = "Schwarze Löcher im Universum sind Orte der Extreme. Die Masse ist in ihnen so stark zusammengepresst, dass nichts ihrer enorm hohen Anziehungskraft entkommt – nicht einmal Licht. Die Objekte selbst sind unsichtbar. Sie verraten sich jedoch über die Materie, die sie verschlucken: Fällt Materie in ein Schwarzes Loch, heizt sie sich auf Millionen Grad auf und strahlt dann hell. Dieses charakteristische Leuchten können Teleskope registrieren. Supermassereiche Schwarze Löcher erreichen durchaus die millionen-bis milliardenfache Masse unserer Sonne. Genau darum handelt es sich den Forschern zu folge bei den entdeckten kleinen roten Punkten – allerdings sind sie noch nicht unverhältnismäßig groß.";
        System.out.println(hillTwo(cutString(text1).toCharArray(), key));
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

    public static int[][] invertMatrixMod26(int[][] matrix) {
        int[][] unitMatrix = {{1, 0}, {0, 1}};
        int[] result = new int[2];

        return unitMatrix;
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
        System.out.println(text);
        return text;
    }
}