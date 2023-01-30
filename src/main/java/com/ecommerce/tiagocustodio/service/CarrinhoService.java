package com.ecommerce.tiagocustodio.service;


import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.model.dto.CarrinhoDto;
import com.ecommerce.tiagocustodio.model.dto.UserDto;
import com.ecommerce.tiagocustodio.repository.CarrinhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    private final ModelMapper modelMapper;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ModelMapper modelMapper) {
        this.carrinhoRepository = carrinhoRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarrinhoDto> findAll() {
        List<CarrinhoModel> carrinhoModels = carrinhoRepository.findAll();
        return carrinhoModels.stream().map(CarrinhoDto::new).collect(Collectors.toList());
    }

    public CarrinhoDto findById(Long id) {
        CarrinhoModel carrinhoModel = carrinhoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carrinho não encontrado com id: " + id));
        return new CarrinhoDto(carrinhoModel);


    /*public CarrinhoDto update(CarrinhoModel carrinhoDto) {
        CarrinhoModel carrinhoModel = carrinhoRepository.findById(carrinhoDto.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carrinho não encontrado com id: " + carrinhoDto.getId()));
        carrinhoModel.setDataHora(carrinhoDto.getDataHora());
        carrinhoModel.setTotal(carrinhoDto.getTotal());
        carrinhoModel = carrinhoRepository.save(carrinhoModel);
        return new CarrinhoDto(carrinhoModel);*/
    }

    public boolean delete(Long id) {
        findById(id);
        carrinhoRepository.deleteById(id);
        return true;
    }

    public CarrinhoDto insert(CarrinhoDto carrinhoDto) {
        carrinhoDto.setId(null);

        CarrinhoDto result = new CarrinhoDto(carrinhoRepository.save(new CarrinhoModel(carrinhoDto)));

        return result;
    }
}