package com.academiacx.service;

import com.academiacx.handler.exceptions.ConstraintViolationException;
import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.handler.exceptions.ParametroNullException;
import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.ClienteModel;
import com.academiacx.model.dto.ClienteDto;
import com.academiacx.repository.ClienteRepository;
import com.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }


    public List<ClienteDto> findAll() {

        List<ClienteModel> clienteModels = clienteRepository.findAll();

        return modelMapper.map(clienteModels, new TypeToken<List<ClienteDto>>() {
        }.getType());
    }


    public ClienteDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id de cliente informado é inválido!");

        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);

        if (!clienteModel.isPresent() || clienteModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de cliente informado não encontrado!");
        }

        return modelMapper.map(clienteModel.get(), ClienteDto.class);
    }


    public List<ClienteDto> findByCpfOrEmailOrTelefone(String cpf, String email, String telefone) {

        Optional<List<ClienteModel>> clienteModels = clienteRepository.findByCpfOrEmailOrTelefone(cpf, email, telefone);

        if (!clienteModels.isPresent() || clienteModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com os filtros informados!");
        }

        return modelMapper.map(clienteModels.get(), new TypeToken<List<ClienteDto>>() {
        }.getType());
    }


    public ClienteDto insert(ClienteModel clienteModel) {

        clienteModel.setId(null);

        validarCliente(clienteModel);

        Optional<ClienteModel> cliente = clienteRepository.findByUsername(clienteModel.getUsername());

        if (cliente.isPresent()) {
            throw new ConstraintViolationException("O username informado já existe!");
        }

        clienteModel.setPassword(new BCryptPasswordEncoder().encode(clienteModel.getPassword()));

        try {
            return new ClienteDto(clienteRepository.save(clienteModel));
        } catch (Exception e) {
            throw new ConstraintViolationException("Algum dado inserido viola uma restrição do banco de dados (dado nulo ou já existente)!");
        }
    }


    public ClienteDto update(ClienteModel clienteModel) {

        if (clienteModel == null) {
            throw new ParametroInvalidoException("O Cliente Model deve ser informado!");
        }

        findById(clienteModel.getId());

        validarCliente(clienteModel);

        clienteModel.setPassword(new BCryptPasswordEncoder().encode(clienteModel.getPassword()));

        try {
            return new ClienteDto(clienteRepository.save(clienteModel));
        } catch (Exception e) {
            throw new ParametroNullException("Algum dado inserido viola uma restrição do banco de dados (dado nulo ou já existente)!");
        }

    }


    public boolean delete(Long id) {

        findById(id);

        Optional<List<ClienteModel>> clientes = clienteRepository.validarItens(id);

        if (clientes.isPresent() && !clientes.get().isEmpty() && clientes.get().stream().anyMatch(cliente -> cliente.getId().equals(id))) {
            throw new ConstraintViolationException("Um cliente que já fez uma compra ou possui um item no carrinho não pode ser deletado!");
        }

        try {
            clienteRepository.limparEnderecos(id);
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ConstraintViolationException("Esta ação viola a integridade dos dados presentes no banco de dados!");
        }

    }


    public void validarCliente(ClienteModel clienteModel) {
        ValidacaoUtils.validarVazio(clienteModel.getNome(), "O nome é obrigatório!");
        ValidacaoUtils.validarCpf(clienteModel.getCpf(), "O CPF não foi inserido ou é inválido!");
        ValidacaoUtils.validarEmail(clienteModel.getEmail(), "O E-mail não foi inserido ou é inválido!");
        ValidacaoUtils.validarTelefone(clienteModel.getTelefone(), "O Telefone não foi inserido ou é inválido!");
        ValidacaoUtils.validarVazio(clienteModel.getPassword(), "A senha precisa ser informada!");
        ValidacaoUtils.validarVazio(clienteModel.getUsername(), "O username precisa ser informado!");

        if (clienteModel.getDataNasc() == null) {
            throw new ParametroNullException("A data de nascimento precisa ser informada!");
        }

        clienteModel.setCpf(clienteModel.getCpf()
                .replace(".", "")
                .replace("-", ""));

        clienteModel.setTelefone(clienteModel.getTelefone()
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", ""));
    }
}
