package Encryption;

public class testing {
    public static void main(String[] args) throws Exception {
        Encryptdecrypt encdec = new Encryptdecrypt("1234567890123456");
        String s = encdec.encrypt("hi");
        System.out.println(s);
        System.out.println(encdec.decrypt(s));
        // ZakMiteWcd0mU5bniq7Szw==

    }
}
