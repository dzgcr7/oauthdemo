package com.example.demo;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Util {

    public static String makeCode(String code){
        long time = System.currentTimeMillis(); // 时间戳
        String Code = code+time+"*";
        return Code;
    }
    public static String makeAccess_token(String access_token){
        long time = System.currentTimeMillis(); // 时间戳
        String Access_token = access_token+time+"*";
        return Access_token;
    }
    public static  void test(){


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest request;
    }
}
