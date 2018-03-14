package com.touchcc.tools;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class PBETest {
    
    private static String str = "hello世界";
    
    public static void main(String[] args) {
        jdkPBE();
    }
    
    public static void jdkPBE() {
        try {
            //生成盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);
            
            //密钥
            String password = "imooc"; //用户口令
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(keySpec); //根据口令生成密钥
            
            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(str.getBytes());
            System.out.println(result);
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE, key,pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
