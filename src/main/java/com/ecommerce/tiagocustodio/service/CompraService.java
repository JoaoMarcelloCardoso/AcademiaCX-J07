package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.CompraModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.model.CompraModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.repository.CompraRepository;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<CompraModel> findAll() {
        return compraRepository.findAll();
    }

    public CompraModel save(CompraModel compraModel) {
        return compraRepository.save(compraModel);
    }

    public Optional<CompraModel> findById(Long id) {
        return compraRepository.findById(id);
    }

    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }
}