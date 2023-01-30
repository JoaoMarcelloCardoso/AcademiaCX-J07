## EXERCÍCIO NTT DATA COMMERCE APPLICATION

### Funcionalidades da aplicação

- Gerenciamento de Produtos
- Gerenciamento de Clientes
- Gerenciamento de Endereços
- Gerenciamento de Itens
- Gerenciamento de Carrinhos
- Realização de compras

**OBS:** trocar o {id} abaixo pelo id desejado

### Fazendo as requisições no Postman

Para fazer as requisições da aplicação é importante utilizar o Basic Auth no Postman. Para fazer isso, siga os seguintes passos:

1. Abra o Postman e crie uma nova requisição.
2. Clique no botão "Authorization" na aba "Headers".
3. Selecione a opção "Basic Auth" na lista de opções de autorização.
4. Insira o username "joaosilva" e a senha "senha123" (todas as senhas pré salvas são senha123, você pode usar qualquer outro usuário do import.sql).
5. Execute a requisição.

**OBS** Para que funcione corretamente é importante que o import.sql tenha sido lido corretamente pelo hibernate.

### Produtos

- Obter lista de produtos
  **GET**: localhost:8080/product

- Obter um produto pelo ID
  **GET by ID**: localhost:8080/product/{id}

- Criar um novo produto
  **POST**: localhost:8080/product/save

```
{
  "id": 1,
 "sku": "pd0004",
 "nome": "testeProduto",
 "price": 29.99
 }
```

- Atualizar um produto
  **PUT**: localhost:8080/product/update
  ````
  {
   "id": 5,
  "sku": "pd0004",
  "nome": "testeProduto",
  "price": 2999.99
  }```
  ````
- Excluir um produto - o produto só pode ser excluído caso não esteja em nenhuma compra.
  **DELETE**: localhost:8080/product/delete?id={id}

### Clientes

- Obter lista de Clientes
  **GET**: localhost:8080/client

- Obter cliente por ID
  **GET by ID**: localhost:8080/client/1

- Criar um novo cliente
  **POST**: localhost:8080/client/save

```
  {
   "cpf": "00000000090",
  "nome": "pedrito silva",
  "username": "pedrito",
  "password": "aaaaaaA@"
  }
```

- Atualizar um cliente
  **PUT**: localhost:8080/client/update

```
  {
  "id": {id},
   "cpf": "00000000090",
  "nome": "pedrito silva",
  "username": "pedrito",
  "password": "aaaaaaA@"
  }
```

- Deletar um cliente
  **DELETE**: localhost:8080/client/delete?id={id}

### Endereços

- Obter lista de endereços
  **GET**: localhost:8080/address

- Obter um endereço pelo ID
  **GET by ID**: localhost:8080/address/{id}

- Criar um novo endereço
  **POST**: localhost:8080/address/save

  ```
  {
   "cep": "12234-638",
  "logradouro": "Rua Mexico",
  "numero": "0",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "client_id": 2
  }
  ```

- Atualizar um endereço
  **PUT**: localhost:8080/address/update

```

{
"id": {id},
"cep": "12234-638",
"logradouro": "Rua Mexico",
"numero": "0",
"bairro": "Centro",
"cidade": "São Paulo",
"estado": "SP",
"client_id": 2
}

```

- Deletar um endereço
  **DELETE**: localhost:8080/address/delete?id=11<br>

### Items

- Obter lista de Items
  **GET**: localhost:8080/item

- Obter item por ID
  **GET by ID**: localhost:8080/item/{id}

- Criar um novo item
  **POST**: localhost:8080/item/save

```

{
 "quantity": 2,
"product_id": 1,
"cart_id": 1
}

```

- Atualizar um cliente
  **PUT**: localhost:8080/item/update

```

{
 "id": {id},
"quantity": 2,
"product_id": 1,
"cart_id": 1
}

```

- Deletar um item
  **DELETE**: localhost:8080/item/delete?id={id}

### Carrinho

- Obter lista de carrinhos
  **GET**: localhost:8080/cart

- Obter carrinho por ID
  **GET by ID**: localhost:8080/cart/{id}

- Adiciona um novo carrinho associado a um cliente
  **POST** localhost:8080/cart/new?client_id={id_cliente}

- Deleta um carrinho
  **DELETE**: localhost:8080/cart/delete?id=1

### Compra

- Finalizando uma compra
  **POST**: localhost:8080/cart/finalizar-compra?cart_id=1&address_id=1&payment_method=pix
  **OBS**: o carrinho e o endereço devem pertencer ao mesmo cliente

- Lista de compras realizadas
  **GET**: localhost:8080/cart/purchases
