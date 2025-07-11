package tests.auth;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthTest {

        /*Teste - auth/login
Login com credenciais válidas.
Login com credenciais inválidas..*/

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void deveAutenticarUsuarioComCredenciaisValidas() {
        String payload = """
            {
                "username": "mor_2314",
                "password": "83r5^_"
            }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void deveFalharAutenticacaoComCredenciaisInvalidas() {
        String payload = """
            {
                "username": "usuarioInvalido",
                "password": "senhaErrada"
            }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(anyOf(is(401), is(403), is(400)));
    }
}
