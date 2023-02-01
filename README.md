# Projeto E-commerce 

### Para você ter acesso a esse projeto, faça o clone do mesmo para a sua máquina.
<br>

![Sem título](https://user-images.githubusercontent.com/98417184/215933497-f2c97f6e-4e82-41e3-91d4-0ae45cf8063e.png)

### Agora será preciso instalar os seguintes aplicativos:

+ **MySql**, você pode baixar ele aqui https://www.mysql.com/
+ **Intellij** (versão Ultimate) https://www.jetbrains.com/idea/download/#section=windows
+ **Postman** https://www.postman.com/downloads/

### Com eles instalados, vamos para a configuração do Spring Boot no Intellij
+ Vá em File / New / Project;
+ Usaremos o **Spring initalizr**;
+ Selecione o Maven;
+ JDK 17 e Java 17 (a versão 11 deu um pequeno erro de compatibilidade. Use a versão 17 ou acima);
+ Instale o **Spring web**, **Spring data JPA** e **H2 Database**;
+ Insira o plugin abaixo no pom.xml.

### Plugin Maven
```
<plugin> 
<groupId>org.apache.maven.plugins</groupId> 
<artifactId>maven-resources-plugin</artifactId> 
<version>3.1.0</version> 
</plugin>
```

### Dependências Spring Security e MySql connector

```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>

 <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
 </dependency>
```



### Padrão MVC
MVC é um padrão de projeto, e a sigla significa: Model, View e Controller (Modelo, Visão e Controlador).
+ **Model** é responsável pela conexão com o banco de dados;
+ **View** é responsável por "imprimir na tela algo para o usuário" ;
+ **Controller** fica recebendo chamadas e retornando dados;
+ **Service** é para separar as regras de negócio, regras da aplicação e regras de apresentação para que as mesmas possam ser facilmente testadas e reutilizadas em outras partes do seu programa;
+ **Repository**  responsável pela comunicação com os dados que os Services precisam.

## O Projeto
Para o projeto foram criadas as entidades: Cliente, Carrinho, Produto, Item, Endereco, Preco e Pedido.

### Cliente
Foi criado o findAll(), findById(), save(), update(), delete(), filter(username), findcpf().

### Carrinho
Foi criado findAll(), findById(), save(), update(), delete() e findByClienteId().

### Endereco
Foi criado findAll(), findById(), save(), update(), delete() e findByClienteId().

### Item
Foi criado findAll(), findById(), save(), update(), delete(), findByCarrinhoId() e deleteByCarrinhoId().

### Preco
Foi criado findAll(), findById(), save(), update(), delete() e findByProdutoId().

### Produto
Foi criado findAll(), findById(), save(), update(), delete().


### Pedido 
Foi criado findAll(), findById(), save(), update(), delete() e findHistory().
Esse findHistory era para gerar o histórico dos pedidos mas não consegui finalizar ainda.

### O Banco de dados pode ser preenchido com as informações abaixo. 
+ Insira elas no arquivo **import.sql** na pasta **resources**
<br>

```
INSERT INTO tb_cliente(cpf, nome, username, password) VALUES ('94614821910', 'José', 'jose', 'teste123');
INSERT INTO tb_cliente(cpf, nome, username, password) VALUES ('72667616979', 'Maria', 'mmaria', 'teste456');
INSERT INTO tb_cliente(cpf, nome, username, password) VALUES ('50629451940', 'João', 'joaogrilo', 'teste789');

INSERT INTO tb_produto(sku, nome) VALUES ('2354FGV23', 'Teclado');
INSERT INTO tb_produto(sku, nome) VALUES ('11885ABGR', 'Placa-mãe');
INSERT INTO tb_produto(sku, nome) VALUES ('773WDR3VB', 'Mouse');

INSERT INTO tb_preco(valor, produto_id, cliente_id) VALUES (100.00, 1, 1);
INSERT INTO tb_preco(valor, produto_id, cliente_id) VALUES (200.00, 2, 2);
INSERT INTO tb_preco(valor, produto_id, cliente_id) VALUES (50.00, 3, 3);

INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('81270280', 'Rua Erval Velho', 'Cidade Industrial', 'Curitiba', 'PR', 1);
INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('82980445', 'Rua Márcia Regina Pavanelo', 'Cajuru', 'Curitiba', 'PR', 1);
INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('82700660', 'Rua Joel Jensen Júnior', 'Barreirinha', 'Curitiba', 'PR', 2);
INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('82720050', 'Rua Clementina Kulik', 'Santa Cândida', 'Curitiba', 'PR', 2);
INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('82520020', 'Rua Arquimedes Cruz', 'Jardim Social', 'Curitiba', 'PR', 3);
INSERT INTO tb_endereco(cep, logradouro, bairro, cidade, uf, cliente_id) VALUES ('80430126', 'Alameda Júlia da Costa', 'Centro', 'Curitiba', 'PR', 3);

INSERT INTO tb_carrinho(datahora, total, cliente_id) VALUES ('2023-01-02', 10, 1);
INSERT INTO tb_carrinho(datahora, total, cliente_id) VALUES ('2022-12-24', 20, 2);
INSERT INTO tb_carrinho(datahora, total, cliente_id) VALUES ('2022-09-12', 10, 3);

INSERT INTO tb_item(quantidade, total, produto_id, preco_id, carrinho_id) VALUES (10, 50.00, 1, 1, 1);
INSERT INTO tb_item(quantidade, total, produto_id, preco_id, carrinho_id) VALUES (100, 30.00, 2, 2, 2);
INSERT INTO tb_item(quantidade, total, produto_id, preco_id, carrinho_id) VALUES (40, 20.00, 3, 3, 3);
```

### Senha para acesso ao banco 
+ username: **leandro**
+ password: **senha123**

### Server Port: 8090



