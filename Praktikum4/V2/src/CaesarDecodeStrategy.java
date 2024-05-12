public class CaesarDecodeStrategy implements DecodeStrategy{

    @Override
    public char[] decode(char[] key, char[] text) {
        char keyChar = CipherUtil.charArrToSingleChar(key);
        char[] decodedText = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            if(text[i] == ' '){
                decodedText[i] = ' ';
                continue;
            }

            int number = (text[i] - (Character.isLowerCase(text[i]) ? 97 : 65) - (Character.isLowerCase(keyChar) ? keyChar - 97 : keyChar - 65));
            if(number < 0){
                decodedText[i] = (char) (number + (Character.isLowerCase(keyChar) ? 123 : 91));
            }else {
                decodedText[i] = (char) (number + (Character.isLowerCase(keyChar) ? 97 : 65));
            }
        }
        return decodedText;
    }
}
