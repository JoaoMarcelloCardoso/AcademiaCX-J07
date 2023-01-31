package com.ecommerce.tiagocustodio.service;


import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.repository.CarrinhoRepository;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public List<CarrinhoModel> findAll() {
        return carrinhoRepository.findAll();
    }

    public CarrinhoModel save(CarrinhoModel carrinhoModel) {
        return carrinhoRepository.save(carrinhoModel);
    }

    public Optional<CarrinhoModel> findById(Long id) {
        return carrinhoRepository.findById(id);
    }

    public void deleteById(Long id) {
        carrinhoRepository.deleteById(id);
    }
}