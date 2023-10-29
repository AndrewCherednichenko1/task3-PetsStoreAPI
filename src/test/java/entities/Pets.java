package entities;

import helpers.Helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pets {

    Helper helper = new Helper();

    private Integer petId;

    public String generateDataForPet() {
        petId = helper.getRandomNumber(1, 1000);
        int categoryId = helper.getRandomNumber(1, 1000);
        var categoryName = "category_" + helper.getRandomString();
        var petName = "pet_" + generatePetName();
        var photoUrl = "url_" + helper.getRandomString();
        int tagsId = helper.getRandomNumber(1, 1000);
        var tagsName = "tag_" + helper.getRandomString();
        var status = "available";
        return "{" +
                "\"id\":\"" + petId + "\"," +
                "\"category\": {" +
                "    \"id\":\"" + categoryId + "\"," +
                "    \"name\":\"" + categoryName + "\"" +
                "}," +
                "\"name\":\"" + petName + "\"," +
                "\"photoUrls\":[\"" + photoUrl + "\"]," +
                "\"tags\":[{" +
                "    \"id\":\"" + tagsId + "\"," +
                "    \"name\":\"" + tagsName + "\"" +
                "}]," +
                "\"status\":\"" + status + "\"" +
                "}";
    }

    public Integer getPetId() {
        return petId;
    }

    public String generatePetStatus() {
        String[] listOfStatuses = new String[]{"available", "notAvailable", "toBeAvailableSoon"};
        return listOfStatuses[new Random().nextInt(listOfStatuses.length)];
    }

    public String generatePetName() {
        return "Pet#" + helper.getRandomString();
    }

    public Map<String, String> getNewPetNameStatus() {
        String newPetName = generatePetName();
        String newPetStatus = generatePetStatus();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", newPetName);
        parameters.put("status", newPetStatus);
        return parameters;
    }
}
