package com.touchcc.tools;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DESTest {
    
    private static String str = "hello世界";
    
    public static void main(String[] args) {
        bcDES();
    }
    
    public static void jdkDES() {
        try {
            //生成key
            //KeyGenerator 根据算法随机生成一个key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES"); //key生成器
            keyGenerator.init(56); //key值的默认大小
            SecretKey secretKey = keyGenerator.generateKey(); //key
            byte[] byteKey = secretKey.getEncoded(); //key的byte数组
            
            //key转换
            //SecretKeyFactory 根据一个密钥去生成一个密钥
            DESKeySpec desKeySpec = new DESKeySpec(byteKey); //key转换
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES"); //密钥工厂
            SecretKey secretKey2 = factory.generateSecret(desKeySpec); //DES算法需要的key
            
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //工作方式，填充方式
            cipher.init(Cipher.ENCRYPT_MODE, secretKey2); //指定加密还是解密，和加密密钥
            byte[] result= cipher.doFinal(str.getBytes()); //加密结果
            System.out.println(result);
            System.out.println(Base64.encodeBase64String(result));
            System.out.println(Hex.encodeHexString(result)); //16进制输出
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE, secretKey2);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void bcDES() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            
            //生成key
            //KeyGenerator 根据算法随机生成一个key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC"); //key生成器
            keyGenerator.init(56); //key值的默认大小
            SecretKey secretKey = keyGenerator.generateKey(); //key
            byte[] byteKey = secretKey.getEncoded(); //key的byte数组
            
            //key转换
            //SecretKeyFactory 根据一个密钥去生成一个密钥
            DESKeySpec desKeySpec = new DESKeySpec(byteKey); //key转换
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES"); //密钥工厂
            SecretKey secretKey2 = factory.generateSecret(desKeySpec); //DES算法需要的key
            
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //工作方式，填充方式
            cipher.init(Cipher.ENCRYPT_MODE, secretKey2); //指定加密还是解密，和加密密钥
            byte[] result= cipher.doFinal(str.getBytes()); //加密结果
            System.out.println(result);
            System.out.println(Base64.encodeBase64String(result));
            System.out.println(Hex.encodeHexString(result)); //16进制输出
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE, secretKey2);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
