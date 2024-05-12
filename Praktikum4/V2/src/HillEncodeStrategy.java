public class HillEncodeStrategy implements EncodeStrategy{

    @Override
    public char[] encode(char[] key, char[] text) {
        char[][] keyMatrix = CipherUtil.charArrTo2x2Matrix(key);
        char[] encodeText = new char[text.length];
        char[] subStr = null;

        for (int i = 0; i < text.length; i++) {
            if(i % keyMatrix.length == 0){
                subStr = new char[keyMatrix.length];
                System.arraycopy(text, i, subStr, 0, keyMatrix.length);
            }

            int sum = 0;

            for (int j = 0; j < keyMatrix.length; j++) {
                sum += (int) (keyMatrix[i % keyMatrix.length][j]) * (subStr[j] % 65);
            }
            encodeText[i] = (char) ((sum % 26) + 65);
        }
        return encodeText;
    }
}
