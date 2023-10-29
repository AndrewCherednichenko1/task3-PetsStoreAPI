package entities;

import helpers.Helper;

public class Users {

    Helper helper = new Helper();

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String generateDataForUser() {
        username = "user_" + helper.getRandomString();
        password = helper.getRandomString();
        var id = helper.getRandomNumber(1, 10000);
        var firstName = "firstName_" + helper.getRandomString();
        var lastName = "lastName_" + helper.getRandomString();
        var email = "lastName_" + helper.getRandomString() + "@gmail.com";
        var phone = "firstName_" + helper.getRandomString();
        return "{" +
                "\"id\": " + id +
                ", \"username\": \"" + getUsername() + "\"" +
                ", \"firstName\": \"" + firstName + "\"" +
                ", \"lastName\": \"" + lastName + "\"" +
                ", \"email\": \"" + email + "\"" +
                ", \"password\": \"" + password + "\"" +
                ", \"phone\": \"" + phone + "\"" +
                ", \"userStatus\": " + 0 +
                "}";
    }

    public String generateListOfUsers() {
        return "[" +
                generateDataForUser() +
                "," + generateDataForUser() +
                "," + generateDataForUser() + "]";
    }
}
