# academiacx-j01
Estes são os JSONs para o funcionamento do sistema.
Estão na ordem para o funcionamento correto.
```
//User-Cliente - O cadastro de usuário gera um cliente e um carrinho.
     {
        "nome":"Maria",
        "email":"pedro.leonardo7738@gmail.com",
        "cliente_id":"0",
        "username":"maria",
        "password":"123",
        "cpf":"64947612088"
      }
```
```
//Endereco
      {
        "id":"1",
        "cep": "12234-638",
        "logradouro": "Rua das Flores",
        "numero": "123",
        "bairro": "Centro",
        "cidade": "Curitiba",
        "uf": "PR",
        "cliente_id": "1"
      }
```
```
//Produto
 {
        "id":"1",
        "sku":"Jeans_1",
        "nome":"Calça Jeans",
        "preco":"20.00"
      }
 ```
 ```
 //Item
  {
      "quantidade":"2",
      "produto_id":"1",
       "carrinho_id":"1"
  }
 ```
 ```
 //Compra
    {
         "pagamento_id":"1"
         "endereco_id":"1"
         "carrinho_id":"1"
    }
```
