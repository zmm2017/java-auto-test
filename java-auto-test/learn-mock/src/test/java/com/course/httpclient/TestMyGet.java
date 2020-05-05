package com.course.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class TestMyGet {
    private String apiHost=null;
    private ResourceBundle bundle;
    @BeforeTest
    public void getHost(){
        bundle=ResourceBundle.getBundle("application");
        apiHost=bundle.getString("test.api.host");
    }
    @Test(description = "不携带参数时，能正常返回")
    public void testWithoutParam() {
        String apiUrl = bundle.getString("test.get.without.param");
        CloseableHttpClient client=HttpClientBuilder.create().build();
        HttpGet get=new HttpGet(apiHost+apiUrl);
        CloseableHttpResponse response = null;
        try {
            response=client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int httpCode=response.getStatusLine().getStatusCode();
        String responseBody=null;
        try {
            responseBody= EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(httpCode,200);
        Assert.assertEquals(responseBody,"这是不带参数的get请求");
    }
    @Test(description = "携带参数时，返回204")
    public void testWithParam(){
        int httpCode=0;
        CloseableHttpClient client=null;
        CloseableHttpResponse response=null;
        String apiUrl=bundle.getString("test.get.without.param");
        client=HttpClientBuilder.create().build();
        HttpGet get=new HttpGet(apiHost+apiUrl);
        try {
            response=client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(),204);
    }
}
