package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.dto.ClienteDto;
import com.demo.academiacx.repository.ClienteRepository;
import com.demo.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {

    private final ModelMapper modelMapper;

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClienteDto> findAll(){

        List<ClienteModel> clienteModel = clienteRepository.findAll();
        return modelMapper.map(clienteModel, new TypeToken<List<ClienteDto>>(){
        }.getType());
    }

    public ClienteDto findById(Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id informado é inválido");
        }

        ClienteModel clienteModel = new ClienteModel();

        try {
            clienteModel = clienteRepository.findById(id).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto findById(ClienteModel clienteModel){

        if(clienteModel == null){
            throw new ParametroInvalidoException("A Cliente Model precisa ser informada");
        }

        if(clienteModel.getId() == null){
            throw new ParametroInvalidoException("A Cliente Model deve conter um id");
        }

        try {
            clienteModel = clienteRepository.findById(clienteModel.getId()).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto save(ClienteDto clienteDto){

        validateSave(clienteDto);

        ClienteModel clienteModel = clienteRepository.save(modelMapper.map(clienteDto, ClienteModel.class));

        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto update(ClienteDto clienteDto){

        findById(clienteDto.getId());

        ClienteModel clienteModel = clienteRepository.save(modelMapper.map(clienteDto, ClienteModel.class));

        return modelMapper.map(clienteModel, ClienteDto.class);

    }

    public boolean delete(Long id){

        findById(id);

        try{
            clienteRepository.deleteById(id);
        }catch (Exception e){
            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");
        }

        return true;
    }

    public ClienteDto findByUsername(String username){
        ClienteModel clienteModel = clienteRepository.findByUsername(username).get();
        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto findByCpf(String cpf){
        ClienteModel clienteModel = clienteRepository.findByCpf(cpf).get();
        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    private void validateSave(ClienteDto clienteDto) {
        ValidacaoUtils.validarTamanhoMinimoMaximo(clienteDto.getNome(), 6, 30, "O nome deve possuir entre 6 e 30 caracteres");

        ValidacaoUtils.validarTamanhoMinimoMaximo(clienteDto.getUsername(), 6, 20, "Username deve possuir entre 6 a 20 caracteres");

        ValidacaoUtils.passwordValidation(clienteDto.getPasswordDecrypt(), "Senha de 8 a 16 caracteres com pelo menos um dígito, pelo menos um letra minúscula, pelo menos uma letra maiúscula, pelo menos uma caractere especial sem espaços em branco");

        ValidacaoUtils.cpfValidation(clienteDto.getCpf(), "Cpf informado inválido");
    }
}
