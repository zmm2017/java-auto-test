package com.course.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {
    @Test(description = "demo")
    public void httpClientDemo() throws IOException {
        String url="http://www.baidu.com";
        CloseableHttpClient client= HttpClientBuilder.create().build();
        HttpGet get=new HttpGet(url);
        CloseableHttpResponse response=client.execute(get);
        System.out.println("返回状态码是："+response.getStatusLine().getStatusCode());
        System.out.println("http版本是："+response.getStatusLine().getProtocolVersion());
        System.out.println("响应body是："+ EntityUtils.toString(response.getEntity(),"utf-8"));
    }
}
