package dnn.common.utils;


import org.apache.commons.codec.binary.Base64;

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
        return  new String(Base64.decodeBase64(key.getBytes())) ;
    }

    /**
     * BASE64加密
     *
     * @param key = 需要加密的字符数组
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return new String(Base64.encodeBase64(key.getBytes()));
    }



    public static void main(String[] args) throws Exception {
        String aa = "121212";
        String pp = encryptBASE64(aa);
        System.out.println(pp);
        System.out.println(decryptBASE64(pp));

    }
}
