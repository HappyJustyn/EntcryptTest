package com.touchcc.tools;

import java.io.File;
import java.util.Map;

public class Test {
    
    private static String str = "京AB8888";

    public static void main(String[] args) throws Exception {
        
        Map<String,String> map = Base64Test.parserXml(new File("resources/ftproot.xml"));
        for(Map.Entry<String, String> entry:map.entrySet()) {
            System.out.println(entry.getValue());
        }
        
        System.out.println(Base64Test.jiami(str));
        System.out.println(Base64Test.jiemi(Base64Test.jiami(str)));
      
    }

}
