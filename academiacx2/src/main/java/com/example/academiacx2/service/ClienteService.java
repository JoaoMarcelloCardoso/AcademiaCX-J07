package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.model.ClienteModel;
import com.example.academiacx2.model.dto.ClienteDto;
import com.example.academiacx2.repository.ClienteRepository;
import com.example.academiacx2.repository.VendaRepository;
import com.example.academiacx2.utils.Validation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    private final VendaRepository vendaRepository;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper,VendaRepository vendaRepository) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.vendaRepository = vendaRepository;
    }

    public List<ClienteDto> findall(){
        List<ClienteModel> clienteModel = clienteRepository.findAll();

        return modelMapper.map(clienteModel, new TypeToken<List<ClienteDto>>(){
        }.getType());
    }

    public ClienteDto findById(ClienteModel clienteModel) {

        if (clienteModel == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        if (clienteModel.getId() == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        try {
            clienteModel = clienteRepository.findById(clienteModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto findById(Long id) {

        if(id == null){
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        ClienteModel clienteModel;

        try {
            clienteModel = clienteRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(clienteModel, ClienteDto.class);
    }

    public ClienteDto insert(ClienteDto clienteDto) {
        clienteDto.setId(null);

        validarInsert(clienteDto);

        ClienteModel clienteModel = clienteRepository.save(modelMapper.map(clienteDto, ClienteModel.class));

        return modelMapper.map(clienteModel,ClienteDto.class);
    }

    public ClienteModel update(ClienteModel clienteModel) {

        if(!clienteRepository.existsById(clienteModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return clienteRepository.save(clienteModel);
    }

    public boolean delete(Long id){

        ClienteModel clienteModel = new ClienteModel();

        if(!clienteRepository.existsById(id) || vendaRepository.existsById(clienteModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        clienteRepository.deleteById(id);

        return true;
    }
    public List<ClienteModel> findByCpfOrNome(String cpf, String nome) {

        Optional<List<ClienteModel>> listClienteModel = clienteRepository.findByCpfOrNome(cpf, nome);

        if (listClienteModel.isPresent()) {
            return listClienteModel.stream().findFirst().get();
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(ClienteDto clienteDto){


        if(clienteDto.getId() == null || clienteRepository.findById(clienteDto.getId()).isPresent()){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        Validation.cpfValidation(clienteDto.getCpf(),"Cpf inválido, digite outro");

        if(clienteDto.getNome() == null){
            throw new ParametroInvalidoException("Nome inválido, digite outro");
        }

        if(clienteDto.getUsername() == null || clienteRepository.findByUsername(clienteDto.getUsername()).isPresent()){
            throw new ParametroInvalidoException("Username inválido, digite outro");
        }

        if(clienteDto.getPassword() == null){
            throw new ParametroInvalidoException("Senha inválida, digite outro");
        }
    }
}
