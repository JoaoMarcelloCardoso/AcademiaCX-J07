package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.CarrinhoModel;
import com.academiacx.models.dto.CarrinhoDto;
import com.academiacx.repository.CarrinhoRepository;
import com.academiacx.repository.ClienteRepository;
import com.academiacx.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public CarrinhoService(
            CarrinhoRepository carrinhoRepository,
            ModelMapper modelMapper,
            ProdutoRepository produtoRepository,
            ClienteRepository clienteRepository
    ) {
        this.carrinhoRepository = carrinhoRepository;
        this.modelMapper = modelMapper;
        this.produtoRepository=produtoRepository;
        this.clienteRepository=clienteRepository;
    }

    public List<CarrinhoDto> findAll() {
        return carrinhoRepository
                .findAll()
                .stream()
                .map(CarrinhoDto::new)
                .toList();

    }

    public CarrinhoDto findById(Long id) {
        Optional<CarrinhoModel> carrinho = carrinhoRepository.findById(id);
        return carrinho
                .map(CarrinhoDto::new)
                .orElse(null);
    }

    public List<CarrinhoDto> findCarrinhoByClienteId(Long clienteId){
        List<CarrinhoModel> carrinhoModel = carrinhoRepository.findCarrinhoModelsByClienteId(clienteId).get();
        return carrinhoModel
                .stream()
                .map(CarrinhoDto::new)
                .toList();
    }

    public CarrinhoDto save(CarrinhoModel carrinhoModel,Long clientId) {
        CarrinhoModel newCarrinhoModel = new CarrinhoModel();
        clienteRepository.findById(clientId).ifPresent(newCarrinhoModel::setCliente);
        newCarrinhoModel.setTotal(carrinhoModel.getTotal());
        newCarrinhoModel.setDatahora(carrinhoModel.getDatahora());
        carrinhoRepository.save(newCarrinhoModel);
        return new CarrinhoDto(newCarrinhoModel);
    }

    public CarrinhoDto update(CarrinhoDto carrinhoDto) {
        Optional<CarrinhoModel> carrinho = carrinhoRepository.findById(carrinhoDto.getId());
        if (carrinho.isPresent()) {
            CarrinhoModel carrinhoModel = carrinho.get();
            carrinhoModel.setTotal((carrinhoDto.getTotal()));
            carrinhoModel.setTotal((carrinhoDto.getTotal()));
            carrinhoRepository.save(carrinhoModel);
            return new CarrinhoDto(carrinhoModel);
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado");
    }

    public CarrinhoDto delete(CarrinhoDto carrinhoDto) {
        try {
            Optional<CarrinhoModel> carrinho = carrinhoRepository.findById(carrinhoDto.getId());
            if (carrinho.isPresent()) {
                CarrinhoModel carrinhoModel = carrinho.get();
                carrinhoRepository.delete(carrinhoModel);
                return new CarrinhoDto(carrinhoModel);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");
        }
        return null;

    }

}

