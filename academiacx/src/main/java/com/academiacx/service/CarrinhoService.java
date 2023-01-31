package com.academiacx.service;

import com.academiacx.handler.exceptions.ConstraintViolationException;
import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.handler.exceptions.ParametroNullException;
import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.CarrinhoModel;
import com.academiacx.model.ClienteModel;
import com.academiacx.model.dto.CarrinhoDto;
import com.academiacx.repository.CarrinhoRepository;
import com.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ModelMapper modelMapper;

    private final ClienteService clienteService;


    public CarrinhoService(CarrinhoRepository carrinhoRepository, ModelMapper modelMapper, ClienteService clienteService) {
        this.carrinhoRepository = carrinhoRepository;
        this.modelMapper = modelMapper;
        this.clienteService = clienteService;
    }


    public List<CarrinhoDto> findAll() {

        List<CarrinhoModel> carrinhoModels = carrinhoRepository.findAll();

        return modelMapper.map(carrinhoModels, new TypeToken<List<CarrinhoDto>>() {
        }.getType());
    }


    public CarrinhoDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id de carrinho informado é inválido!");

        Optional<CarrinhoModel> carrinhoModel = carrinhoRepository.findById(id);

        if (!carrinhoModel.isPresent() || carrinhoModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de carrinho informado não encontrado!");
        }

        return modelMapper.map(carrinhoModel.get(), CarrinhoDto.class);
    }


    public List<CarrinhoDto> findByClienteId(Long id) {

        ValidacaoUtils.validarId(id, "Id de cliente informado é inválido!");

        Optional<List<CarrinhoModel>> carrinhoModels = carrinhoRepository.findAllByClienteId(id);

        if (!carrinhoModels.isPresent() || carrinhoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("O cliente de id informado não possui nenhum carrinho ou não foi encontrado!");
        }

        return modelMapper.map(carrinhoModels.get(), new TypeToken<List<CarrinhoDto>>() {
        }.getType());
    }


    public CarrinhoDto insert(CarrinhoModel carrinhoModel) {

        carrinhoModel.setId(null);

        validarCarrinho(carrinhoModel);

        Optional<List<CarrinhoModel>> carrinhos = carrinhoRepository.findAllByClienteId(carrinhoModel.getCliente().getId());

        if (carrinhos.isPresent() && carrinhos.get().stream().anyMatch(CarrinhoModel::isAtivo)) {
            throw new ConstraintViolationException("O cliente com o id inserido já possui um carrinho ativo!");
        }

        carrinhoModel.setData(LocalDate.now());
        carrinhoModel.setTotal(new BigDecimal(0.00));
        carrinhoModel.setAtivo(true);

        try {
            return new CarrinhoDto(carrinhoRepository.save(carrinhoModel));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao adicionar carrinho!");
        }
    }


    public CarrinhoDto update(CarrinhoModel carrinhoModel) {

        if (carrinhoModel == null) {
            throw new ParametroInvalidoException("O Carrinho Model deve ser informado!");
        }

        ValidacaoUtils.validarId(carrinhoModel.getId(), "Id de carrinho não foi inserido ou é inválido!");

        Optional<CarrinhoModel> carrinho = carrinhoRepository.findById(carrinhoModel.getId());

        if (!carrinho.isPresent() || carrinho.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de carrinho informado não encontrado!");
        }

        carrinhoModel.setCliente(new ClienteModel(carrinhoModel.getCliente().getId()));

        carrinhoModel.setTotal(new BigDecimal(0.00));

        Optional<BigDecimal> total = carrinhoRepository.calcularTotal(carrinhoModel.getId());

        if (total.isPresent() && !total.isEmpty()) {
            carrinhoModel.setTotal(total.get());
        }

        try {
            return new CarrinhoDto(carrinhoRepository.save(carrinhoModel));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao atualizar carrinho!");
        }
    }


    public CarrinhoDto atualizarTotalComCupom(CarrinhoModel carrinhoModel, String cupom) {

        Optional<BigDecimal> total = carrinhoRepository.calcularTotal(carrinhoModel.getId());

        if (total.isPresent() && !total.isEmpty()) {
            carrinhoModel.setTotal(total.get());
        }

        switch (cupom) {
            case "academiacx10" -> carrinhoModel.setTotal(carrinhoModel.getTotal().multiply(BigDecimal.valueOf(0.9)));
            case "academiacx20" -> carrinhoModel.setTotal(carrinhoModel.getTotal().multiply(BigDecimal.valueOf(0.8)));
            case "academiacx30" -> carrinhoModel.setTotal(carrinhoModel.getTotal().multiply(BigDecimal.valueOf(0.7)));
        }

        carrinhoModel.setTotal(carrinhoModel.getTotal().setScale(2, RoundingMode.HALF_UP));

        try {
            return new CarrinhoDto(carrinhoRepository.save(carrinhoModel));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao inserir carrinho!");
        }
    }


    public boolean delete(Long id) {

        CarrinhoDto carrinhoDto = findById(id);

        if (!carrinhoDto.isAtivo()) {
            throw new ConstraintViolationException("O carrinho referente ao id informado não está ativo!");
        }

        carrinhoRepository.limparCarrinho(carrinhoDto.getId());
        carrinhoRepository.deleteById(id);
        return true;
    }


    public boolean deleteByClienteId(Long id) {

        ValidacaoUtils.validarId(id, "Id de cliente informado é inválido!");

        Optional<List<CarrinhoModel>> carrinhoModels = carrinhoRepository.findAllByClienteId(id);

        if (!carrinhoModels.isPresent() || carrinhoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de cliente informado não encontrado!");
        }

        Optional<CarrinhoModel> carrinhoAtivo = carrinhoModels.get().stream().filter(CarrinhoModel::isAtivo).findFirst();

        if (!carrinhoAtivo.isPresent() || carrinhoAtivo.isEmpty()) {
            throw new ConstraintViolationException("O cliente cujo id foi informado não possui nenhum carrinho ativo para deleção!");
        }


        carrinhoRepository.limparCarrinho(carrinhoAtivo.get().getId());
        carrinhoRepository.deleteByClienteId(id);
        return true;

    }


    private void validarCarrinho(CarrinhoModel carrinhoModel) {
        if (carrinhoModel.getCliente() == null) {
            throw new ParametroNullException("O cliente precisa ser informado!");
        }

        ValidacaoUtils.validarId(carrinhoModel.getCliente().getId(), "Id de cliente informado inválido!");

        carrinhoModel.setCliente(new ClienteModel(clienteService.findById(carrinhoModel.getCliente().getId())));
    }

}
