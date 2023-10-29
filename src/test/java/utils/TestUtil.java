package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class TestUtil {

    public void checkStatusIs200(Response res) {
        Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
    }

    public void checkStatusIs404(Response res) {
        Assert.assertEquals(res.getStatusCode(), 404, "Status Check Failed!");
    }

    public void checkStatusIsLoggedIn(Response res) {
        String message = res.getBody().jsonPath().get("message");
        Assert.assertTrue(message.contains("logged in user session:"));
    }

    public void checkUserIsCreated(Response res, String expectedUsername) {
        String actualUsername = res.getBody().jsonPath().get("username");
        Assert.assertEquals(actualUsername, expectedUsername, "User creation check failed!");
    }

    public void checkPetIsCreated(Response res, Integer expectedPetId) {
        Integer actualPetId = res.getBody().jsonPath().get("id");
        Assert.assertEquals(actualPetId, expectedPetId, "Pet creation check failed!");
    }

    public void checkStatusAndNameAreUpdated(Response res, String name, String status) {
        String newName = res.getBody().jsonPath().get("name");
        String newStatus = res.getBody().jsonPath().get("status");
        Assert.assertEquals(name, newName, "Name check failed!");
        Assert.assertEquals(status, newStatus, "Status check failed!");
    }
}
