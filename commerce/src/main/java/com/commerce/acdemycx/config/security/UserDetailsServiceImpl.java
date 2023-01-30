package com.commerce.acdemycx.config.security;


import com.commerce.acdemycx.repository.ClientRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {


    final ClientRepository clientRepository;

    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente com username: " + username));
    }
}
