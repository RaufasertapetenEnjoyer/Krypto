public class VigenereCipher extends AbstractPolyalphabeticalCipher implements IsCrackable{
    private char[] key;

    VigenereCipher(char[] key, char[] text){
        super(text);
        this.key = key;
    }

    public char[] getKey() {
        return key;
    }

    public void setKey(char[] key) {
        this.key = key;
    }

    public char[] encode() {
        char[] encodedText = new char[getText().length];
        for (int i = 0; i < getText().length; i++) {
            if(getText()[i] == ' '){
                encodedText[i] = ' ';
                continue;
            }

            boolean keyUpper = Character.isUpperCase(getText()[i]);
            int numberToAdd = (getText()[i]) - (keyUpper ? 65 : 97) + key[i % key.length] - 97;
            encodedText[i] = (char) ((numberToAdd % 26) + (keyUpper ? 65 : 97));

        }
        return encodedText;
    }

    @Override
    public char[] decode() {
        char[] decodedText = new char[getText().length];
        for (int i = 0; i < getText().length; i++) {
            if(getText()[i] == ' '){
                decodedText[i] = ' ';
                continue;
            }

            boolean isUpper = Character.isUpperCase(getText()[i]);
            int number = (getText()[i] - (isUpper ? 65 : 97)) - (key[i % key.length] - 97);
            if(number < 0){
                decodedText[i] = (char) (number + (isUpper ? 91 : 123));
            }else {
                decodedText[i] = (char) (number + (isUpper ? 65 : 97));
            }
        }
        return decodedText;
    }
}
