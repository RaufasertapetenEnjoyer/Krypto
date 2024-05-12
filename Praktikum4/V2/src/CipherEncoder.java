public class CipherEncoder extends AbstractCipherActor{

    public CipherEncoder(char[] key, char[] text, CipherStrategy strategy) {
        super(key, text, strategy);
    }

    @Override
    public char[] action() {
        EncodeStrategy encodeStrategy = (EncodeStrategy) getStrategy();
        return encodeStrategy.encode(getKey(), getText());
    }
}
