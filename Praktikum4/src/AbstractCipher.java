public abstract class AbstractCipher {
    private char[] text;

    AbstractCipher(char[] text){
        this.text = text;
    }

    public char[] getText() {
        return text;
    }

    public void setText(char[] text) {
        this.text = text;
    }

    public abstract char[] encode();
    public abstract char[] decode();
}
