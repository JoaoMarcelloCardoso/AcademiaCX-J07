package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}