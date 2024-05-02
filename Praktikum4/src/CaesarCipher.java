public class CaesarCipher extends AbstractMonoalphabeticalCipher implements IsCrackable{
    private char[] key;

    CaesarCipher(char[] key, char[] text){
        super(text);
        this.key = key;
    }

    public char[] getKey() {
        return key;
    }

    public void setKey(char[] key) {
        this.key = key;
    }

    @Override
    public char[] encode() {
        return null;
    }

    @Override
    public char[] decode() {
        return null;
    }
}
