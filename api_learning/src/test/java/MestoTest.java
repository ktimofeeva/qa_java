import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class MestoTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }


    @Test
    public void checkCardsStatusCode() {
        // проверяем статус-код ответа на запрос «Получение всех карточек»
        given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2I5ODcxMWQzYjg2YTAwM2Q2ODMxYjgiLCJpYXQiOjE2NzMxMDMxNDcsImV4cCI6MTY3MzcwNzk0N30.BMSG7hcgop4FsE_I2pLeXi47JzzoVkfjpdxNRA3y1tw")
                .get("/api/cards")
                .then().statusCode(200);
    }

    @Test
    public void checkUserActivityAndPrintResponseBody() {

        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
        Response response = given().auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2I5ODcxMWQzYjg2YTAwM2Q2ODMxYjgiLCJpYXQiOjE2NzMxMDMxNDcsImV4cCI6MTY3MzcwNzk0N30.BMSG7hcgop4FsE_I2pLeXi47JzzoVkfjpdxNRA3y1tw").get("/api/users/me");

        // проверяет, что в теле ответа ключу about соответствует нужное занятие
        response.then().assertThat().body("data.about",equalTo("Самый крутой исследователь"));

        // выводит тело ответа на экран
        System.out.println(response.body().asString());

    }

    @Test
    public void createNewPlaceAndCheckResponse(){
        File json = new File("src/test/resources/newCard.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2I5ODcxMWQzYjg2YTAwM2Q2ODMxYjgiLCJpYXQiOjE2NzMxMDMxNDcsImV4cCI6MTY3MzcwNzk0N30.BMSG7hcgop4FsE_I2pLeXi47JzzoVkfjpdxNRA3y1tw")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }
    @Test
    public void updateProfileAndCheckStatusCode() {
        File json = new File("src/test/resources/updateProfile.json"); // запиши файл в файловую переменную
        Response response =
                given()
                        .header("Content-type", "application/json") // заполни header
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2I5ODcxMWQzYjg2YTAwM2Q2ODMxYjgiLCJpYXQiOjE2NzMxMDMxNDcsImV4cCI6MTY3MzcwNzk0N30.BMSG7hcgop4FsE_I2pLeXi47JzzoVkfjpdxNRA3y1tw")
                        .and()
                        .body(json)
                        .when()
                        .patch("/api/users/me");
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("data.name", equalTo("Василий Васильев"));
    }
}
