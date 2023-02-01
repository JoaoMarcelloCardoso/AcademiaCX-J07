# Projeto Spring - Academia CX NTT Data


<br>
<br>


# Informações


* A porta do servidor é :8080

* A Database utilizada tem o nome api_spring

* Use essas informações para autenticar no banco <br>
**User = 00000000-00** <br>
**Password = senha123**

## Cliente

* O modelo cliente possui essas rotas:

- **GET** http://localhost:8080/cliente/findAll -> Retorna uma lista com todos os clientes cadastrados
- **GET** http://localhost:8080/cliente/findById/{id} -> Retorna o cliente pelo Id // {id} -> inserir o id do cliente desejado
- **POST** http://localhost:8080/cliente/novo -> Adiciona um cliente ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "nome": "Nome da pessoa",
    "cpf": "000000000-00",
    "email": "email@exemplo.com",
    "password": "senha123"
}

```

- **PUT** http://localhost:8080/cliente/update -> Atualiza um cliente ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "id": 0,
    "nome": "Nome da pessoa",
    "cpf": "000000000-00",
    "email": "email@exemplo.com",
    "password": "senha123"
}
```

<br/>

- **DELETE** para http://localhost:8080/cliente/delete -> Remove um cliente do banco, é necessário passar o Body seguindo padrão:
 ```json
{
    "id": 0,
    "nome": "Nome da pessoa",
    "cpf": "000000000-00",
    "email": "email@exemplo.com",
    "password": "senha123"
}
```

<br>


## Endereço


* O modelo endereço possui essas rotas:

- **GET** http://localhost:8080/endereco/findAll -> Retorna uma lista com todos os endereços cadastrados
- **GET** http://localhost:8080/endereco/findById/{id} -> Retorna o endereço pelo Id // {id} -> inserir o id do endereço desejado
- **GET** http://localhost:8080/endereco/findEnderecoByClienteId/{id} -> Retorna o endereço pelo Id do cliente // {id} -> inserir o id do endereço desejado
- **POST**  http://localhost:8080/endereco/save/{id} -> Adiciona um endereço ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "logradouro": "Rua",
    "numero": "Número",
    "bairro": "Bairro",
    "cidade": "Cidade",
    "uf": "Estado"

}
```
<br>

- **PUT** http://localhost:8080/endereco/update -> Atualiza um endereço ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "id": 0,
    "logradouro": "Rua",
    "numero": "Número",
    "bairro": "Bairro",
    "cidade": "Cidade",
    "uf": "Estado",
    "cliente": {
        "id": 0
    }

}
```


<br>

- **DELETE** http://localhost:8080/endereco/delete -> Remove um endereço do banco, é necessário passar o Body seguindo padrão:
```json
{
    "id": 0,
    "logradouro": "Rua",
    "numero": "Número",
    "bairro": "Bairro",
    "cidade": "Cidade",
    "uf": "Estado",
    "cliente": {
        "id": 0
    }

}
```

<br>
<br>



## Preço


* O modelo preço possui essas rotas:

- **GET** http://localhost:8080/preco/findAll -> Retorna uma lista com todos os preços cadastrados
- **GET** http://localhost:8080/preco/findById/{id} -> Retorna o endereço pelo Id // {id} -> inserir o id do preço desejado
- **GET** http://localhost:8080/preco/findPrecoByProdutoId/{id} -> Retorna o preço pelo Id do cliente // {id} -> inserir o id do preço desejado
- **POST**  http://localhost:8080/preco/save/{id} -> Adiciona um preço ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "valor": 123.45

}
```
<br>

- **PUT** http://localhost:8080/preco/update -> Atualiza um preço ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "id": 0,
    "valor": 123.45

}
```

<br>

- **DELETE** http://localhost:8080/preco/delete -> Remove um preço do banco, é necessário passar o Body seguindo padrão:
```json
{
    "id": 0,
    "valor": 123.45

}
```
<br>
<br>


## Carrinho

* O modelo carrinho possui essas rotas:

- **GET** http://localhost:8080/carrinho/findAll -> Retorna uma lista com todos os carrinhos cadastrados
- **GET** http://localhost:8080/carrinho/findById/{id} -> Retorna o carrinho pelo Id // {id} -> inserir o id do carrinho desejado
- **GET** http://localhost:8080/carrinho/findCarrinhoByClienteId/{id} -> Retorna o carrinho pelo Id do cliente // {id} -> inserir o id do cliente desejado
- **POST** http://localhost:8080/carrinho/save/{clientId} -> Adiciona um carrinho para um cliente, é necessário passar o Body seguindo padrão:
```json
{
    "data_hora": "1999-12-31 23:59:00.0000",
    "total": 123.45   
    
}

```

- **PUT** http://localhost:8080/carrinho/update -> Atualiza um carrinho ao banco, é necessário passar o Body seguindo padrão:
```json
{
    "data_hora": "1999-12-31 23:59:00.0000",
    "total": 123.45,
    "id": 0,
    "clienteDto": {
        "cpf": "000000000-00",
        "nome": "Nome da pessoa",
        "email": "Email da pessoa"
    }
}
```

<br/>

- **DELETE** para http://localhost:8080/carrinho/delete -> Remove um carrinho do banco, é necessário passar o Body seguindo padrão:
 ```json
{
    "data_hora": "1999-12-31 23:59:00.0000",
    "total": 123.45,
    "id": 0,
    "clienteDto": {
        "cpf": "000000000-00",
        "nome": "Nome da pessoa",
        "email": "Email da pessoa"
    }
}
```

<br>



## Item

* O modelo item possui essas rotas:

- **GET** http://localhost:8080/item/findAll -> Retorna uma lista com todos os itens cadastrados
- **GET** http://localhost:8080/item/findById/{id} -> Retorna os itens pelo Id // {id} -> inserir o id do iten desejado
- **GET** http://localhost:8080/item//findItemByCarrinhoId/{id} -> Retorna os itens pelo Id do carrinho // {id} -> inserir o id do carrinho desejado
- **POST** http://localhost:8080/item/save -> Adiciona um item no banco, é necessário passar o Body seguindo padrão:
```json
[
    {
        "quantidade": 1,
        "total": 123.45,
        "precoDto": {
            "valor": 60.0,
            "id": 1
        },
        "produtoDto": {
            "sku": "1",
            "nome": "Nome do produto",
            "id": 1
        },
        "carrinhoDto": {
            "data_hora": "2023-01-04T11:44:00",
            "total": 414.9,
            "id": 2,
            "clienteDto": {
                "cpf": "000000000-00",
                "nome": "Nome da pessoa",
                "email": "email@exemplo.com"
            }
        }
    }

```

- **PUT** http://localhost:8080/item/update -> Atualiza um item no banco, é necessário passar o Body seguindo padrão:
```json
[
    {
        "quantidade": 1,
        "total": 123.45,
        "id": 1,
        "precoDto": {
            "valor": 60.0,
            "id": 1
        },
        "produtoDto": {
            "sku": "1",
            "nome": "Nome do produto",
            "id": 1
        },
        "carrinhoDto": {
            "data_hora": "2023-01-04T11:44:00",
            "total": 414.9,
            "id": 2,
            "clienteDto": {
                "cpf": "000000000-00",
                "nome": "Nome da pessoa",
                "email": "email@exemplo.com"
            }
        }
    }

```

<br/>

- **DELETE** para http://localhost:8080/item/delete -> Remove um item do banco, é necessário passar o Body seguindo padrão:
```json
[
    {
        "quantidade": 1,
        "total": 123.45,
        "id": 1,
        "precoDto": {
            "valor": 60.0,
            "id": 1
        },
        "produtoDto": {
            "sku": "1",
            "nome": "Nome do produto",
            "id": 1
        },
        "carrinhoDto": {
            "data_hora": "2023-01-04T11:44:00",
            "total": 414.9,
            "id": 2,
            "clienteDto": {
                "cpf": "000000000-00",
                "nome": "Nome da pessoa",
                "email": "email@exemplo.com"
            }
        }
    }

```

<br>

## Produtos

* O modelo produtos possui essas rotas:

- **GET** http://localhost:8080/produto/findAll -> Retorna uma lista com todos os produtos cadastrados
- **GET** http://localhost:8080/produto/findById/{id} -> Retorna o produto pelo Id // {id} -> inserir o id do produto desejado
- **POST**  http://localhost:8080/produto/save -> Adiciona um produto ao banco, é necessário passar o Body seguindo padrão:
```json
{
        "sku": "1",
        "nome": "Nome do produto",
    }
```
<br>

- **PUT** http://localhost:8080/produto/update -> Atualiza um produto no banco, é necessário passar o Body seguindo padrão:
```json
{
        "sku": "1",
        "nome": "Nome do produto",
        "id": 1
    }
```

<br>

- **DELETE** http://localhost:8080/produto/delete -> Remove um produto do banco, é necessário passar o Body seguindo padrão:
```json
{
        "sku": "1",
        "nome": "Nome do produto",
        "id": 1
    }
```
<br>
<br>





## Pedidos

* O modelo pedidos possui essas rotas:

- **GET** http://localhost:8080/pedidos/listarPedidos -> Retorna uma lista com todos os pedidos
- **POST** http://localhost:8080/pedidos/criarPedido/{carrinhoId}/{enderecoId} -> Cria um pedido, seguindo o carrinho e o endereço escolhido
