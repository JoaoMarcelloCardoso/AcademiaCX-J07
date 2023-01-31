# Ecommerce
Um ecommerce com Spring

A atividade consiste em, a partir das models Carrinho, Cliente, Compra, Produto e User, fazer o backend de um ecommerce que tenha:  
-  Login de admin e user  
- Mostra os produtos que podem ser selecionados para o carrinho  
- Cria o Carrinho (Que é vinculado à um cliente específico, adicionando produtos)  
- Conclui o pagamento, que é ligado à um carrinho e à um user    

## Como usar
Por padrão, a aplicação está configurada pra usar o banco de dados cxcommerce_teste2 no appplication.properties. Mas você pode e deve alterar isso.  
Par rodar a aplicação, primeiramente:
- Crie um banco de dados local com o nome que quiser e coloque esse nome no application.properties.  
- Use a seguinte query para criar as tabelas:  

> CREATE TABLE produto (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
categoria VARCHAR(255) NOT NULL,
preco BIGINT
);

> CREATE TABLE carrinho (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
produto_id BIGINT,
FOREIGN KEY (produto_id) REFERENCES produto(id)
);

> CREATE TABLE cliente (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
phone_number BIGINT,
endereco VARCHAR(255) NOT NULL,
carrinho_id BIGINT,
FOREIGN KEY (carrinho_id) REFERENCES carrinho(id)
);

> CREATE TABLE user (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
cliente_id BIGINT,
FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

> CREATE TABLE compra (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
pagamento VARCHAR(255) NOT NULL,
cliente_id BIGINT,
carrinho_id BIGINT,
FOREIGN KEY (cliente_id) REFERENCES cliente(id),
FOREIGN KEY (carrinho_id) REFERENCES carrinho(id)
);

Após criar as tabelas, rode o seguinte comando para ter certeza que todas as dependências estão instaladas:  
> mvn clean install

Rode a aplicação com:  
> mvn spring-boot:run

Entre no seu POSTMAN, onde você conseguirá enviar requisições diversas para as rotas. 
As rotas são:  
> /carrinho, /cliente, /compra, /produto, /user

As requests possíveis são:  
> GET all, GET por ID, PUT, POST, DELETE  

No seu postman antes de mais nada configure qualquer autenticação como BasicAuth e entre com um dos dois usuários possíveis:  
> user: admin; password: password ou user:user; password: password.

Após isso você estará apto a fazer qualquer requisição.  

Modelos de requisição mais comuns:  
Para dar um POST em um produto novo, por exemplo, você pode enviar para a rota /produto/: 
> {  
    "name" : "playstation5",  
    "categoria" : "eletronicos",  
    "preco" : 5000  
}

Tendo produtos já cadastrados, você pode criar um carrinho novo adicionando produtos, fazendo para a rota /carrinho/:  
> {  
  "name": "Carrinho de Compras 1",  
  "produtos": [  
    {  
      "name": "Produto 1",  
      "categoria": "Eletrônicos",  
      "preco": 1000.00  
    },  
    {  
      "name": "Produto 2",  
      "categoria": "Moda",  
      "preco": 50.00  
    }  
  ]  
}  
