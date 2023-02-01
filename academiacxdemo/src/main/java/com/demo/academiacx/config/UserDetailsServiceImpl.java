package com.demo.academiacx.config;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    final ClienteRepository userRepository;

    public UserDetailsServiceImpl(ClienteRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ClienteModel clienteModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username));

        return clienteModel;
    }
}
