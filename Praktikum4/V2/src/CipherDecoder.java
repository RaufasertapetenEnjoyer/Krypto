public class CipherDecoder extends AbstractCipherActor {

    public CipherDecoder(char[] key, char[] text, CipherStrategy strategy) {
        super(key, text, strategy);
    }

    @Override
    public char[] action() {
        DecodeStrategy decodeStrategy = (DecodeStrategy) getStrategy();
        return decodeStrategy.decode(getKey(), getText());
    }
}
