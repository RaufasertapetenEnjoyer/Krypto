public class CaesarCipher extends AbstractMonoalphabeticalCipher implements IsCrackable{
    private char key;

    CaesarCipher(char key, char[] text){
        super(text);
        this.key = key;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    @Override
    public char[] encode() {
        char[] encodedText = new char[getText().length];
        for (int i = 0; i < getText().length; i++) {
            if(getText()[i] == ' '){
                encodedText[i] = ' ';
                continue;
            }

            int numberToAdd = (getText()[i]) - (Character.isUpperCase(getText()[i]) ? 65 : 97) + (Character.isLowerCase(key) ? key - 97 : key - 65);
            encodedText[i] = (char) ((numberToAdd % 26) + (Character.isUpperCase(getText()[i]) ? 65 : 97));
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

            int number = (getText()[i] - (Character.isLowerCase(getText()[i]) ? 97 : 65) - (Character.isLowerCase(key) ? key - 97 : key - 65));
            if(number < 0){
                decodedText[i] = (char) (number + (Character.isLowerCase(key) ? 123 : 91));
            }else {
                decodedText[i] = (char) (number + (Character.isLowerCase(key) ? 97 : 65));
            }
        }
        return decodedText;
    }
}
