package com.academiacx.service;

import com.academiacx.handler.exceptions.ConstraintViolationException;
import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.handler.exceptions.ParametroNullException;
import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.ClienteModel;
import com.academiacx.model.EnderecoModel;
import com.academiacx.model.dto.EnderecoDto;
import com.academiacx.repository.EnderecoRepository;
import com.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ModelMapper modelMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }


    public List<EnderecoDto> findAll() {

        List<EnderecoModel> enderecoModels = enderecoRepository.findAll();

        return modelMapper.map(enderecoModels, new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }


    public EnderecoDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id de endereço informado é inválido!");

        Optional<EnderecoModel> enderecoModel = enderecoRepository.findById(id);

        if (!enderecoModel.isPresent() || enderecoModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de endereço informado não encontrado!");
        }

        return modelMapper.map(enderecoModel.get(), EnderecoDto.class);
    }


    public List<EnderecoDto> findByClienteId(Long id) {

        ValidacaoUtils.validarId(id, "Id de cliente informado é inválido!");

        Optional<List<EnderecoModel>> enderecoModels = enderecoRepository.findAllByClienteId(id);

        if (!enderecoModels.isPresent() || enderecoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Cliente de id informado não foi encontrado, ou não possui nenhum endereço!");
        }

        return modelMapper.map(enderecoModels.get(), new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }


    public List<EnderecoDto> findByBairroOrCidadeOrUf(String bairro, String cidade, String uf) {

        Optional<List<EnderecoModel>> enderecoModels = enderecoRepository.findByBairroOrCidadeOrUf(bairro, cidade, uf);

        if (!enderecoModels.isPresent() || enderecoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Endereço não encontrado com os filtros informados!");
        }

        return modelMapper.map(enderecoModels.get(), new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }


    public EnderecoDto insert(EnderecoModel enderecoModel) {

        enderecoModel.setId(null);

        validarEndereco(enderecoModel, false);

        enderecoModel.setCliente(new ClienteModel(enderecoModel.getCliente().getId()));

        try {
            return new EnderecoDto(enderecoRepository.save(enderecoModel));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao adicionar endereço!");
        }

    }


    public EnderecoDto update(EnderecoModel enderecoModel) {

        if (enderecoModel == null) {
            throw new ParametroInvalidoException("O Endereço Model deve ser informado!");
        }

        ValidacaoUtils.validarId(enderecoModel.getId(), "Id de endereço informado é inválido!");

        validarEndereco(enderecoModel, false);

        Optional<EnderecoModel> endereco = enderecoRepository.findById(enderecoModel.getId());
        if (!endereco.isPresent() || endereco.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de endereço informado não encontrado!");
        }

        enderecoModel.setCliente(new ClienteModel(enderecoModel.getCliente().getId()));

        try {
            return new EnderecoDto(enderecoRepository.save(enderecoModel));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao atualizar endereço!");
        }

    }


    public boolean delete(Long id) {

        ValidacaoUtils.validarId(id, "Id de endereço informado é inválido!");

        Optional<EnderecoModel> enderecoModel = enderecoRepository.findById(id);

        if (!enderecoModel.isPresent() || enderecoModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de endereço informado não foi encontrado!");
        }

        List<EnderecoModel> enderecoModels = enderecoRepository.findAllByClienteId(enderecoModel.get().getCliente().getId()).get();

        if (enderecoModels.isEmpty() || enderecoModels.size() <= 1) {
            throw new ConstraintViolationException("Não é possível deletar, o cliente deve ter ao menos um endereço!");
        }

        enderecoRepository.deleteById(id);
        return true;
    }


    public void validarEndereco(EnderecoModel enderecoModel, boolean cadastro) {
        ValidacaoUtils.validarNumeroEndereco(enderecoModel.getNumero(), "Número não foi inserido ou é inválido!");
        ValidacaoUtils.validarCep(enderecoModel.getCep(), "CEP não foi inserido ou é inválido!");
        ValidacaoUtils.validarVazio(enderecoModel.getCidade(), "A cidade é obrigatória!");
        ValidacaoUtils.validarRangeTamanho(enderecoModel.getUf(), 2, 2, "UF não foi inserida ou é inválida!");
        ValidacaoUtils.validarVazio(enderecoModel.getBairro(), "O bairro é obrigatório!");
        ValidacaoUtils.validarVazio(enderecoModel.getLogradouro(), "O logradouro é obrigatório!");

        if (enderecoModel.getCliente() == null) {
            throw new ParametroNullException("O Cliente deve ser informado!");
        }

        if (!cadastro) {
            ValidacaoUtils.validarId(enderecoModel.getCliente().getId(), "Id de cliente não foi inserido ou é inválido!");
        }

        enderecoModel.setCep(enderecoModel.getCep().replace("-", ""));
        enderecoModel.setNumero(enderecoModel.getNumero()
                .replace("n", "")
                .replace("N", "")
                .replace(".", "")
                .replace("º", "")
                .replace(" ", ""));
    }

}
