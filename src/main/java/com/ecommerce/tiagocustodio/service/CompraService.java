package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.CompraModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.model.dto.CompraDto;
import com.ecommerce.tiagocustodio.model.dto.UserDto;
import com.ecommerce.tiagocustodio.repository.CompraRepository;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;

    private final ModelMapper modelMapper;

    public CompraService(CompraRepository compraRepository, ModelMapper modelMapper) {
        this.compraRepository = compraRepository;
        this.modelMapper = modelMapper;
    }

    public List<CompraDto> findAll() {
        List<CompraModel> compraModels = compraRepository.findAll();


        return modelMapper.map(compraModels, new TypeToken<List<CompraDto>>() {
        }.getType());
    }

    public CompraDto findById(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");

        }

        CompraModel compraModel = new CompraModel();
        try {
            compraModel = compraRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(compraModel, CompraDto.class);
    }

    public CompraDto insert(CompraDto compraDto) {
        compraDto.setId(null);

        CompraDto result = new CompraDto(compraRepository.save(new CompraModel(compraDto)));

        return result;
    }
}
