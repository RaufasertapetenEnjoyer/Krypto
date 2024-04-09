import java.util.Arrays;

public class Main {
    public static char[] encode(char[] textToEncode, char[] key){
        char[] array = new char[textToEncode.length];
        for (int i = 0; i < textToEncode.length; i++) {
            if(textToEncode[i] == ' '){
                array[i] = ' ';
                continue;
            }
            if(Character.isUpperCase(textToEncode[i])){
                int numberToAdd = (textToEncode[i]) - 65 + key[i % key.length] - 97;
                array[i] = (char) ((numberToAdd % 26) + 65);
                continue;
            }

            if(Character.isLowerCase(textToEncode[i])){
                int number = (textToEncode[i]) - 97 + key[i % key.length] - 97;
                array[i] = (char) ((number % 26) + 97);
            }
        }
        return array;
    }

    public static char[] decode(char[] textToDecode, char[] key){
        char[] array = new char[textToDecode.length];
        for (int i = 0; i < textToDecode.length; i++) {
            if(textToDecode[i] == ' '){
                array[i] = ' ';
                continue;
            }
            if(Character.isUpperCase(textToDecode[i])){
                int number = (textToDecode[i] - 65) - (key[i % key.length] - 97);
                array[i] = (char) (number + 65);
                continue;
            }
            if(Character.isLowerCase(textToDecode[i])){
                int number = (textToDecode[i] - 97) - (key[i % key.length] - 97);
                if(number < 0){
                    array[i] = (char) (number + 123);
                }else {
                    array[i] = (char) (number + 97);
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        char[] text = "Halloichbinmichael".toCharArray();
        char[] key = "kleinermann".toCharArray();

        System.out.print("Der zu verschlüssende Text lautet:    ");
        System.out.println(text);
        System.out.print("Der Schlüssel zur verschlüsselung lautet: ");
        System.out.println(key);

        char[] encodedText = encode(text, key);
        System.out.print("Der verschlüsselte Text lautet:       ");
        System.out.println(encodedText);

        char[] decodedText = decode(encodedText, key);
        System.out.print("Der entschlüsselte Text lautet:       ");
        System.out.println(decodedText);
    }
}