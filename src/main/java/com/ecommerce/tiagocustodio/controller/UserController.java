package com.ecommerce.tiagocustodio.controller;

import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.model.dto.UserDto;
import com.ecommerce.tiagocustodio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<UserDto> response = userService.findAll();

        return response == null ? ResponseEntity.unprocessableEntity().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {

        return userService.findById(id);
    }
    @PostMapping("/salvar")
    public UserDto insert(@RequestBody UserDto userDto) {

        return userService.insert(userDto);
    }


    @PutMapping("/update")
    public UserModel update(@RequestBody UserModel userModel) {

        return userService.update(userModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = false) Long id) {

        return userService.delete(id);
    }

    @GetMapping("/filter")
    public UserModel filter(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "email", required = false) String email) {

        return userService.findByNameAndEmail(name, email);
    }

    @GetMapping("/buscar-id")
    public UserDto filter(@RequestParam(value = "id", required = true) Long id) {

        return userService.buscarPorId(id);
    }




}
