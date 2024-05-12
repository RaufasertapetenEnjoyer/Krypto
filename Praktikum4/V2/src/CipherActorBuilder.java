public class CipherActorBuilder {

    public CipherActorBuilder() {
    }

    public AbstractCipherActor build(char[] key, char[] text, boolean isDecode, int cipher) {
        CipherStrategy strategy = switch (cipher) {
            case CipherUtil.CAESAR_CIPHER -> isDecode ? new CaesarDecodeStrategy() : new CaesarEncodeStrategy();
            case CipherUtil.VIGENERE_CIPHER -> isDecode ? new VigenereDecodeStrategy() : new VigenereEncodeStrategy();
            case CipherUtil.HILL_CIPHER -> isDecode ? new HillDecodeStrategy() : new HillEncodeStrategy();
            default -> isDecode ? new CaesarDecodeStrategy() : new CaesarEncodeStrategy();
        };
        return isDecode ? new CipherDecoder(key, text, strategy) : new CipherEncoder(key, text, strategy);
    }
}
