package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "tb_user")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = true)
    private Boolean flAdmin;
    @Column(nullable = true)
    private Boolean flCliente;
    @Column(nullable = true, unique = true)
    private String username;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteModel cliente;

    private String cpf;

    public UserModel() {
    }

    public UserModel(UserDto userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getNome();
        this.password = userDto.getPassword();
        this.username = userDto.getUsername();
        this.cpf = userDto.getCpf();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFlAdmin() {
        return flAdmin;
    }

    public void setFlAdmin(Boolean flAdmin) {
        this.flAdmin = flAdmin;
    }

    public Boolean getFlCliente() {
        return flCliente;
    }

    public void setFlCliente(Boolean flCliente) {
        this.flCliente = flCliente;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
