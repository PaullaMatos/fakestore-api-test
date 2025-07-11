package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartsTest {


    /* teste carts
Listar todos os carrinhos.
Buscar carrinho por ID.
Carrinho com ID inexistente.
Carrinho com ID inválido (abc).
Atualizar carrinho com PUT.
Excluir carrinho com DELETE.*/


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void deveRetornarTodosOsCarrinhos() {
        given()
                .when()
                .get("/carts")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    public void deveRetornarCarrinhoPorId() {
        given()
                .when()
                .get("/carts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("products", not(empty()));
    }

    @Test
    public void deveTratarCarrinhoInexistente() {
        String response =
                given()
                        .when()
                        .get("/carts/9999")
                        .then()
                        .statusCode(anyOf(is(200), is(404)))
                        .extract().asString();

        assertTrue(
                response == null ||
                        response.isEmpty() ||
                        response.equalsIgnoreCase("null") ||
                        response.equals("{}") ||
                        response.equals("[]") ||
                        response.contains("error"),
                "Esperado: corpo vazio, null, 'null', {} ou []. Recebido: " + response
        );
    }

    @Test
    public void deveTratarIdInvalidoSemErro() {
        given()
                .when()
                .get("/carts/abc")
                .then()
                .statusCode(anyOf(is(200), is(400), is(404)))
                .body("status", equalTo("error"))
                .body("message", containsStringIgnoringCase("id"));
    }

    @Test
    public void deveCriarNovoCarrinho() {
        String payload = """
        {
            "userId": 5,
            "date": "2025-07-11",
            "products": [
                {
                    "productId": 1,
                    "quantity": 2
                },
                {
                    "productId": 2,
                    "quantity": 1
                }
            ]
        }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/carts")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", equalTo(5))
                .body("products.size()", greaterThan(0));
    }

    @Test
    public void deveAtualizarCarrinhoExistente() {
        String payloadAtualizacao = """
        {
            "userId": 5,
            "date": "2025-07-11",
            "products": [
                {
                    "productId": 1,
                    "quantity": 3
                }
            ]
        }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(payloadAtualizacao)
                .when()
                .put("/carts/7")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("userId", equalTo(5))
                .body("products[0].productId", equalTo(1))
                .body("products[0].quantity", equalTo(3));
    }

    @Test
    public void deveExcluirCarrinhoPorId() {
        int idParaExcluir = 6;

        given()
                .when()
                .delete("/carts/" + idParaExcluir)
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }


}
