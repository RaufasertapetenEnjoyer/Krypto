public class Main {

    public static void main(String[] args) {
        CipherActorBuilder builder = new CipherActorBuilder();

        char[] key1 = "key".toCharArray();
        char[] text1 = "Ichwillnichtmitihmtanzen".toCharArray();
        System.out.println(text1);
        AbstractCipherActor encoder1 = builder.build(key1, text1, false, CipherUtil.CAESAR_CIPHER);
        char[] cryptext1 = encoder1.action();
        System.out.println(cryptext1);
        AbstractCipherActor decoder1 = builder.build(key1, cryptext1, true, CipherUtil.CAESAR_CIPHER);
        System.out.println(decoder1.action());

        char[] key2 = "x".toCharArray();
        char[] text2 = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam".toCharArray();
        System.out.println(text2);
        AbstractCipherActor encoder2 = builder.build(key2, text2, false, CipherUtil.CAESAR_CIPHER);
        char[] cryptext2 = encoder2.action();
        System.out.println(cryptext2);
        AbstractCipherActor decoder2 = builder.build(key2, cryptext2, true, CipherUtil.CAESAR_CIPHER);
        System.out.println(decoder2.action());

        char[] key3 = "6353".toCharArray();
        char[] text3 = "Die Macht ist keine Fähigkeit, die man besitzt. Es geht nicht darum, Steine hoch zu heben. Es ist ein Energiefeld zwischen allen Dingen - eine Spannung, oder ein Gleichgewicht, dass die Galaxis zusammen hält.".toCharArray();
        System.out.println(text3);
        AbstractCipherActor encoder3 = builder.build(key3, text3, false, CipherUtil.CAESAR_CIPHER);
        char[] cryptext3 = encoder3.action();
        System.out.println(cryptext3);
        AbstractCipherActor decoder3 = builder.build(key3, cryptext3, true, CipherUtil.CAESAR_CIPHER);
        System.out.println(decoder3.action());
    }
}
