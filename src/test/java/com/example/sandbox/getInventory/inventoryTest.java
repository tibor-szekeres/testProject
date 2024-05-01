package com.example.sandbox.getInventory;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.example.sandbox.util.constans.Tags.REGRESSION;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class inventoryTest extends Common {
    @Test(enabled = true,groups = {REGRESSION},description ="Return pet inventory by status")
    public void Test1(){
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","available");

        Response  response = getUrl(inventory, queryParams);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");
    }
}
