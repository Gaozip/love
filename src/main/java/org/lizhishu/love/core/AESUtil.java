package org.lizhishu.love.core;/**
 * @author sleo
 * @date 2019/4/18
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 *
 * @Title AES.java
 * @Description AES工具类
 *
 */
public class AESUtil {
    public static final String ALGORITHM = "AES";
    public final static String DEFAULT_CHARSET = "UTF-8";
    

    /**
     * 生成密钥
     * @param seed 密钥种子
     * @param size 密钥长度
     * @return 密钥哈希
     * @throws Exception
     */
    public static String genKey(String seed, int size) throws Exception {
        SecureRandom secureRandom = new SecureRandom(seed.getBytes(DEFAULT_CHARSET));
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(size, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytes = secretKey.getEncoded();

        return new String(Base64.encodeBase64(bytes, false), DEFAULT_CHARSET);
    }

    /**
     * 加密
     * @param text 明文
     * @param key 密钥哈希
     * @return 密文Base64
     * @throws Exception
     */
    public static String encrypt(String text, String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] textBytes = text.getBytes(DEFAULT_CHARSET);
        byte[] bytes = cipher.doFinal(textBytes);

        return new String(Base64.encodeBase64(bytes), DEFAULT_CHARSET);
    }

    /**
     * 解密
     * @param text 密文
     * @param key 密钥哈希
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String text, String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] textBytes = Base64.decodeBase64(text);
        byte[] bytes = cipher.doFinal(textBytes);

        return new String(bytes, DEFAULT_CHARSET);
    }
}