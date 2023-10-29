import entities.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import utils.RestAssuredUtil;
import utils.TestUtil;

public class BaseTest {

    Response resp = null;

    RequestSpecification req = null;

    TestUtil testUtil = new TestUtil();

    Users users = new Users();

    Pets pets = new Pets();

    RestAssuredUtil rest = new RestAssuredUtil();

    @BeforeClass
    public void setup() {
        rest.setBaseURI();
    }

    @AfterClass
    public void afterTest() {
        rest.resetBaseURI();
    }
}
