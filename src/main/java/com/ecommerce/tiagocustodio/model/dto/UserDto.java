package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.DepartmentModel;
import com.ecommerce.tiagocustodio.model.UserModel;

public class UserDto {

    private Long id;
//    private String name;

    private String name;
    private String email;

    private DepartmentModel departmentModel;


    public UserDto() {
    }


    public UserDto(UserModel userModel) {
        this.id= userModel.getId();
        this.departmentModel= userModel.getDepartmentModel();
        this.email = userModel.getEmail();
        this.name = userModel.getName();
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

    public void setNome(String name) {
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

}
