
INSERT INTO tb_product (sku, nome, price) VALUES ('P001', 'Celular Samsung', '1999.99');
INSERT INTO tb_product (sku, nome, price) VALUES ('P002', 'Notebook Dell', '2999.99');
INSERT INTO tb_product (sku, nome, price) VALUES ('P003', 'Smartwatch Samsung', '499.99');
INSERT INTO tb_product (sku, nome, price) VALUES ('P004', 'Tablet Samsung', '799.99');
INSERT INTO tb_product (sku, nome, price) VALUES ('P005', 'TV LG 55 polegadas', '2999.99');
INSERT INTO tb_product (sku, nome, price) VALUES ('P006', 'Ventilador de Teto', '499.99');




INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678901', 'João Silva', 'joao.silva@gmail.com', 'joaosilva', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');
INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678902', 'Maria Souza', 'maria.souza@gmail.com', 'mariasouza', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');
INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678903', 'José Pereira', 'jose.pereira@gmail.com', 'josepereira', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');
INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678904', 'Ana Rodriguez', 'ana.rodriguez@gmail.com', 'anarodriguez', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');
INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678905', 'Fabio Almeida', 'fabio.almeida@gmail.com', 'fabioalmeida', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');
INSERT INTO tb_client (cpf, nome, email, username, password) VALUES ('12345678906', 'Carla Nunes', 'carla.nunes@gmail.com', 'carlanunes', '$2a$10$Scf06dfN8ApPVafeV42A3eteolxzZLqR5qouYK53uQtnbz1iNngUG');

INSERT INTO tb_cart (total, client_id) VALUES ('2499.98', '1');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '1999.99', '1', '1');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '499.99', '3', '1');

INSERT INTO tb_cart (total, client_id) VALUES ('3299.98', '2');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '2999.99', '2', '2');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '299.99', '4', '2');

INSERT INTO tb_cart (total, client_id) VALUES ('3499.98', '3');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '2999.99', '5', '3');
INSERT INTO tb_item (quantity, total, product_id, cart_id) VALUES ('1', '499.99', '6', '3');


INSERT INTO tb_address (cep, logradouro, numero, bairro, cidade, estado, complemento, client_id) VALUES ('01001000', 'Av. Paulista', '1000', 'Bela Vista', 'São Paulo', 'SP', 'Apto 1001', '1');

INSERT INTO tb_purchase (id_cart, id_address, status, payment_method) VALUES ('1', '1', 'completed', 'pix');

INSERT INTO tb_address (cep, logradouro, numero, bairro, cidade, estado, complemento, client_id) VALUES ('01002000', 'Av. Brigadeiro Faria Lima', '1500', 'Itaim Bibi', 'São Paulo', 'SP', 'Apto 1501', '2');

INSERT INTO tb_purchase (id_cart, id_address, status, payment_method) VALUES ('2', '2', 'completed', 'debito');

INSERT INTO tb_address (cep, logradouro, numero, bairro, cidade, estado, complemento, client_id) VALUES ('01003001', 'Rua José Paulino', '500', 'Vila Mariana', 'São Paulo', 'SP', 'Apto 500', '3');

INSERT INTO tb_purchase (id_cart, id_address, status, payment_method) VALUES ('3', '3', 'completed', 'credito');