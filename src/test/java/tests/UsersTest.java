package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersTest {

    /*Teste users
    Listar todos os usuários.
    Buscar usuário por ID.
    Usuário com ID inexistente.
    Usuário com ID inválido (abc).*/

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void deveRetornarTodosOsUsuarios() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .body("[0].username", not(emptyString()));
    }

    @Test
    public void deveRetornarUsuarioPorId() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("username", not(emptyString()));
    }

    @Test
    public void deveRetornarVazioParaUsuarioInexistente() {
        String body = given()
                .when()
                .get("/users/9999")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .extract().body().asString();

        assertTrue(
                body == null ||
                        body.trim().isEmpty() ||
                        body.trim().equalsIgnoreCase("null") ||
                        body.trim().equals("{}") ||
                        body.trim().equals("[]"),
                "Esperado: corpo vazio, null, 'null', {} ou []. Recebido: " + body
        );
    }

    @Test
    public void deveTratarIdInvalidoSemErro() {
        given()
                .when()
                .get("/users/abc")
                .then()
                .statusCode(anyOf(is(400), is(404), is(200)))
                .body("status", anyOf(equalTo("error"), notNullValue()))
                .body("message", containsStringIgnoringCase("id"));
    }
}
