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
