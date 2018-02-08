package com.example.protal.util;

import java.util.Base64;

public class BaseUtil {
    public  static String encode(String src){
        return Base64.getEncoder().encodeToString(src.getBytes());

    }
    public static String decode(String src){
        byte[] bytes= Base64.getDecoder().decode(src);
        return  new String(bytes);
    }
    public  static void main(String[] args){
        System.out.println(encode("66iuuuuiooooooooooooooooonm.....==="));
        System.out.println(decode("NjZpdXV1dWlvb29vb29vb29vb29vb29vb25tLi4uLi49PT0="));
    }
}
