**#  FakeStore API - Testes Automatizados

Este projeto contém testes automatizados desenvolvidos em Java utilizando **JUnit 5** e **RestAssured** para validar os principais endpoints da [FakeStore API](https://fakestoreapi.com/).

---

****##  Tecnologias Utilizadas****

- Java 21
- Maven
- JUnit 5
- RestAssured
- IntelliJ IDEA

---

**##  Estrutura do Projeto****
fakestore-api-test/
├── pom.xml
└── src
└── test
└── java
└── tests
├── ProductsTest.java
├── UsersTest.java
├── CartsTest.java
└── AuthTest.java


---

**##  Endpoints Testados

### Produtos
- `GET /products` → Lista todos os produtos
- `GET /products/{id}` → Retorna um produto específico
- `GET /products/9999` → Testa um ID inexistente
- `GET /products/abc` → Testa um ID inválido

###  Usuários
- `GET /users` → Lista todos os usuários
- `GET /users/{id}` → Retorna um usuário específico
- `GET /users/9999` → Testa usuário inexistente
- `GET /users/abc` → Testa ID inválido

###  Carrinhos
- `GET /carts` → Lista todos os carrinhos
- `GET /carts/{id}` → Retorna carrinho específico
- `GET /carts/9999` → Testa carrinho inexistente
- `GET /carts/abc` → Testa ID inválido

###  Autenticação
- `POST /auth/login` → Realiza login com credenciais válidas
- `POST /auth/login` → Testa login com credenciais inválidas

---

## Como Executar os Testes

1. Clone este repositório:
```bash
git cloneh https://github.com/PaullaMatos/fakestore-api-test.git**

2. Navegue até a pasta do projeto:
   cd fakestore-api-test

3. Execute os testes com Maven:
   mvn test


 Observações Importantes
As requisições são feitas diretamente para a FakeStore API pública, portanto a resposta pode variar conforme o estado atual da API.
Os testes tratam tanto respostas positivas (200) quanto respostas negativas (404, mensagens de erro, ou corpo vazio).

