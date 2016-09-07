package dnn.common.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by lgq on 16-9-7.
 */
public class CryptUtil {
    /**
     * BASE64解密
     *
     * @param key = 需要解密的密码字符串
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws Exception {
        return new String ((new BASE64Decoder()).decodeBuffer(key));
    }

    /**
     * BASE64加密
     *
     * @param key = 需要加密的字符数组
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key.getBytes());
    }



    public static void main(String[] args) throws Exception {
        String aa = "121212";
        String pp = encryptBASE64(aa);
        System.out.println(pp);
        System.out.println(decryptBASE64(pp));

    }
}
