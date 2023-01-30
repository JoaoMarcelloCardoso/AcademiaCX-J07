CREATE DATABASE commercenttcx;

USE commercenttcx;

CREATE TABLE tb_address (
    id BIGINT  AUTO_INCREMENT,
    cep VARCHAR(9) ,
    logradouro VARCHAR(100) ,
    numero VARCHAR(100) ,
    bairro VARCHAR(100) ,
    cidade VARCHAR(100) ,
    estado VARCHAR(2) ,
    complemento VARCHAR(100) ,
    client_id BIGINT ,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES tb_client(id)
);

CREATE TABLE tb_cart (
    id BIGINT AUTO_INCREMENT,
    total DECIMAL(10,2),
    client_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES tb_client(id)
);

CREATE TABLE tb_client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(11),
    nome VARCHAR(100) ,
    email VARCHAR(150) ,
    username VARCHAR(50) UNIQUE ,
    password VARCHAR(50) ,
    PRIMARY KEY (id)
);

CREATE TABLE tb_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INT,
    total DECIMAL(10,2),
    product_id BIGINT,
    cart_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id),
    FOREIGN KEY (cart_id) REFERENCES tb_cart(id)
);

CREATE TABLE tb_product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sku VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_purchase (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_cart BIGINT ,
    id_address BIGINT ,
    dataDaCompra DATETIME ,
    status VARCHAR(50),
    payment_method VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_cart) REFERENCES tb_cart(id),
    FOREIGN KEY (id_address) REFERENCES tb_address(id)
);

