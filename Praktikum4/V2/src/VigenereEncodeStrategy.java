public class VigenereEncodeStrategy implements EncodeStrategy{

    @Override
    public char[] encode(char[] key, char[] text) {
        char[] encodedText = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            if(text[i] == ' '){
                encodedText[i] = ' ';
                continue;
            }

            boolean keyUpper = Character.isUpperCase(text[i]);
            int numberToAdd = (text[i]) - (keyUpper ? 65 : 97) + key[i % key.length] - 97;
            encodedText[i] = (char) ((numberToAdd % 26) + (keyUpper ? 65 : 97));

        }
        return encodedText;
    }
}
