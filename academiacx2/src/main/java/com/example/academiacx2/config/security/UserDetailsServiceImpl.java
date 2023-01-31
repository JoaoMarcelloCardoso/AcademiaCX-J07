package com.example.academiacx2.config.security;

import com.example.academiacx2.handler.exceptions.AcessoNaoAutorizadoException;
import com.example.academiacx2.model.ClienteModel;
import com.example.academiacx2.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final ClienteRepository clienteRepository;

    public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws AcessoNaoAutorizadoException {
        ClienteModel clienteModel = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new AcessoNaoAutorizadoException("Usuario nao encontrado com username: " + username
                        + " - Acesso não autorizado"));

        return clienteModel;
    }
}
