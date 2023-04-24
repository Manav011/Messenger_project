package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.*;

import java.security.Key;
//import java.util.Base64.Decoder;
//import java.util.Base64.Encoder;

public class Encryptdecrypt {
    private final byte[] key_value;

    public Encryptdecrypt(String key) {// Expected key is 16 Characters
        key_value = key.getBytes();// converting userDecide key into Byte array
    }

    private Key genKey() {// generating key using SecretKeySpec provided by java crypto and choosing AES
                          // as a encryption technology
        return new SecretKeySpec(key_value, "AES");

    }

    public String encrypt(String s) throws Exception {
        Key key = genKey();
        Cipher c = Cipher.getInstance("AES");// getting instance of Cipher in AES mode
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted_value = c.doFinal(s.getBytes());// encryption done
        Base64.Encoder encoder = Base64.getEncoder();
        // base64encoder
        return encoder.encodeToString(encrypted_value);
    }

    public String decrypt(String s) throws Exception {
        Key key = genKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        Decoder decoder = Base64.getDecoder();
        // decoding back to format which AES undrstands
        byte[] decoded_value = decoder.decode(s);
        byte[] deccrypted_value = c.doFinal(decoded_value);// Decrypting using AES
        return new String(deccrypted_value);
    }

}
