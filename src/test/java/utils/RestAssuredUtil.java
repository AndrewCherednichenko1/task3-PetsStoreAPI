package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredUtil {

    public void setBaseURI() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    public Response post(RequestSpecification request) {
        Response response = request.post();
        System.out.println("POST RESPONSE:\n" + response.asString());
        return response;
    }

    public Response post(RequestSpecification request, boolean jsonType) {
        Response response = request.contentType(ContentType.JSON).post();
        System.out.println("POST RESPONSE:\n" + response.asString());
        return response;
    }

    public Response get(RequestSpecification request) {
        Response response = request.contentType(ContentType.JSON).get();
        System.out.println("GET RESPONSE:\n" + response.asString());
        return response;
    }

    public Response delete(RequestSpecification request) {
        Response response = request.delete();
        System.out.println("DELETE RESPONSE:\n" + response.asString());
        return response;
    }

    public RequestSpecification request(String endpoint) {
        return given().basePath(endpoint);
    }

    public RequestSpecification request(String endpoint, String bodyData) {
        return request(endpoint).body(bodyData);
    }

    public RequestSpecification request(String endpoint, String[] params) {
        return request(endpoint).contentType(ContentType.MULTIPART).queryParam(Arrays.toString(params));
    }

    public RequestSpecification request(String endpoint, Map<String, String> params) {
        return request(endpoint).contentType(ContentType.URLENC).queryParams(params);
    }

    public RequestSpecification request(String endpoint, File file) {
        return request(endpoint).contentType(ContentType.MULTIPART).multiPart(file);
    }
}
