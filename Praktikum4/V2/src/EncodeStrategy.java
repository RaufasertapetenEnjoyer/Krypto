public interface EncodeStrategy extends CipherStrategy{
    char[] encode(char[] key, char[] text);
}
