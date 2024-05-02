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
            if(Character.isUpperCase(getText()[i])){
                int numberToAdd = (getText()[i]) - 65 + key[i % key.length] - 97;
                encodedText[i] = (char) ((numberToAdd % 26) + 65);
                continue;
            }

            if(Character.isLowerCase(getText()[i])){
                int number = (getText()[i]) - 97 + key[i % key.length] - 97;
                encodedText[i] = (char) ((number % 26) + 97);
            }
        }
        return encodedText;
    }

    @Override
    public char[] decode() {
        char[] array = new char[getText().length];
        for (int i = 0; i < getText().length; i++) {
            if(getText()[i] == ' '){
                array[i] = ' ';
                continue;
            }
            if(Character.isUpperCase(getText()[i])){
                int number = (getText()[i] - 65) - (key[i % key.length] - 97);
                array[i] = (char) (number + 65);
                continue;
            }
            if(Character.isLowerCase(getText()[i])){
                int number = (getText()[i] - 97) - (key[i % key.length] - 97);
                if(number < 0){
                    array[i] = (char) (number + 123);
                }else {
                    array[i] = (char) (number + 97);
                }
            }
        }
        return array;
    }
}
