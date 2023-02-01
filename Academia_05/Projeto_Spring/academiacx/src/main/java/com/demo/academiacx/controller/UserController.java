package com.demo.academiacx.controller;

import com.demo.academiacx.model.UserModel;
import com.demo.academiacx.model.dto.user.UserDto;
import com.demo.academiacx.model.dto.user.UserResponseDto;
import com.demo.academiacx.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

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
    public UserResponseDto insert(@RequestBody UserDto userDto) {
        return userService.insert(userDto);
    }
    @PutMapping("/update")
    public UserModel update(@RequestBody UserModel userModel) {

        return userService.update(userModel);
    }

    @DeleteMapping("/delete")
    public boolean deleteById(@RequestParam(value = "id", required = true) Long id) {

        return userService.delete(id);
    }

    @GetMapping("/filter")
    public UserDto filter(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "email", required = false) String email,
                            @RequestParam(value = "id", required = false) Long id) {

        return userService.findByNameOrEmailOrId(name, email, id, username);
    }

}
