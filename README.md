# Projeto Final Java/Spring - Academia CX NTT Data

* Aluno: Gianluca Notari Magnabosco da Silva

<br/>
<br/>


# Sobre o projeto

* O projeto foi realizado com o Maven, e todas as dependências estão contidas no arquivo `pom.xml`

## Dependências (arquivo pom.xml)

* `spring-boot-starter-data-jpa`

* `spring-boot-starter-web`

* `mysql-connector-java`

* `spring-boot-starter-test`

* `javax.persistence-api`

* `jackson-dataformat-xml`

* `modelmapper`

* `spring-boot-starter-security`

### Plugins

* `spring-boot-maven-plugin`

* `maven-resources-plugin`

<br/>
<br/>

## Propriedades da aplicação (arquivo application.properties)

* O banco de dados será criado pelo JPA do próprio Spring ao iniciar a aplicação, e será removido quando a aplicação for terminada (essa configuração pode ser alterada no arquivo `application.properties` [`spring.jpa.hibernate.ddl-auto`]).

* A porta do servidor é a 8080, ela pode ser alterada no arquivo `application.properties` (configuração `server.port`)

* O projeto foi realizado utilizando o SGBD MySQL, se desejado, é possível alterar o SGBD no arquivo `application.properties`, a dependência do mesmo deve ser adicionada ao arquivo `pom.xml`

* O projeto utiliza a Database cxcommerce, se necessário crie-a ou altere a configuração `spring.datasource.url` no arquivo `application.properties`

* O login e senha para acesso ao MySQL podem ser alterados no arquivo `application.properties`, configurações `spring.datasource.username` e `spring.datasource.password`

* O projeto utiliza da dependência `jackson-dataformat-xml` e suas configurações podem ser alteradas no arquivo `application.properties`

* Como padrão, a timezone está setada como `Brazil/East`, locale como `pt-BR`, dateformat como `dd/MM/yyyy HH:mm` e qualquer registro nulo que venha a retornar de uma response não será mostrado (`spring.jackson.default-property-inclusion = NON_NULL`)

<br/>
<br/>

## Para rodar a aplicação

* Faça clone deste repositório em sua máquina local

* Instale as dependências necessárias para rodar a aplicação

* Rode a aplicação

<br/>
<br/>

# Estrutura da aplicação

* O pacote `config` é responsável pelos arquivos de configuração da aplicação, tanto de mapeamento quanto de segurança e autenticação;

* O pacote `controller` contém todas as controladoras da aplicação, é onde são mapeadas as rotas e onde as services são chamadas;

* O pacote `handler` contém o handler para tratamento de exceções, e as exceções de fato da aplicação;

* O pacote `model` contém todas as classes de modelo (request) da aplicação, bem como as classes de dto (response);

* O pacote `repository` contém os repositórios de cada modelo, mapeando ao banco de dados;

* O pacote `service` contém as services da aplicação, é onde ocorre a lógica de negócio e demais validações;

* O pacote `utils` contém uma classe de validação com métodos estáticos, os métodos desta classe são chamados pela service para realizar a validação de dados;

<br/>
<br/>

## Para acessar os recursos da aplicação recomenda-se o uso do Postman

* Instale o Postman no seu computador

* Abra-o e crie uma nova requisição HTTP

* Insira como URL: http://localhost:8080/

* Selecione o método e a rota desejados

* Uma lista das rotas disponíveis com suas respectivas descrições consta abaixo

<br/>
<br/>

# Modelos/Rotas

* Para acessar as rotas é necessário realizar autenticação com username e password;

* Existem 3 clientes já presentes no banco de dados (fulano, ciclano, joana), todos possuem a password (senha123);

* É possível se autenticar utilizando um desses clientes, ou posteriormente é possível realizar seu cadastro e utilizá-lo;

* Para realizar a autenticação utilizando Postman -> vá para a aba Authorization, em seguida selecione o tipo Basic Auth e insira o username e password;

<br/>

## Cadastro

* O modelo cadastro possui um cliente e um endereço;

* É através desta rota que os clientes realizarão seus cadastros;

* Para realizar um cadastro é necessário acessar a rota http://localhost:8080/cadastro utilizando o método HTTP POST;

* É necessário informar no corpo da requisição (RequestBody) todos os dados para inserção de cliente e para inserção de endereço;

```json
{
    "cliente": {
        "nome": "Fulano de Tal",
        "cpf": "98023171011",
        "email": "amigao@teste.com",
        "telefone": "(47) 99907-0594",
        "dataNasc": "2000-06-14",
        "username": "fulano",
        "password": "senha123"
    },
    "endereco": {
        "cep": "12345678",
        "logradouro": "Rua das Flores",
        "numero": "123",
        "complemento": "AP 169",
        "bairro": "Centro",
        "cidade": "Curitiba",
        "uf": "PR"
    }
}
```

* Todos os dados apresentados são obrigatórios;

* Existe validação para CPF (com ou sem pontuação), E-mail, Telefone (com ou sem parênteses), CEP (com ou sem hífen), Número (com ou sem [Nn].º), UF (2 dígitos);

* Note que os campos CPF, E-mail, Telefone e Username são UNIQUE (não podem ser duplicados);

* Todos os dados são limpos antes da inserção no banco;

* A senha informada é criptografada com BCrypt e inserida no banco de dados;


<br/>
<br/>

## Cliente

* O modelo cliente possui id, nome, cpf, e-mail, telefone, data de nascimento, username e password;

* O modelo cliente contém as rotas:

1. Método HTTP GET para http://localhost:8080/cliente -> Retorna uma lista com todos os clientes cadastrados na aplicação;
2. Método HTTP GET para http://localhost:8080/cliente/{id} (onde {id} = id do cliente) -> Retorna o cliente cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/cliente/filter?cpf={cpf}&email={email}&telefone={telefone} -> Retorna uma lista de clientes que batem com o filtro especificado (os 3 parâmetros são opcionais);
4. Método HTTP POST para http://localhost:8080/cliente/save -> Adiciona um cliente ao banco, é necessário passar no RequestBody o objeto referente ao cliente;
```json
{
    "nome": "Fulano de Tal",
    "cpf": "98023171011",
    "email": "amigao@teste.com",
    "telefone": "(47) 99907-0594",
    "dataNasc": "2000-06-14",
    "username": "fulano",
    "password": "senha123"
}

```
* Todos os dados apresentados são obrigatórios;

* Existe validação para CPF (com ou sem pontuação), E-mail, Telefone (com ou sem parênteses);

* Note que os campos CPF, E-mail, Telefone e Username são UNIQUE (não podem ser duplicados);

* Todos os dados são limpos antes da inserção no banco;

* A senha informada é criptografada com BCrypt e inserida no banco de dados;

<br/>

5. Método HTTP PUT para http://localhost:8080/cliente/update -> Atualiza um cliente no banco, é necessário passar no RequestBody o objeto referente ao cliente;
```json
{
    "id": 1,
    "nome": "Fulano de Tal",
    "cpf": "98023171011",
    "email": "amigao@teste.com",
    "telefone": "(47) 99907-0594",
    "dataNasc": "2000-06-14",
    "username": "fulano",
    "password": "senha123"
}

```

* O id precisa ser especificado no RequestBody;

<br/>

6. Método HTTP DELETE para http://localhost:8080/cliente/delete?id={id} (onde {id} = id do cliente a ser removido) -> Remove um cliente do banco;

* O cliente só será removido caso não tenha realizado nenhum pedido;

<br/>
<br/>


## Endereço

* O modelo endereço possui id, cep, logradouro, número, complemento, bairro, cidade e uf;

* O modelo endereço contém as rotas:

1. Método HTTP GET para http://localhost:8080/endereco -> Retorna uma lista com todos os endereços cadastrados na aplicação;
2. Método HTTP GET para http://localhost:8080/endereco/{id} (onde {id} = id do endereço) -> Retorna o endereço cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/endereco/cliente?id={id} (onde {id} = id do cliente) -> Retorna uma lista de endereços cadastrados em nome do cliente;
4. Método HTTP GET para http://localhost:8080/endereco/filter?bairro={bairro}&cidade={cidade}&uf={uf} -> Retorna uma lista de endereços que batem com o filtro especificado (os 3 parâmetros são opcionais);
5. Método HTTP POST para http://localhost:8080/endereco/save -> Adiciona um endereço ao banco, é necessário passar no RequestBody o objeto referente ao endereço;
```json
{
    "cep": "12345678",
    "logradouro": "Rua das Flores",
    "numero": "123",
    "complemento": "AP 169",
    "bairro": "Centro",
    "cidade": "Curitiba",
    "uf": "PR",
    "cliente": {
        "id": 1
    }
}
```

* Todos os dados apresentados são obrigatórios;

* Existe validação para CEP (com ou sem hífen), Número (com ou sem [Nn].º), UF (2 dígitos);

* Todos os dados são limpos antes da inserção no banco;

<br/>

6. Método HTTP PUT para http://localhost:8080/endereco/update -> Atualiza um endereço no banco, é necessário passar no RequestBody o objeto referente ao endereço;
```json
{
    "id": 1,
    "cep": "12345678",
    "logradouro": "Rua das Flores",
    "numero": "123",
    "complemento": "AP 169",
    "bairro": "Centro",
    "cidade": "Curitiba",
    "uf": "PR",
    "cliente": {
        "id": 1
    }
}
```

* O id precisa ser especificado no RequestBody;

<br/>

7. Método HTTP DELETE para http://localhost:8080/endereco/delete?id={id} (onde {id} = id do endereço a ser removido) -> Remove um endereço do banco;

* O endereço só será removido caso o cliente que contenha o endereço tenha mais de um endereço além desse (Cliente não pode não ter endereço);

<br/>
<br/>


## ViaCep

* O modelo ViaCep possui cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi;

* O modelo ViaCep contém as rotas:

1. Método HTTP GET para http://localhost:8080/cep/{cep} (onde {cep} = cep a ser consultado) -> Consulta um CEP na API do ViaCep e retorna;

* O CEP possui validação, pode tanto ser inserido com hífen ou sem;

* Se o CEP não for encontrado ou for inválido, é informado ao usuário;

<br/>
<br/>



## Produto

* O modelo produto possui id, sku, nome e preço;

* O modelo produto contém as rotas:

1. Método HTTP GET para http://localhost:8080/produto -> Retorna uma lista com todos os produtos cadastrados na aplicação;
2. Método HTTP GET para http://localhost:8080/produto/{id} (onde {id} = id do produto) -> Retorna o produto cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/produto/filter?nome={nome}&sku={sku} -> Retorna uma lista de produtos que batem com o filtro especificado (os 2 parâmetros são opcionais);
4. Método HTTP POST para http://localhost:8080/produto/save -> Adiciona um produto ao banco, é necessário passar no RequestBody o objeto referente ao produto;
```json
{
    "sku": "CX-NTT-E-65455",
    "nome": "Chapéu",
    "preco": 99.90
}
```

* Todos os dados apresentados são obrigatórios;

* O SKU é UNIQUE (não pode ser duplicado);

<br/>

5. Método HTTP PUT para http://localhost:8080/produto/update -> Atualiza um produto no banco, é necessário passar no RequestBody o objeto referente ao produto;
```json
{
    "id": 1,
    "sku": "CX-NTT-E-65455",
    "nome": "Chapéu",
    "preco": 99.90
}
```

* O id precisa ser especificado no RequestBody;

<br/>

6. Método HTTP DELETE para http://localhost:8080/produto/delete?id={id} (onde {id} = id do produto a ser removido) -> Remove um produto do banco;

* O produto só será removido caso não esteja em nenhum carrinho ou pedido (Produto não pode ser removido se já tiver sido vendido);

<br/>
<br/>

## Carrinho

* O modelo carrinho possui id, data, total, ativo e cliente;

* Se um carrinho estiver inativo, significa que ele faz parte de um pedido que foi concluído com sucesso;

* Um cliente só pode ter um carrinho ativo por vez;

* O modelo carrinho contém as rotas:

1. Método HTTP GET para http://localhost:8080/carrinho -> Retorna uma lista com todos os carrinhos cadastrados na aplicação;
2. Método HTTP GET para http://localhost:8080/carrinho/{id} (onde {id} = id do carrinho) -> Retorna o carrinho cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/carrinho/cliente?id={id} (onde {id} = id do cliente) -> Retorna uma lista com os carrinhos do cliente cujo id foi passado;
4. Método HTTP POST para http://localhost:8080/carrinho/save -> Adiciona um carrinho ao banco, é necessário passar no RequestBody o id de cliente para qual vai ser criado o carrinho;
```json
{
    "cliente": {
        "id": 1
    }
}
```

* Todos os dados apresentados são obrigatórios;

* Um cliente só pode ter um carrinho ativo por vez;

<br/>

5. Método HTTP PUT para http://localhost:8080/carrinho/update -> Atualiza um carrinho no banco, é necessário passar no RequestBody o objeto referente ao produto;
```json
{
    "id": 1,
    "cliente": {
        "id": 1
    }
}
```

* O id precisa ser especificado no RequestBody;

* Um cliente só pode ter um carrinho ativo por vez;

<br/>

6. Método HTTP DELETE para http://localhost:8080/carrinho/delete?id={id} (onde {id} = id do carrinho a ser removido) -> Remove um carrinho do banco;
7. Método HTTP DELETE para http://localhost:8080/carrinho/delete/cliente?id={id} (onde {id} = id do cliente cujo carrinho vai ser removido) -> Remove um carrinho do banco;


<br/>
<br/>

## Item

* O modelo item possui id, quantidade, total, produto e carrinho;

* O modelo item contém as rotas:

1. Método HTTP GET para http://localhost:8080/item -> Retorna uma lista com todos os itens cadastrados na aplicação;
2. Método HTTP GET para http://localhost:8080/item/{id} (onde {id} = id do item) -> Retorna o item cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/item/carrinho?id={id} (onde {id} = id do carrinho) -> Retorna uma lista com os itens do carrinho cujo id foi passado;
4. Método HTTP POST para http://localhost:8080/item/save -> Adiciona um item ao banco, é necessário passar no RequestBody a quantidade, o id de produto e id de carrinho;
```json
{
    "quantidade": 2,
    "produto": {
        "id": 1
    },
    "carrinho": {
        "id": 1
    }
}
```

* Todos os dados apresentados são obrigatórios;

<br/>

5. Método HTTP PUT para http://localhost:8080/item/update -> Atualiza um item no banco, é necessário passar no RequestBody o objeto referente ao produto;
```json
{
    "id": 1,
    "quantidade": 2,
    "produto": {
        "id": 1
    },
    "carrinho": {
        "id": 1
    }
}
```

* O id precisa ser especificado no RequestBody;

<br/>

6. Método HTTP DELETE para http://localhost:8080/item/delete?id={id} (onde {id} = id do item a ser removido) -> Remove um item do banco;
7. Método HTTP DELETE para http://localhost:8080/item/delete/carrinho?id={id} (onde {id} = id do carrinho cujo item vai ser removido) -> Remove um item do banco;


<br/>
<br/>

## Pedido

* O modelo pedido possui id, data, método de pagamento, endereço e carrinho;

* O modelo pedido contém as rotas:

1. Método HTTP GET para http://localhost:8080/pedido -> Retorna uma lista com todos os pedidos realizados;
2. Método HTTP GET para http://localhost:8080/pedido/{id} (onde {id} = id do pedido) -> Retorna o pedido cujo id foi passado;
3. Método HTTP GET para http://localhost:8080/pedido/cliente?id={id} (onde {id} = id do cliente) -> Retorna uma lista com os pedidos realizados pelo cliente cujo id foi passado;
4. Método HTTP POST para http://localhost:8080/pedido/save -> Adiciona um pedido ao banco, é necessário passar no RequestBody o método de pagamento, o id de carrinho e o id de endereço;
```json
{
    "metodoPagamento": "Pix",
    "carrinho": {
        "id": 1
    },
    "endereco": {
        "id": 2
    }
}
```

* Todos os dados apresentados são obrigatórios;

* Opcionalmente pode-se adicionar cupons de desconto como parâmetros de requisição (Ex: http://localhost:8080/pedido/save?cupom=academiacx10)

* Ao inserir o cupom de desconto, o valor total do carrinho presente no pedido é decrescido de acordo com o cupom

* Cupons disponíveis:
1. academiacx10 - 10% de desconto;
2. academiacx20 - 20% de desconto;
3. academiacx30 - 30% de desconto;

* Um pedido só será realizado se o cliente presente no carrinho for igual ao cliente presente no endereço;

<br/>

5. Método HTTP POST para http://localhost:8080/pedido/save/falha -> Simula uma falha ao processar pedido;