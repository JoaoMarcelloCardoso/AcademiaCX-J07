package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.ParametroNullException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.EnderecoModel;
import com.demo.academiacx.model.dto.endereco.EnderecoDto;
import com.demo.academiacx.repository.EnderecoRepository;
import com.demo.academiacx.utils.ValidacaoUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ModelMapper modelMapper;
    private final ClienteService clienteService;

    public List<EnderecoDto> findAll() {

        List<EnderecoModel> enderecoModels = enderecoRepository.findAll();

        return modelMapper.map(enderecoModels, new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }

    public EnderecoDto findById(EnderecoModel enderecoModel) {

        if (enderecoModel == null) {
            throw new ParametroInvalidoException("O Endereço Model deve ser informado!");
        }

        if (enderecoModel.getId() == null) {
            throw new ParametroInvalidoException("O Endereço Model deve conter um id!");
        }

        try {
            enderecoModel = enderecoRepository.findById(enderecoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public List<EnderecoDto> findByClienteId(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }
            List<EnderecoModel> enderecoModels;
        try {
            enderecoModels = enderecoRepository.findByClienteId(id);

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(enderecoModels, new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }

    public EnderecoDto findById(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        EnderecoModel enderecoModel = new EnderecoModel();
        try {
            enderecoModel = enderecoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public EnderecoDto insert(EnderecoDto enderecoDto) {

        enderecoDto.setId(null);

        enderecoDto.setCep(enderecoDto.getCep().replace("-", ""));

        validarEndereco(enderecoDto);



        try {
            ClienteModel clienteModel = new ClienteModel(clienteService.findById(enderecoDto.getCliente_id()));

            EnderecoModel enderecoModel = new EnderecoModel(enderecoDto);

            enderecoModel.setCliente(clienteModel);

            return new EnderecoDto(enderecoRepository.save(enderecoModel));

        } catch (Exception e) {
            throw new ParametroNullException("Algum dado obrigatório não foi inserido!");
        }

    }

    public EnderecoDto update(EnderecoDto enderecoDto) {

        findById(new EnderecoModel(enderecoDto));

        validarEndereco(enderecoDto);

        enderecoDto.setCep(enderecoDto.getCep().replace("-", ""));

        try {
            return new EnderecoDto(enderecoRepository.save(new EnderecoModel(enderecoDto)));
        } catch (Exception e) {
            throw new ParametroNullException("Algum dado obrigatório não foi inserido!");
        }

    }

    public boolean delete(Long id) {

        findById(id);

        enderecoRepository.deleteById(id);

        return true;
    }


    private void validarEndereco(EnderecoDto enderecoDto) {
        //ValidacaoUtils.validarCep(enderecoDto.getCep(), "CEP não foi inserido ou é inválido!");
        //ValidacaoUtils.validarVazio(enderecoDto.getCidade(), "A cidade é obrigatória!");
        //ValidacaoUtils.validarRangeTamanho(enderecoDto.getUf(), 2, 2, "UF inserida inválida!");
    }
}
