package com.ecommerce.tiagocustodio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "endereco")
    private String endereco;
    @OneToMany
    @JoinColumn(name = "carrinho_id")
    private List<CarrinhoModel> carrinhos;



    public ClienteModel() {}

    public ClienteModel(Long id, String name, String last_name, String email, String phone_number, String endereco, List<CarrinhoModel> carrinhos) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.carrinhos = carrinhos;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<CarrinhoModel> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<CarrinhoModel> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}