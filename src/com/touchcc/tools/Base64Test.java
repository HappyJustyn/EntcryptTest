package com.touchcc.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {
    
    public static void jdkBase64(String str) {
        //加密
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(str.getBytes());
        System.out.println("encode:"+encode);
        //解密
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = "";
        try {
            decode = new String(decoder.decodeBuffer(encode));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("decode:"+decode);
    }
    
    public static void bouncybastleBase64(String str) {
        //加密
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
        System.out.println("encode: "+new String(encode));
        //解密
        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println("decode: "+new String(decode));
    }
    
    public static void commonscodecBase64(String str){
        byte[] encode=Base64.encodeBase64(str.getBytes());
        System.out.println("encode: "+new String(encode));  //需要转化为String
        
        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("decode: "+new String(decode));
    }
    
    //得到base64转码的字符串
    public static String jiami(String str) {
        byte[] encode=Base64.encodeBase64(str.getBytes());
        return new String(encode);
    }
    
    //解码base64
    public static String jiemi(String str) {
        byte[] decode = Base64.decodeBase64(str.getBytes());
        return new String(decode);
    }
    
    //jdom解析xml文件
    public static Map<String,String> parserXml(File fileName) {
        SAXBuilder builder = new SAXBuilder();
        Map<String,String> map = new HashMap<String,String>();
        try {
            Document document = builder.build(fileName);
            Element root = document.getRootElement();
            Element flag = root.getChild("ftproot-flag");
            if(flag.getValue().equals("1")) {
                List values = root.getChild("ftproot-values").getChildren();
                byte[] decode = null;
                for(Object obj:values) {
                    decode = Base64.decodeBase64(((Element)obj).getValue().getBytes());
                    map.put(((Element)obj).getName(),new String(decode));
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
