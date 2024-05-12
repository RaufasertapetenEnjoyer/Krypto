public interface DecodeStrategy extends CipherStrategy{
    char[] decode(char[] key, char[] text);
}
