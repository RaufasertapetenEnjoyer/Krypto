public class VigenereDecodeStrategy implements DecodeStrategy{

    @Override
    public char[] decode(char[] key, char[] text) {
        char[] decodedText = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            if(text[i] == ' '){
                decodedText[i] = ' ';
                continue;
            }

            boolean isUpper = Character.isUpperCase(text[i]);
            int number = (text[i] - (isUpper ? 65 : 97)) - ( Character.isUpperCase(key[i % key.length]) ? (key[i % key.length] - 65) : (key[i % key.length] - 97));
            if(number < 0){
                decodedText[i] = (char) (number + (isUpper ? 91 : 123));
            }else {
                decodedText[i] = (char) (number + (isUpper ? 65 : 97));
            }
        }
        return decodedText;
    }
}
