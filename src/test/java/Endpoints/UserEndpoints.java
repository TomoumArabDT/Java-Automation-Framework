package Endpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static Response getUser(Object requestBody) {
        return given()
                .contentType("application/json")
//                .body(requestBody)
                .when()
                .get("api/v1/users");
    }

    public static Response createUser(Object requestBody) {
        return given()
                .contentType("application/json")
//                .body(requestBody)
                .when()
                .post("api/v1/users");
    }
}
