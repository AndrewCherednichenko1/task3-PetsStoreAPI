import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

public class TestRunner extends BaseTest {

    @Test(priority = 1, description = "Verify that allows creating a User")
    public void verifyCreatingUser() {
        var endpoint = "/user/";
        var userData = users.generateDataForUser();
        var username = users.getUsername();

        //create a new user
        req = rest.request(endpoint, userData);
        resp = rest.post(req, true);
        testUtil.checkStatusIs200(resp);

        //check that this user exists
        req = rest.request(endpoint + username);
        resp = rest.get(req);
        testUtil.checkStatusIs200(resp);
        testUtil.checkUserIsCreated(resp, username);
    }

    @Test(priority = 2, description = "Verify that allows login as a User")
    public void verifyLoginUser() {
        verifyCreatingUser();
        var endpoint = "/user/login/";
        String[] params = {"username", users.getUsername(), "password", users.getPassword()};
        req = rest.request(endpoint, params);
        resp = rest.get(req);
        testUtil.checkStatusIs200(resp);
        testUtil.checkStatusIsLoggedIn(resp);
    }

    @Test(priority = 3, description = "Verify that allows creating the list of Users")
    public void verifyCreatingListOfUsers() {
        var endpoint = "/user/createWithList";
        var userData = users.generateListOfUsers();
        req = rest.request(endpoint, userData);
        resp = rest.post(req, true);
        testUtil.checkStatusIs200(resp);
    }

    @Test(priority = 4, description = "Verify that allows Log out User")
    public void verifyLogOutUser() {
        var endpoint = "/user/logout";
        req = rest.request(endpoint);
        resp = rest.get(req);
        testUtil.checkStatusIs200(resp);
    }

    @Test(priority = 5, description = "Verify that allows adding a new Pet")
    public void verifyAddingPet() {
        var endpoint = "/pet/";
        var petData = pets.generateDataForPet();
        int petId = pets.getPetId();
        req = rest.request(endpoint, petData);
        resp = rest.post(req, true);
        testUtil.checkStatusIs200(resp);
        testUtil.checkPetIsCreated(resp, petId);
    }

    @Test(priority = 6, description = "Verify that allows updating Pet’s image")
    public void verifyUpdatingPetImage() {
        verifyAddingPet();
        int petId = pets.getPetId();
        var endpoint = "/pet/" + petId + "/uploadImage";
        File uploadImage = new File("src/test/java/uploads/dog.png");
        req = rest.request(endpoint, uploadImage);
        resp = rest.post(req);
        testUtil.checkStatusIs200(resp);
    }

    @Test(priority = 7, description = "Verify that allows updating Pet’s name and status")
    public void verifyUpdatingPetNameAndStatus() {
        verifyAddingPet();
        var endpoint = "/pet/";
        int petId = pets.getPetId();
        Map<String, String> parameters = pets.getNewPetNameStatus();

        //change status and name
        req = rest.request(endpoint + petId, parameters);
        resp = rest.post(req);
        testUtil.checkStatusIs200(resp);

        //check that name and status are changed
        req = rest.request(endpoint + petId);
        resp = rest.get(req);
        testUtil.checkStatusIs200(resp);
        testUtil.checkStatusAndNameAreUpdated(resp, parameters.get("name"), parameters.get("status"));
    }

    @Test(priority = 8, description = "Verify that allows deleting Pet")
    public void verifyDeletingPet() {
        verifyAddingPet();
        var endpoint = "/pet/";
        int petId = pets.getPetId();

        //delete a pet
        req = rest.request(endpoint + petId);
        resp = rest.delete(req);
        testUtil.checkStatusIs200(resp);

        //check that pet is deleted
        resp = rest.get(req);
        testUtil.checkStatusIs404(resp);
    }
}
