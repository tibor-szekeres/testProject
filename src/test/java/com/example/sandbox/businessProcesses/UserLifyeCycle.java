package com.example.sandbox.businessProcesses;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.example.sandbox.util.constans.Tags.REGRESSION;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class UserLifyeCycle extends Common {

    private static String username, password, id = "string";

    private static String requestBody = "[\n" +
            "  {\n" +
            "    \"id\": 0,\n" +
            "    \"username\": \"string\",\n" +
            "    \"firstName\": \"string\",\n" +
            "    \"lastName\": \"string\",\n" +
            "    \"email\": \"string\",\n" +
            "    \"password\": \"string\",\n" +
            "    \"phone\": \"string\",\n" +
            "    \"userStatus\": 0\n" +
            "  }\n" +
            "]";

    @Test(enabled = true,groups = {REGRESSION},description ="Create user with Array")
    public void Test1(){
        Response response2 = postUrl(createWithArray, requestBody);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }

    @Test(enabled = true,groups = {REGRESSION},description ="Create user with List")
    public void Test2(){
        Response response2 = postUrl(createWithList, requestBody);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
    @Test(enabled = true,groups = {SMOKE},description ="Get user by username")
    public void Test3(){

        Response  response2 = getUrl(user, id);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
    @Test(enabled = true,groups = {SMOKE},description ="Login user by username")
    public void Test4(){
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("username",username);
        queryParams.put("password",password);

        Response  response2 = getUrl(login, queryParams);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
    @Test(enabled = true,groups = {SMOKE},description ="Logout user")
    public void Test5(){

        Response  response2 = getUrl(logout);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
}
