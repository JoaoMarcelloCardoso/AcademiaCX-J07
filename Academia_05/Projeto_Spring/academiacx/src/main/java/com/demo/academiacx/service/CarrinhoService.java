package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ConstraintViolationException;
import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.ParametroNullException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.dto.carrinho.CarrinhoDto;
import com.demo.academiacx.repository.CarrinhoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        return modelMapper.map(carrinhoModels, new TypeToken<List<CarrinhoDto>>() {
        }.getType());
    }

    public CarrinhoDto findById(CarrinhoModel carrinhoModel) {

        if (carrinhoModel == null) {
            throw new ParametroInvalidoException("O Carrinho Model deve ser informado!");
        }

        if (carrinhoModel.getId() == null) {
            throw new ParametroInvalidoException("O Carrinho Model deve conter um id!");
        }

        try {
            carrinhoModel = carrinhoRepository.findById(carrinhoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto findById(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        CarrinhoModel carrinhoModel = new CarrinhoModel();
        try {
            carrinhoModel = carrinhoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }
    public CarrinhoDto findByCliente(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        CarrinhoModel carrinhoModel = new CarrinhoModel();
        try {
            carrinhoModel = carrinhoRepository.findByClienteId(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto insert(CarrinhoDto carrinhoDto) {

        carrinhoDto.setId(null);

        try {
            return new CarrinhoDto(carrinhoRepository.save(new CarrinhoModel(carrinhoDto)));
        } catch (Exception e) {
            throw new ParametroNullException("Algum dado obrigatório não foi inserido!");
        }
    }

    public CarrinhoDto update(CarrinhoDto carrinhoDto) {

        findById(new CarrinhoModel(carrinhoDto));

        try {
            return new CarrinhoDto(carrinhoRepository.save(new CarrinhoModel(carrinhoDto)));
        } catch (Exception e) {
            throw new ParametroNullException("Algum dado obrigatório não foi inserido!");
        }
    }

    public boolean delete(Long id) {

        findById(id);

        try {
            carrinhoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ConstraintViolationException("Esta ação viola a integridade dos dados presentes no banco de dados!");
        }
    }

    public void deleteByClienteId(ClienteModel clienteModel) {
        carrinhoRepository.deleteByClienteId(clienteModel.getId());
    }
}
