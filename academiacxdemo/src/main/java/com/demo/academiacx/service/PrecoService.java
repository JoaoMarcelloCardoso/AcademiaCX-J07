package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.EnderecoModel;
import com.demo.academiacx.model.PrecoModel;
import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.model.dto.EnderecoDto;
import com.demo.academiacx.model.dto.PrecoDto;
import com.demo.academiacx.model.dto.ProdutoDto;
import com.demo.academiacx.repository.PrecoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecoService {

    private final ModelMapper modelMapper;

    private final PrecoRepository precoRepository;

    public PrecoService(PrecoRepository precoRepository, ModelMapper modelMapper) {
        this.precoRepository = precoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PrecoDto> findAll() {

        List<PrecoModel> precoModel = precoRepository.findAll();

        return modelMapper.map(precoModel, new TypeToken<List<PrecoDto>>(){
        }.getType());
    }

    public PrecoDto findById(Long id){

        if(id == null){

            throw new ParametroInvalidoException("Id informado inválido");

        }

        PrecoModel precoModel = new PrecoModel();

        try {
            precoModel = precoRepository.findById(id).get();

        } catch (Exception e){

            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(precoModel, PrecoDto.class);
    }

    public PrecoDto findById(PrecoModel precoModel){

        if(precoModel == null){

            throw new ParametroInvalidoException("O Preco Model precisa ser informado");

        }

        if(precoModel.getId() == null){

            throw new ParametroInvalidoException("O Preco Model precisa ter um id");
        }

        try {
            precoModel = precoRepository.findById(precoModel.getId()).get();

        } catch (Exception e){

            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(precoModel, PrecoDto.class);    }

    public PrecoDto save(PrecoDto precoDto){

        precoDto.setId(null);

        PrecoModel precoModel = precoRepository.save(modelMapper.map(precoDto, PrecoModel.class));

        return modelMapper.map(precoModel, PrecoDto.class);
    }

    public PrecoDto update(PrecoDto precoDto){

        findById(precoDto.getId());

        PrecoModel precoModel = precoRepository.save(modelMapper.map(precoDto, PrecoModel.class));

        return modelMapper.map(precoModel, PrecoDto.class);

    }

    public boolean delete(Long id){

        try {

            precoRepository.deleteById(id);

        } catch (Exception e){

            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");

        }

        return true;
    }

    public List<PrecoDto> findByProdutoId(Long id){

        Optional<List<PrecoModel>> precoModel = precoRepository.findByProdutoId(id);

        return modelMapper.map(precoModel.get(), new TypeToken<List<PrecoDto>>(){
        }.getType());
    }
}
