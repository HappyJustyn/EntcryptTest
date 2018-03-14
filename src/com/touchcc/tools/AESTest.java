package com.touchcc.tools;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESTest {
    
    private static String str = "hello世界";
    
    public static void main(String[] args) {
        jdkAES();
    }
    
    public static void jdkAES() {
        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] ketByte = secretKey.getEncoded();
            
            //key转换
            Key key = new SecretKeySpec(ketByte, "AES");
            
            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result  = cipher.doFinal(str.getBytes());
            System.out.println(result);
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void bcAES() {
        
    }

}
