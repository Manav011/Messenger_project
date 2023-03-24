
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

import java.security.Key;

public class Encryptdecrypt {
    private byte[] key_value;

    Encryptdecrypt(String key) {// Expected key is 16 Characters
        key_value = key.getBytes();//converting userDecide key into Byte array
    }

    private Key genKey() {// generating key using SecretKeySpec provided by java crypto and choosing AES as a encryption technology
        Key key = new SecretKeySpec(key_value, "AES");
        return key;

    }

    public String encrypt(String s) throws Exception{
        Key key = genKey();
        Cipher c = Cipher.getInstance("AES");//getting instance of Cipher in AES mode
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted_value = c.doFinal(s.getBytes());//encryption done
        String encrypted_string = new BASE64Encoder().encode(encrypted_value);//coverting to alphanumeric using base64encoder
        return encrypted_string;
    }

    public String decrypt(String s) throws Exception{
        Key key = genKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded_value = new BASE64Decoder().decodeBuffer(s);//decoding back to format which AES undrstands
        byte[] deccrypted_value = c.doFinal(decoded_value);//Decrypting using AES
        String deccrypted_string = new String(deccrypted_value);
        return deccrypted_string;
    }

}
