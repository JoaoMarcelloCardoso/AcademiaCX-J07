INSERT INTO tb_cliente (cpf, nome, email, telefone, data_nasc, username, password) VALUES ('12345678900', 'Fulano de Tal', 'fulano@teste.com', '47999050594', '2000-06-14 09:42:00.0000', 'fulano', '$2a$10$3GdjKhUg.BMG4Z95e0bWf.PkapfixNoWknAw/2pCK9xRBo25EtZJe');
INSERT INTO tb_cliente (cpf, nome, email, telefone, data_nasc, username, password) VALUES ('98765432100', 'Ciclano da Silva', 'ciclano@teste.com', '41998657734', '1997-12-29 17:50:00.0000', 'ciclano', '$2a$10$3GdjKhUg.BMG4Z95e0bWf.PkapfixNoWknAw/2pCK9xRBo25EtZJe');
INSERT INTO tb_cliente (cpf, nome, email, telefone, data_nasc, username, password) VALUES ('12332112332', 'Joana de Castro', 'joana@teste.com', '52988375743', '1965-03-20 20:35:00.0000', 'joana', '$2a$10$3GdjKhUg.BMG4Z95e0bWf.PkapfixNoWknAw/2pCK9xRBo25EtZJe');



INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('12345678', 'Rua das Flores', 123, 'AP 169', 'Centro', 'Curitiba', 'PR', 1);
INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('87654321', 'Avenida Guepardo', 456, 'AP 101', 'Batel', 'Curitiba', 'PR', 1);

INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('12332123', 'Avenida do Estado', 1337, 'AP 30', 'Guepardo', 'Mafra', 'SC', 2);
INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('32112321', 'Avenida Visconde de Guarapuava', 4445, 'AP 42', 'Passo', 'Mafra', 'SC', 2);

INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('13377331', 'Rua Ciclano Flores', 128, 'AP 135', 'Bairro Alto', 'Porto Alegre', 'RS', 3);
INSERT INTO tb_endereco (cep, logradouro, numero, complemento, bairro, cidade, uf, cliente_id) VALUES ('96400020', 'Travessa Francisco Ferdinando', 256, 'AP 57', 'Centro', 'Passo Fundo', 'RS', 3);



INSERT INTO tb_carrinho (data, cliente_id, ativo) VALUES ('2023-01-18 09:42:00.0000', 1, true);
INSERT INTO tb_carrinho (data, cliente_id, ativo) VALUES ('2023-01-18 13:37:00.0000', 2, true);
INSERT INTO tb_carrinho (data, cliente_id, ativo) VALUES ('2023-01-19 16:20:00.0000', 3, true);



INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-A-4321', 'Calça Jeans', '200.00');
INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-B-1234', 'Coturno', '70.00');
INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-C-5432', 'Bombacha', '90.00');
INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-D-2345', 'Boné', '59.90');
INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-E-6543', 'Chapéu', '99.90');
INSERT INTO tb_produto (sku, nome, preco) VALUES ('CX-NTT-F-3456', 'Escova de Dentes', '12.25');



INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (2, 1, 1);
INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (3, 2, 1);

INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (3, 3, 2);
INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (2, 4, 2);

INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (1, 5, 3);
INSERT INTO tb_item (quantidade, produto_id, carrinho_id) VALUES (2, 6, 3);

