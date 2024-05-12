public class CaesarEncodeStrategy implements EncodeStrategy{

    @Override
    public char[] encode(char[] key, char[] text) {
        char keyChar = key[0];
        char[] encodedText = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            if(text[i] == ' '){
                encodedText[i] = ' ';
                continue;
            }

            int numberToAdd = (text[i]) - (Character.isUpperCase(text[i]) ? 65 : 97) + (Character.isLowerCase(keyChar) ? keyChar - 97 : keyChar - 65);
            encodedText[i] = (char) ((numberToAdd % 26) + (Character.isUpperCase(text[i]) ? 65 : 97));
        }
        return encodedText;
    }
}
