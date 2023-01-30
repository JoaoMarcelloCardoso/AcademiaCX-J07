package com.commerce.acdemycx.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "tb_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    private String email;

    @Column(nullable = true, unique = true)
    private String username;
    @Column(nullable = true)
    private String password;


    @OneToMany(mappedBy = "clientModel", cascade = CascadeType.ALL)
    private List<AddressModel> addresses;

    @OneToMany(mappedBy = "clientModel", cascade = CascadeType.ALL)
    private List<CartModel> carts;


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