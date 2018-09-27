package test.dstest;

import org.json.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class T3AllUsersRestAPITest extends FluentLeniumTest{

    @BeforeClass

    public static void setup(){
        RestAssured.baseURI = "http://85.93.17.135:9000";
    }

    @Test
    public void shouldFindAllUsersNames() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/user/all/json");
        String responseBody = response.getBody().asString();
        int index  = responseBody.indexOf("[");
        responseBody = responseBody.substring(index);
        JSONArray responseJson = new JSONArray(responseBody);
        for (int i = 0;i<responseJson.length();i++){
            System.out.println(responseJson.getJSONObject(i).get("name"));
        }
    }

    @Test
    public void deleteAllUsersNames() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.DELETE, "/user/all");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
    }

}
