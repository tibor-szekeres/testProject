package com.example.sandbox.businessProcesses;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.example.sandbox.util.constans.Tags.REGRESSION;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class StoreLifeCycle extends Common {
    @Test(enabled = true,groups = {REGRESSION},description ="Place an order for a pet")
    public void Test2(){
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","available");

        Response  response = getUrl(findByStatus, queryParams);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");

        String id = response.jsonPath().get("[0].id").toString();

        String requestbody = "{\n" +
                "  \"id\": "+ id + ",\n" +
                "  \"quantity\": 1,\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response2 = postUrl(order, requestbody);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");
    }
}
