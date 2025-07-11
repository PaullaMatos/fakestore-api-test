package tests;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsTest {

    /* teste - products
 Listar todos os produtos.
 Buscar produto por ID.
 Produto com ID inexistente.
 Produto com ID inválido (abc).*/

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void deveRetornarTodosOsProdutos() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].price", greaterThan(0f));
    }

    @Test
    public void deveRetornarProdutoPorId() {
        given()
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .body("price", greaterThan(0f))
                .body("category", not(emptyString()));
    }

    @Test
    public void deveRetornarErroComIdInexistente() {
        given()
                .when()
                .get("/products/9999")
                .then()
                .statusCode(anyOf(is(404), is(200)))
                .body(isEmptyOrNullString());
    }

    @Test
    public void deveTratarIdInvalidoSemErro() {
        given()
                .when()
                .get("/products/abc")
                .then()
                .statusCode(200)
                .body(isEmptyOrNullString());
    }
}
