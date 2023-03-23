
public class Encryptdecrypt {

    public static String encrypt(String s) {

        String encrypt = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ch += 26;
            encrypt = encrypt + ch;

           
        }
        return encrypt;

    }

    public static String decrypt(String s) {

        String decrypt = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ch -= 26;
            decrypt = decrypt + ch;

           
        }
        return decrypt;
    }


}
