package com.academiacx.config.security;

import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.ClienteModel;
import com.academiacx.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final ClienteRepository clienteRepository;


    public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsuarioNaoEncontradoException {
        return clienteRepository.findByCpf(cpf).orElseThrow(()-> new UsuarioNaoEncontradoException("CPF não encontrado"));
    }
}