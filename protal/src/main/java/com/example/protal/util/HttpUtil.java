package com.example.protal.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
    public static  String getData(String url){
        try {
            URL u = new URL(url);
            //打开连接
            URLConnection con = u.openConnection();
            //获取输入流
            InputStream in = con.getInputStream();
            byte bs[] = new byte[1024];
            int count = 0;
            StringBuilder sd = new StringBuilder();
            while( (count = in.read(bs) )!=-1){
                    sd.append(new String(bs,0,count));
            }
            in.close();
            return  sd.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static void main(String[] args){
            String x = getData("http://www.baidu.com");
            System.out.println(x);
    }
}
