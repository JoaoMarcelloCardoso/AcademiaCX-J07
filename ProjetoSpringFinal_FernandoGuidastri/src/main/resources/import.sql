INSERT INTO tb_cliente (nome, cpf, password, email) VALUES ('Guilherme','409635472-65', '$2a$10$MOc9fYjmKkIkOPQwPYYPNuablhahra1ug/JHrOGvdxAaF/Votdftq', 'guilherme@exemplo.com');
INSERT INTO tb_cliente (nome, cpf, password, email) VALUES ('Paulo','385264162-35', '$2a$10$MOc9fYjmKkIkOPQwPYYPNuablhahra1ug/JHrOGvdxAaF/Votdftq', 'paulo@exemplo.com');
INSERT INTO tb_cliente (nome, cpf, password, email) VALUES ('Julia','414685342-63', '$2a$10$MOc9fYjmKkIkOPQwPYYPNuablhahra1ug/JHrOGvdxAaF/Votdftq', 'julia@exemplo.com');
INSERT INTO tb_cliente (nome, cpf, password, email) VALUES ('admin', '00000000-00', '$2a$10$MOc9fYjmKkIkOPQwPYYPNuablhahra1ug/JHrOGvdxAaF/Votdftq', 'admin@exemplo.com');

INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua São Francisco','156','Penha','São Paulo','SP',(SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua Dominicanos','57','Pampulha','Belo Horizonte','MG',(SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua Xavier Sigaud','354','Urca','Rio de Janeiro','RJ',(SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua Jorge Mussi','414','Canasvieiras','Florianópolis','SC',(SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua Paulo Pontes','253','Esperança','Londrina','PR',(SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_endereco (logradouro, numero, bairro, cidade, uf, cliente_id) VALUES ('Rua Santo Amaro','865','Serraria','Maceió','AL',(SELECT id FROM tb_cliente WHERE cpf='414685342-63'));

INSERT INTO tb_carrinho (data_hora, total, cliente_id) VALUES ('2023-01-14 10:24:00.0000', 455.90, (SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_carrinho (data_hora, total, cliente_id) VALUES ('2023-01-04 14:44:00.0000', 414.90, (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_carrinho (data_hora, total, cliente_id) VALUES ('2023-01-12 18:28:00.0000', 214.90, (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_carrinho (data_hora, total, cliente_id) VALUES ('2023-01-05 21:19:00.0000', 603.90, (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_carrinho (data_hora, total, cliente_id) VALUES ('2023-01-17 12:32:00.0000', 89.90, (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));

INSERT INTO tb_produto (nome, sku) VALUES ('Mesa','1');
INSERT INTO tb_produto (nome, sku) VALUES ('Gaveta','2');
INSERT INTO tb_produto (nome, sku) VALUES ('Estojo','3');
INSERT INTO tb_produto (nome, sku) VALUES ('Relógio de mesa','4');
INSERT INTO tb_produto (nome, sku) VALUES ('Teclado','5');
INSERT INTO tb_produto (nome, sku) VALUES ('Mouse','6');

INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (400.00, (SELECT id FROM tb_produto WHERE sku='1'), (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (300.00, (SELECT id FROM tb_produto WHERE sku='1'), (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (70.00, (SELECT id FROM tb_produto WHERE sku='2'), (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (50.00, (SELECT id FROM tb_produto WHERE sku='2'), (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (10.00, (SELECT id FROM tb_produto WHERE sku='3'), (SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (15.00, (SELECT id FROM tb_produto WHERE sku='3'), (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (60.00, (SELECT id FROM tb_produto WHERE sku='4'), (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (45.00, (SELECT id FROM tb_produto WHERE sku='4'), (SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (250.00, (SELECT id FROM tb_produto WHERE sku='5'), (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (199.90, (SELECT id FROM tb_produto WHERE sku='5'), (SELECT id FROM tb_cliente WHERE cpf='409635472-65'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (150.00, (SELECT id FROM tb_produto WHERE sku='6'), (SELECT id FROM tb_cliente WHERE cpf='385264162-35'));
INSERT INTO tb_preco (valor, produto_id, cliente_id) VALUES (170.00, (SELECT id FROM tb_produto WHERE sku='6'), (SELECT id FROM tb_cliente WHERE cpf='414685342-63'));

INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('2', '120.90', '4', '2', '7');
INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('1', '250.00', '5', '4', '9');
INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('3', '450.00', '6', '1', '11');
INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('1', '70.00', '2', '3', '3');
INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('2', '340.00', '6', '4', '12');
INSERT INTO tb_item (quantidade, total, produto_id, carrinho_id, preco_id) VALUES ('1', '50.00', '2', '5', '4');
