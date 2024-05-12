public abstract class AbstractCipherActor {
    private char[] key;
    private char[] text;
    private CipherStrategy strategy;



    public AbstractCipherActor(char[] key, char[] text, CipherStrategy strategy) {
        this.key = key;
        this.text = text;
        this.strategy = strategy;
    }

    public char[] getKey() {
        return key;
    }

    public void setKey(char[] key) {
        this.key = key;
    }

    public char[] getText() {
        return text;
    }

    public void setText(char[] text) {
        this.text = text;
    }

    public CipherStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CipherStrategy strategy) {
        this.strategy = strategy;
    }

    public abstract char[] action();
}
