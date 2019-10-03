package com.gdaas.iard.datafill.wechat.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by like on 2017/4/11.
 */
public class SecurityUtil {
    private static final String MD5 = "MD5";
    private static final String SHA = "SHA";
    private static final String SHA256 = "SHA-256";
    private static final String SHA384 = "SHA-384";
    private static final String SHA512 = "SHA-512";


    public static final String AES_KEY="TIYOUPIasdf)(*&^@#$%765";

    public static String md5Encrypt(String msg){
        return encrypt2HexStr(msg, SecurityUtil.MD5);
    }

    /**
     * Encrypt msg use SHA algorithm and return hex result as string
     * the length of result will be 40(20*2)
     * @param msg
     * @return
     */
    public static String shaEncrypt(String msg){
        return encrypt2HexStr(msg, SecurityUtil.SHA);
    }

    /**
     * Encrypt msg use SHA-256 algorithm and return hex result as string
     * the length of result will be 64(32*2)
     * @param msg
     * @return
     */
    public static String sha256Encrypt(String msg){
        return encrypt2HexStr(msg, SecurityUtil.SHA256);
    }

    /**
     * Encrypt msg use SHA-384 algorithm and return hex result as string
     * the length of result will be 96(48*2)
     * @param msg
     * @return
     */
    public static String sha384Encrypt(String msg){
        return encrypt2HexStr(msg, SecurityUtil.SHA384);
    }

    /**
     * Encrypt msg use SHA-512 algorithm and return hex result as string
     * the length of result will be 128(64*2)
     * @param msg
     * @return
     */
    public static String sha512Encrypt(String msg){
        return encrypt2HexStr(msg, SecurityUtil.SHA512);
    }
    private static String encrypt2HexStr(String msg, String algorithm){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
            md.update(msg.getBytes());
            byte[] mded = md.digest();
            return toHexString(mded);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    /**
     * Convert byte arrays to hex string
     * @param b
     * @return
     */
    private static String toHexString(byte[] b){
        String hs="";
        String stmp="";
        for (int n=0;n<b.length;n++)
        {
            stmp=(Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
        }
        return hs.toUpperCase();
    }



    // ==Aes加解密==================================================================
    /**
     * aes解密-128位
     */
    public static String aesDecrypt(String encryptContent, String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(hex2Bytes(encryptContent)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * aes加密-128位
     */
    public static String aesEncrypt(String content, String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return byte2Hex(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将byte[] 转换成字符串
     */
    private static String byte2Hex(byte[] srcBytes) {
        StringBuilder hexRetSB = new StringBuilder();
        for (byte b : srcBytes) {
            String hexString = Integer.toHexString(0x00ff & b);
            hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
        }
        return hexRetSB.toString();
    }

    /**
     * 将16进制字符串转为转换成字符串
     */
    private static byte[] hex2Bytes(String source) {
        byte[] sourceBytes = new byte[source.length() / 2];
        for (int i = 0; i < sourceBytes.length; i++) {
            sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }
        return sourceBytes;
    }


}
