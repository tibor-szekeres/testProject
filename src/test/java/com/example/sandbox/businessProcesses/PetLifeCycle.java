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

public class PetLifeCycle extends Common {

    private static String requestbody = "{\n" +
            "  \"category\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"rottweiler\"\n" +
            "  },\n" +
            "  \"name\": \"doggie\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"goodBoi\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";
    @Test(enabled = true,groups = {SMOKE},description ="Create new Pet")
    public void Test1(){

        Response response = postUrl(newPet, requestbody);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");
    }

    @Test(enabled = true,groups = {SMOKE},description ="Create, Update and Delete Pet")
    public void Test2(){
        String id = "9223372036854775807";

        Response response = postUrl(newPet, requestbody);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");

        String updateBody = "{\n" +
                "  \"id\": "+ id +
                ",\"name\": \"updatedPet2\"}";

        Response response2 = putUrl(newPet, updateBody);
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");

        Response response3 = getUrl(petById, id);
        String responseCode = response3.getBody().asString();
        System.out.println(responseCode);

        Response response4 = deleteUrl(petById, id);
        Assert.assertEquals(response4.getStatusCode(),200,"Invalid response code");

        Response response5 = getUrl(petById, id);
        String responseCode2 = response5.getBody().asString();
        Assert.assertFalse(responseCode2.contains("updatedPet2"));
    }

    @Test(enabled = true,groups = {REGRESSION},description ="Upload image - not supported media type")
    public void Test3(){
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","available");

        Response  response = getUrl(findByStatus, queryParams);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");

        String id = response.jsonPath().get("[0].id").toString();

        Response response2 = postUrl(uploadImage, id, HYDRAIMAGE);
        Assert.assertEquals(response2.getStatusCode(),415,"Invalid response code");
    }
}
