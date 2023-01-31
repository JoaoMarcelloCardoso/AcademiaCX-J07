package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.UserModel;
import com.ecommerce.tiagocustodio.repository.ClienteRepository;
import com.ecommerce.tiagocustodio.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public Optional<ClienteModel> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}