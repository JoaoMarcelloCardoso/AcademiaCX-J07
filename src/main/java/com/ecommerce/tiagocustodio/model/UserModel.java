package com.ecommerce.tiagocustodio.model;

import com.ecommerce.tiagocustodio.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Column(nullable = true)
    private Boolean flAdmin;

    @Column(nullable = true)
    private Boolean flCliente;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentModel departmentModel;

    //Login
    @Column(nullable = true, unique = true)
    private String username;
    @Column(nullable = true)
    private String password;


    public UserModel() {
    }

    public UserModel(UserDto userDto) {
        this.id= userDto.getId();
        this.departmentModel= userDto.getDepartmentModel();
        this.email = userDto.getEmail();
        this.name = userDto.getName();
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

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
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

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        if (flAdmin != null && flAdmin) {
            roles.add("ADMIN");
        }
        if (flCliente != null && flCliente) {
            roles.add("CLIENTE");
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = this.getRoles();
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
