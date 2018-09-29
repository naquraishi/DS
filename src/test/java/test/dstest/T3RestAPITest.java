package test.dstest;

import org.fluentlenium.core.annotation.Page;
import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import test.dspages.AllUsersPage;


import static org.junit.Assert.*;


public class T3RestAPITest extends FluentLeniumTest{


    @BeforeClass
    public static void baseUriSetup(){
        RestAssured.baseURI = "http://85.93.17.135:9000";
    }

    //the deleteAllUsersNames is covering both GET And DELETE REST APIs

    @Test
    public void deleteAllUsersNames() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.request(Method.DELETE, "/user/all");
        assertEquals(getUsersJsonArrayLength(),0);
    }

    private int getUsersJsonArrayLength(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/user/all/json");
        String responseBody = response.getBody().asString();
        int index  = responseBody.indexOf("[");
        responseBody = responseBody.substring(index);
        return new JSONArray(responseBody).length();
    }


}
