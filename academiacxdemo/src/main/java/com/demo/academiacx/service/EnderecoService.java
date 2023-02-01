package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.EnderecoModel;
import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.model.dto.ClienteDto;
import com.demo.academiacx.model.dto.EnderecoDto;
import com.demo.academiacx.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final ModelMapper modelMapper;

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    public List<EnderecoDto> findAll(){

        List<EnderecoModel> enderecoModel = enderecoRepository.findAll();
        return modelMapper.map(enderecoModel, new TypeToken<List<EnderecoDto>>(){
        }.getType());

    }

    public EnderecoDto findById(Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id informada é inválida");
        }

        EnderecoModel enderecoModel = new EnderecoModel();

        try{
            enderecoModel = enderecoRepository.findById(id).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public EnderecoDto findById(EnderecoModel enderecoModel){

        if(enderecoModel == null){
            throw new ParametroInvalidoException("O Endereco Model precisa ser informado");
        }

        if(enderecoModel.getId() == null){
            throw new ParametroInvalidoException("O Endereco Model deve conter um id");
        }

        try{
            enderecoModel = enderecoRepository.findById(enderecoModel.getId()).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);

    }

    public EnderecoDto save(EnderecoDto enderecoDto){

        enderecoDto.setId(null);

        EnderecoModel enderecoModel =  enderecoRepository.save(modelMapper.map(enderecoDto, EnderecoModel.class));

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public EnderecoDto update(EnderecoDto enderecoDto){

        findById(enderecoDto.getId());

        EnderecoModel enderecoModel =  enderecoRepository.save(modelMapper.map(enderecoDto, EnderecoModel.class));

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public boolean delete(Long id){

        try{

            enderecoRepository.deleteById(id);

        } catch (Exception e){

            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");

        }

        return true;
    }

    public List<EnderecoDto> findByClienteId(Long id) {


        Optional<List<EnderecoModel>> enderecoModel = enderecoRepository.findByClienteId(id);

        return modelMapper.map(enderecoModel.get(), new TypeToken<List<EnderecoDto>>() {
        }.getType());
    }




}
