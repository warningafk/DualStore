package com.dualstore.tienda.service;

import com.dualstore.tienda.entity.Cliente;
import com.dualstore.tienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service("clienteUserDetailsService")
public class ClienteUserDetailsService implements UserDetailsService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreoAndEstado(username, 1)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CLIENTE");
        
        return new org.springframework.security.core.userdetails.User(
            cliente.getCorreo(),
            cliente.getContrasena(),
            Collections.singletonList(authority)
        );
    }
}
