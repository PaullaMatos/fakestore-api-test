#  Antecipação de Cenários para o Frontend - FakeStore API

> Documento criado com base no desafio técnico para Analista de Qualidade - Suzano  
> Data: 11/07/2025

---

##  Objetivo

Antecipar possíveis comportamentos e falhas que o **frontend** pode enfrentar ao consumir a API da [FakeStore](https://fakestoreapi.com), com base nos testes manuais e automatizados realizados para os endpoints de produtos, usuários, carrinho e autenticação.

---

##  Produtos (`/products`)

###  Cenário 1: Produto sem título
- **Dado:** Um produto sem o campo `title`
- **Quando:** O frontend renderiza a listagem de produtos
- **Então:** Deve exibir a mensagem padrão `"Produto sem título disponível"` ou um placeholder amigável

###  Cenário 2: Produto com `price` igual a 0
- **Então:** Mostrar `"Preço indisponível"` ou `"Consulte valor"`

###  Cenário 3: Produto com `category` nulo ou vazio
- **Então:** A categoria deve ser substituída por `"Categoria desconhecida"`

###  Cenário 4: Produto inexistente (ID inválido ou não encontrado)
- **Então:** O frontend deve exibir uma página ou modal de erro como `"Produto não encontrado"`

---

##  Usuários (`/users`)

###  Cenário 1: Usuário sem nome
- **Então:** Exibir `"Nome não disponível"` em cards, perfis ou comentários

###  Cenário 2: Campos de endereço ausentes ou incompletos
- **Então:** Endereços devem ser exibidos com fallback ou ocultados com aviso

###  Cenário 3: ID inválido
- **Então:** Exibir tela de erro 404 ou mensagem de `"Usuário não encontrado"`

---

##  Carrinho (`/carts`)

###  Cenário 1: Carrinho com produtos que não existem mais
- **Então:** Exibir alerta `"Produto não disponível no momento"` no carrinho

### Cenário 2: Carrinho sem produtos
- **Então:** Mostrar mensagem `"Seu carrinho está vazio"`

---

##  Autenticação (`/auth/login`)

###  Cenário 1: Login com credenciais inválidas
- **Então:** Exibir mensagem clara como `"Usuário ou senha inválidos"`

###  Cenário 2: Token ausente ou nulo
- **Então:** Evitar que o usuário navegue na aplicação sem autenticação válida

###  Cenário 3: Formulário de login vazio
- **Então:** Validar os campos obrigatórios antes de enviar requisição

---

##  Considerações Finais

- Todos os cenários foram pensados com foco na **experiência do usuário** e na **robustez do frontend**.
- A responsabilidade do frontend é **tratar comportamentos inesperados da API** com feedbacks claros.
- Estes casos antecipados servem como base para testes exploratórios, E2E e cobertura visual.

---


**Desafio:** FakeStore API - Suzano  

