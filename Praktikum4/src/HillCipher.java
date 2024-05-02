public class HillCipher extends AbstractPolyalphabeticalCipher{
    private char[][] key;

    HillCipher(char[][] key, char[] text){
        super(text);
        this.key = key;
    }

    public char[][] getKey() {
        return key;
    }

    public void setKey(char[][] key) {
        this.key = key;
    }

    @Override
    public char[] encode() {
        char[] encodeText = new char[getText().length];
        char[] subStr = null;

        for (int i = 0; i < getText().length; i++) {
            if(i % key.length == 0){
                subStr = new char[key.length];
                System.arraycopy(getText(), i, subStr, 0, key.length);
            }

            int sum = 0;

            for (int j = 0; j < key.length; j++) {
                sum += (int) (key[i % key.length][j]) * (subStr[j] % 65);
            }
            encodeText[i] = (char) ((sum % 26) + 65);
        }
        return encodeText;
    }

    @Override
    public char[] decode() {
        char[][] key = CipherUtil.inverseOf2x2mod26(this.key);
        char[] encodeText = new char[getText().length];
        char[] subStr = null;

        for (int i = 0; i < getText().length; i++) {
            if(i % key.length == 0){
                subStr = new char[key.length];
                System.arraycopy(getText(), i, subStr, 0, key.length);
            }

            int sum = 0;

            for (int j = 0; j < key.length; j++) {
                sum += (int) (key[i % key.length][j]) * (subStr[j] % 65);
            }
            encodeText[i] = (char) ((sum % 26) + 65);
        }
        return encodeText;
    }
}
